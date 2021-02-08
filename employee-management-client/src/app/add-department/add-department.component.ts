import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from '../department.service';

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit {

  departmentForm!: FormGroup;

  constructor(public fb: FormBuilder,
    private departmentService: DepartmentService,
    private route: ActivatedRoute,
    private router: Router,) { }

  ngOnInit(): void {
    this.departmentForm = this.fb.group({
      departmentName: ['', Validators.required]
    })
  }

  onSubmit() {
    this.departmentService.addDepartment(this.departmentForm.value).subscribe(data => {
      console.log(data);
      this.router.navigate(['departments']);
    })

    
  }

}
