package com.products.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.products.service.ProductsService;

class ProductsExceptionHandlerTest {
	
	@InjectMocks
	ProductsExceptionHandler productsExceptionHandler;
	
	@Mock 
	ProductsService service;
	
	@Mock
	ErrorMessage errorMessage;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testHandlerProductNotFoundException() {
		
	}

	@Test
	void testHandlerRuntimeException() {
		fail("Not yet implemented");
	}

	@Test
	void testMethodArgumentNotValidExceptionHandler() {
		fail("Not yet implemented");
	}

	@Test
	void testConstraintsViolationExceptionHandler() {
		fail("Not yet implemented");
	}

}
