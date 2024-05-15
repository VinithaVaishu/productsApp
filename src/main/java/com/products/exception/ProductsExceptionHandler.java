package com.products.exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.products.model.ProductsRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ProductsExceptionHandler {
	
	@Autowired
	ErrorMessage errorMessage;
	 
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handler(ProductNotFoundException ex){
		
		List<String> errors= new ArrayList<String>();
		errors.add(ex.getMessage());
		errorMessage.setErroMmessages(errors);
		errorMessage.setTimeStamp(System.currentTimeMillis());
		errorMessage.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handler(RuntimeException ex){		
		List<String> errors= new ArrayList<String>();
		errors.add(ex.getMessage());
		errorMessage.setErroMmessages(errors);
		errorMessage.setTimeStamp(System.currentTimeMillis());
		errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		List<String> errors= new ArrayList<String>();
		errors.add( ex.getBindingResult().getFieldError().getDefaultMessage());
		errorMessage.setErroMmessages(errors);
		errorMessage.setTimeStamp(System.currentTimeMillis());
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> constraintsViolationExceptionHandler(ConstraintViolationException ex){
		
		Set<ConstraintViolation<?>> violations=ex.getConstraintViolations();
		errorMessage.setErroMmessages(violations.stream().map(e->e.getMessage()).collect(Collectors.toList()));
		errorMessage.setTimeStamp(System.currentTimeMillis());
		errorMessage.setStatus(HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
		
	}
}
