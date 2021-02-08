package com.socgen.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.EmployeeManagement.dto.EmployeeDto;
import com.socgen.EmployeeManagement.model.Employee;
import com.socgen.EmployeeManagement.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		
		return employeeService.getEmployees();
		
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		
		return employeeService.getEmployee(id);
		
	}
	
//	public Employee getEmployee(String name);
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody EmployeeDto e) {
		
		return employeeService.addEmployee(e);
		
	}
	
	@PutMapping("/employee/{id}")
	public Employee editEmployee(@RequestBody EmployeeDto e, @PathVariable int id) {
		
		return employeeService.editEmployee(e, id);
		
	}
	
	@DeleteMapping("/employee/{id}")
	public void DeleteEmployee(@PathVariable int id) {
		
		employeeService.DeleteEmployee(id);
		
	}
	
}
