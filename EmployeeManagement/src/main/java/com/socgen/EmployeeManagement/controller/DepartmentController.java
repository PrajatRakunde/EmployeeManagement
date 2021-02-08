package com.socgen.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.EmployeeManagement.model.Department;
import com.socgen.EmployeeManagement.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService deptService;
	
	@PostMapping("/department")
	public ResponseEntity<Department> addDepartment(@RequestBody Department dept) {
		
		Department department = deptService.addDepartment(dept);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(department);
	}
	
	@DeleteMapping("/department/{id}")
	public void deleteDepartment(@PathVariable int id) {
		deptService.deleteDepartment(id);
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartments() {
		return deptService.getDepartments();
	}
	
	
	
}
