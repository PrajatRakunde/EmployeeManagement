import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = 'http://localhost:8082/';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'*' })
  };

  constructor(private http: HttpClient) {
    
   }

  getEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/employee/${id}`);
  }

  createEmployee(employee: Object): Observable<Object> {
    console.log("inside employeeService post");
    //JSON.stringify(employee);
    console.log(employee);
    console.log(JSON.stringify(employee));
    return this.http.post(`${this.baseUrl}/employee`, employee);
  }

  updateEmployee(id: number, employee: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/employee/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/employee/${id}`);
  }

  getEmployeesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/employees`);
  }
}
