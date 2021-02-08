package com.socgen.EmployeeManagement.exception;

public class EmployeeBadRequestException extends RuntimeException {

	public EmployeeBadRequestException() {
		super();
	}

	public EmployeeBadRequestException(String message) {
		super(message);
		
	}
}
