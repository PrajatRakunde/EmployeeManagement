import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseUrl = 'http://localhost:8082/';

  constructor(private http: HttpClient) {
    
   }

  addDepartment(dept: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/department`, dept);
  }

  deleteDepartment(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/department/${id}`);
  }

  getDepartmentList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/departments`);
  }
}
