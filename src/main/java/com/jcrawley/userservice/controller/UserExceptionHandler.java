package com.jcrawley.userservice.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserExceptionHandler {



	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<String> constraintException( ConstraintViolationException ex) {
		System.out.println("Exception: Constraint Violation Exception!");
		/*
		ex.getConstraintViolations().forEach(violation -> {
			violation.getMessage();
		});
		*/
	  
	      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> constraintException( MethodArgumentNotValidException ex) {
		System.out.println("Exception: Bean Violation Exception!");
	
	    return new ResponseEntity<>(ex.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}
	
	
}
