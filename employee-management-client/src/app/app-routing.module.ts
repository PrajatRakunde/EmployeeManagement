import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddDepartmentComponent } from './add-department/add-department.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { DepartmentListComponent } from './department-list/department-list.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { ErrorPageComponent } from './error-page/error-page.component';

const routes: Routes = [
  { path: 'addEmployee/:id', component: AddEmployeeComponent },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'departments', component: DepartmentListComponent },
  { path: 'addDepartment', component: AddDepartmentComponent },
  { path: 'error', component: ErrorPageComponent },
  { path: '', redirectTo: 'employees', pathMatch: 'full'},
  { path: '**', component: EmployeeListComponent }


  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
