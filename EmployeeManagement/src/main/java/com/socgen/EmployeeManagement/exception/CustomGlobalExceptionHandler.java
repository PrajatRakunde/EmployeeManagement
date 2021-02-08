package com.socgen.EmployeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	ResponseEntity<Object> exception(EmployeeNotFoundException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	ResponseEntity<Object> exception(DepartmentNotFoundException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeBadRequestException.class)
	ResponseEntity<Object> exception(EmployeeBadRequestException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DepartmentBadRequestException.class)
	ResponseEntity<Object> exception(DepartmentBadRequestException exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<Object> exception(Exception exception) {
		
		 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
