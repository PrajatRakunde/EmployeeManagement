import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees!: Observable<Employee>[];
  displayedColumns: string[] = ['firstName', 'lastName', 'department', 'delete', 'edit'];
  dataSource!: MatTableDataSource<Employee>;

  constructor(private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.dataSource = new MatTableDataSource();
    this.getEmployees();
  }

  getEmployees() {
    this.employeeService.getEmployeesList().subscribe(
      data => {
        this.dataSource.data = data;
        console.log(this.dataSource.data);
      },
      error => {
        console.log(error);
        this.router.navigate(['error']);
      });
    console.log("in employees");
    console.log(this.employees);

  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id)
      .subscribe(
        data => {
          console.log(data);
          this.getEmployees();
        },
        error => {
          console.log(error);
          this.router.navigate(['error']);
        });
  }

  editEmployee(id: number) {
    //route to routerLink="addEmployee/0"
    this.router.navigate(['addEmployee/', id]);
  }


}
