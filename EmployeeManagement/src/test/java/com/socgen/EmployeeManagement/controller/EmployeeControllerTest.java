package com.socgen.EmployeeManagement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socgen.EmployeeManagement.dto.EmployeeDto;
import com.socgen.EmployeeManagement.model.Department;
import com.socgen.EmployeeManagement.model.Employee;
import com.socgen.EmployeeManagement.repository.DepartmentRepository;
import com.socgen.EmployeeManagement.repository.EmployeeRepository;
import com.socgen.EmployeeManagement.service.DepartmentServiceImpl;
import com.socgen.EmployeeManagement.service.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value= EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private EmployeeServiceImpl employeeService;
	
	@MockBean
	private DepartmentServiceImpl departmentService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private DepartmentRepository departmentRepository;
	
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
		employee.setId(10);
		employeeList = new ArrayList<>();
		employeeList.add(employee);
//		employeeList.add(new Employee("firstName2", "lastName2", new Department("departmentName2")));
		
	}
	
	@Test
	void getEmployeesTest() throws Exception {
		
		doReturn(employeeList).when(employeeService).getEmployees();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees");
		mockMvc.perform(request)
			.andExpect(status().is(200))
			.andExpect(content().json("[{'id':" + employee.getId() + ",'firstName': 'firstName','lastName': 'lastName', "
					+ "'department':{'id':" + department.getId() + ", 'departmentName' : 'departmentName'}}]"));
		
	}
	
	@Test
	void getEmployeeByIdTest() throws Exception {
		
		when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
		employeeRepository.save(employee);
		RequestBuilder request = MockMvcRequestBuilders.get("/employee/" + employee.getId());
		mockMvc.perform(request)
			.andExpect(status().is(200))
			.andExpect(jsonPath("id", is(employee.getId())))
			.andExpect(jsonPath("firstName", is("firstName")))
			.andExpect(jsonPath("lastName", is("lastName")));
		
	}
	
	@Test
	void addEmployeeTest() throws Exception {

		when(employeeService.addEmployee(employeeDto)).thenReturn(employee);
		employeeRepository.save(employee);
		RequestBuilder request = MockMvcRequestBuilders.post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeDto));
		
		mockMvc.perform(request)
			.andExpect(status().is(200));		
		
	}
	
	@Test
	void editEmployeeTest() throws Exception {
		
		when(employeeService.editEmployee(employeeDto, employee.getId())).thenReturn(employee);
		employeeRepository.save(employee);
		RequestBuilder request = MockMvcRequestBuilders.put("/employee/" + employee.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeDto));
		
		mockMvc.perform(request)
			.andExpect(status().is(200));		
		
	}
	
	@Test
	void deleteEmployeeTest() throws Exception {
		
		employeeRepository.save(employee);
		RequestBuilder request = MockMvcRequestBuilders.delete("/employee/" + employee.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(employeeDto));
		
		mockMvc.perform(request)
			.andExpect(status().is(200));		
		
	}


}
