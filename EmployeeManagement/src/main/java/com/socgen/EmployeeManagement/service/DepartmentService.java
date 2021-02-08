package com.socgen.EmployeeManagement.service;

import java.util.List;

import com.socgen.EmployeeManagement.model.Department;

public interface DepartmentService {

	public Department addDepartment(Department dept);
	
	public void deleteDepartment(int id);
	
	public List<Department> getDepartments();
	
}
