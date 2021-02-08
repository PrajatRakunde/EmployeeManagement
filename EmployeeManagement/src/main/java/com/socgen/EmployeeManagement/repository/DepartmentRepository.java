package com.socgen.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socgen.EmployeeManagement.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
