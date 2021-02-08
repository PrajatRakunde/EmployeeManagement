package com.socgen.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socgen.EmployeeManagement.exception.DepartmentBadRequestException;
import com.socgen.EmployeeManagement.exception.DepartmentNotFoundException;
import com.socgen.EmployeeManagement.exception.EmployeeBadRequestException;
import com.socgen.EmployeeManagement.model.Department;
import com.socgen.EmployeeManagement.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	DepartmentRepository repo;

	@Override
	public Department addDepartment(Department dept) {
		try {
			repo.save(dept);
		} catch(DepartmentBadRequestException e) {}
		
		return dept;
	}

	@Override
	public void deleteDepartment(int id) throws DepartmentNotFoundException{
		
		Optional<Department> dept = repo.findById(id);
		
		if(!dept.isPresent()) {
			throw new DepartmentNotFoundException("Department with " + id + " not found");
		}
		
		repo.deleteById(id);
		
		
	}

	@Override
	public List<Department> getDepartments() {

		List<Department> departments = repo.findAll();
		
		if(departments.isEmpty()) {
			
			throw new DepartmentNotFoundException("No departments available");
			
		}
		
		return departments;
		
	}

	
	
}
