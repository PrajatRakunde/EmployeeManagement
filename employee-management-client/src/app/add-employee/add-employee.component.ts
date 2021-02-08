import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Department } from '../department';
import { DepartmentService } from '../department.service';
import { Employee } from '../employee';
import { EmployeeModel } from '../employee-model';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employeeModel: EmployeeModel;
  employee!: Employee;
  submitted = false;
  update = false;
  departments!: Observable<Department>[];
  employeeForm!: FormGroup;
  id = 0;

  constructor(public fb: FormBuilder,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
    this.employeeModel = new EmployeeModel();
    this.employeeForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      departmentId: ['', Validators.required]
    })
    this.id = this.route.snapshot.params['id'];
    console.log("id: " + this.id);
    if (this.id != 0) {
      this.update = true
    }

    if (this.update) {
      this.updateEmployee();
    }


    this.departmentService.getDepartmentList().subscribe(
      data => {
        this.departments = data;

      },
      error => {
        console.log(error);
        this.router.navigate(['error']);
      });
  }

  ngOnInit(): void {
    //throw new Error('Method not implemented.');
  }


  updateEmployee() {

    this.employeeService.getEmployee(this.id).subscribe(data => {
      this.employee = data;
      console.log(this.employee);
      // this.employeeForm = this.fb.group({
      //   firstName: [this.employee?.firstName, Validators.required],
      //   lastName: [this.employee?.lastName, Validators.required],
      //   departmentId: [this.employee?.department.id, Validators.required]
      // })
      if (this.employee.firstName != "" && this.employee.lastName != "" && this.employee.department != null) {
        this.employeeForm.controls.departmentId.setValue(this.employee?.department.id);
        this.employeeForm.controls.firstName.setValue(this.employee?.firstName);
        this.employeeForm.controls.lastName.setValue(this.employee.lastName);
      }


    })

  }

  getformControls() { return this.employeeForm.controls; }

  setDepartment(department: Department) {

  }

  onSubmit() {
    if (!this.update) {

      this.employeeModel.firstName = this.employeeForm.controls.firstName.value;
      this.employeeModel.lastName = this.employeeForm.controls.lastName.value;
      this.employeeModel.daprtmentId = parseInt(this.employeeForm.controls.departmentId.value);
      this.employeeService.createEmployee(this.employeeModel).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['employees']);
        },
        error => {
          console.log(error);
          this.router.navigate(['error']);
        });
    } else {

      this.employeeModel.firstName = this.employeeForm.controls.firstName.value;
      this.employeeModel.lastName = this.employeeForm.controls.lastName.value;
      this.employeeModel.daprtmentId = parseInt(this.employeeForm.controls.departmentId.value);
      this.employeeService.updateEmployee(this.id, this.employeeModel).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['employees']);
        },
        error => {
          console.log(error);
          this.router.navigate(['error']);
        });

    }


  }

}
