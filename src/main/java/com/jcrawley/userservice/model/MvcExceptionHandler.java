package com.jcrawley.userservice.model;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.exception.ConstraintViolationException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

// doesn't work, can't resolve ConstraintViolationException!
//@ControllerAdvice
public class MvcExceptionHandler{

    public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex){
            List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());
            ex.getConstraintViolations().forEach(error -> errorsList.add(error.toString()));
            return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);        
    }

}
