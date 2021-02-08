package com.socgen.EmployeeManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter @Setter @NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;

	public Employee(String firstName, String lastName, @NotNull Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}
	
	
	
}
