package com.socgen.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socgen.EmployeeManagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	public Employee findByFirstName(String firstName); 
	
}
