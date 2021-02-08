package com.socgen.EmployeeManagement.service;

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

import com.socgen.EmployeeManagement.model.Department;
import com.socgen.EmployeeManagement.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

	@Mock
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	private Department department;

	Optional<Department> mockOptionalDepartment;
	
	List<Department> mockDepartmentList;

	@BeforeEach
	public void init() {
		
		department = new Department("departmentName");
		mockDepartmentList = new ArrayList<Department>();
		mockDepartmentList.add(department);
		mockOptionalDepartment = Optional.of(department);

	}
	
	@Test
    void testAddDepartmentSuccess() {
		
		departmentRepository.save(department);
        Department newDepartment = departmentService.addDepartment(department);
        assertEquals(department.getDepartmentName(), newDepartment.getDepartmentName());
    }
	
	@Test
    void testDeleteDepartmentSuccess() {
        departmentRepository.save(department);
        
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.deleteDepartment(department.getId());

	}
	
	@Test
    void testGetDepartmentSuccess() {
		doReturn(mockDepartmentList).when(departmentRepository).findAll();
        List<Department> departmentList = departmentService.getDepartments();
        assertFalse(mockDepartmentList.isEmpty());
        assertEquals(mockDepartmentList.get(0).getId(), departmentList.get(0).getId());
    }

}
