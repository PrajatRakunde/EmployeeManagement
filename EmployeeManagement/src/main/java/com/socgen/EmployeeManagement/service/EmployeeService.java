package com.socgen.EmployeeManagement.service;

import java.util.List;

import com.socgen.EmployeeManagement.dto.EmployeeDto;
import com.socgen.EmployeeManagement.model.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();
	
	public Employee getEmployee(int id);
	
//	public Employee getEmployee(String name);
	
	public Employee addEmployee(EmployeeDto e);
	
	public Employee editEmployee(EmployeeDto e, int id);
	
	public void DeleteEmployee(int id);
	
}
