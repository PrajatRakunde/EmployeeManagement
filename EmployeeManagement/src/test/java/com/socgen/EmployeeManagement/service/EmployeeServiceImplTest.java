package com.socgen.EmployeeManagement.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.socgen.EmployeeManagement.dto.EmployeeDto;
import com.socgen.EmployeeManagement.model.Department;
import com.socgen.EmployeeManagement.model.Employee;
import com.socgen.EmployeeManagement.repository.DepartmentRepository;
import com.socgen.EmployeeManagement.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private DepartmentRepository departmentRepository;

	@Mock
	private DepartmentServiceImpl departmentService;
	
	Department department;
	EmployeeDto employeeDto;
	Employee employee;
	List<Employee> employeeList;
	
	
	@BeforeEach
	void init() {
		department = new Department("departmentName");
		employeeDto = new EmployeeDto();
		employeeDto.setFirstName("firstName");
		employeeDto.setLastName("lastName");
		employeeDto.setDaprtmentId(department.getId());
		employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), department);
		employeeList = new ArrayList<>();
		employeeList.add(new Employee("firstName1", "lastName1", new Department("departmentName1")));
		employeeList.add(new Employee("firstName2", "lastName2", new Department("departmentName2")));
		
	}
	

	@Test
	void testGetEmployeeSuccess() {
		
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

		Employee persistedEmployee = employeeService.getEmployee(employee.getId());
		
		assertEquals(persistedEmployee.getId(), employee.getId());
	}

	@Test
	void testGetEmployeesSuccess() {
		
		doReturn(employeeList).when(employeeRepository).findAll();
		
		List<Employee> persitedEmployee = employeeService.getEmployees();
		
		assertEquals(employeeList, persitedEmployee);
	}

	@Test
	void testAddEmployeeSuccess() {
				
		when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
		
		Employee employee = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), department);

		employeeRepository.save(employee);
		Employee persistedEmployee = employeeService.addEmployee(employeeDto);
		
		assertNotNull(persistedEmployee);
		assertEquals("firstName",persistedEmployee.getFirstName());
		assertEquals("lastName",persistedEmployee.getLastName());
		assertEquals(department,persistedEmployee.getDepartment());
		assertEquals(employee.getId(),persistedEmployee.getId());
		
	}

	@Test
	void testEditEmployeeSuccess() {
		
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
		employeeRepository.save(employee);
		Employee persistedEmp = employeeService.editEmployee(employeeDto, employee.getId());
		assertEquals(employee.getId(), persistedEmp.getId());
	}


	@Test
	void testDeleteEmployeeSuccess() {
				
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
		employeeRepository.save(employee);
		
		employeeRepository.deleteById(employee.getId());
		employeeService.DeleteEmployee(employee.getId());
	
		
	}

}
