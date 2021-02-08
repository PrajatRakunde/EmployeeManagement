import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Department } from '../department';
import { DepartmentService } from '../department.service';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit {

  displayedColumns: string[] = ['departmentId', 'departmentName'];
  dataSource!: MatTableDataSource<Department>;

  constructor(private departmentService: DepartmentService, private router: Router) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource();
    this.getDepartments();
  }

  getDepartments() {

    this.departmentService.getDepartmentList().subscribe(
      data => {
        this.dataSource.data = data;
      }
      ,
      error => {
        console.log(error);
        this.router.navigate(['error']);
      });

  }

}
