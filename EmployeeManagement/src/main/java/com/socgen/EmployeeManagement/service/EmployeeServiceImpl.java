package com.socgen.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socgen.EmployeeManagement.dto.EmployeeDto;
import com.socgen.EmployeeManagement.exception.DepartmentNotFoundException;
import com.socgen.EmployeeManagement.exception.EmployeeBadRequestException;
import com.socgen.EmployeeManagement.exception.EmployeeNotFoundException;
import com.socgen.EmployeeManagement.model.Department;
import com.socgen.EmployeeManagement.model.Employee;
import com.socgen.EmployeeManagement.repository.DepartmentRepository;
import com.socgen.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public List<Employee> getEmployees() throws EmployeeNotFoundException{
		List<Employee> employees = employeeRepository.findAll();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employees present");
		}
		
		return employees;
	}

	@Override
	public Employee getEmployee(int id) throws EmployeeNotFoundException{

		Optional<Employee> employee = employeeRepository.findById(id);
		if(!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee with " + id +" is not found");
		}
		return employee.get();
		
	}


	@Override
	public Employee addEmployee(EmployeeDto e) throws DepartmentNotFoundException{
		
		Optional<Department> dept = departmentRepository.findById(e.getDaprtmentId());
		Employee employee = new Employee(e.getFirstName(), e.getLastName(), dept.get());
		if(!dept.isPresent()) {
			throw new DepartmentNotFoundException("Department with " + e.getDaprtmentId() +" is not found");
		}
		
		try {	
			employeeRepository.save(employee);
		} 
		catch (EmployeeBadRequestException empExc) {}
		catch (Exception e2) {}
		
		return employee;
		
	}

	@Override
	public Employee editEmployee(EmployeeDto e, int id) {
		
		Optional<Employee> employee = employeeRepository.findById(id);
		Optional<Department> department = departmentRepository.findById(e.getDaprtmentId());
		
		if(!employee.isPresent()) {
			throw new EmployeeNotFoundException("Employee with " + id +" is not found");
		}
		
		Employee newEmployee = new Employee();
		newEmployee.setId(employee.get().getId());
		newEmployee.setDepartment(department.get());
		newEmployee.setFirstName(e.getFirstName());
		newEmployee.setLastName(e.getLastName());
		try {
			employeeRepository.save(newEmployee);
		}
		catch (EmployeeBadRequestException empExc) {}
		catch (Exception e2) {}
		
		return newEmployee;
		
	}

	@Override
	public void DeleteEmployee(int id) {
		
		Optional<Employee> e = employeeRepository.findById(id); 	
		
		if(!e.isPresent()) {
			throw new EmployeeNotFoundException("Employee with " + id +" is not found");
		}
		
		try {
			employeeRepository.deleteById(id);
		}
		catch (EmployeeBadRequestException empExc) {}
		catch (Exception e2) {}
	
	}
	
}
