package com.products.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.model.GetAllProductsResponse;
import com.products.model.ProductsRequest;
import com.products.model.ProductsResponse;
import com.products.service.ProductsService;

class ProductsControllerTest {
	
	@InjectMocks
	ProductsController controller; 
	
	@Mock
	ProductsService service;
	private static ProductsRequest addProductJson;
	private static ProductsResponse responseProductjson;
	private static GetAllProductsResponse getAllProductsJson;
	
	@BeforeEach
	void set() throws StreamReadException, DatabindException, IOException {
		MockitoAnnotations.openMocks(this);
		ObjectMapper mapper = new ObjectMapper();
		
		addProductJson= mapper.readValue(Paths.get("addProduct.json").toFile(),ProductsRequest.class);
		responseProductjson= mapper.readValue(Paths.get("productResponseJson.json").toFile(),ProductsResponse.class);
		getAllProductsJson= mapper.readValue(Paths.get("getAllProductsJson.json").toFile(),GetAllProductsResponse.class);
		
	}

	@Test
	void testAddProduct() {
		when(service.addProduct(Mockito.any(ProductsRequest.class))).thenReturn(responseProductjson);
		
		ResponseEntity<ProductsResponse> actual=controller.addProduct(addProductJson);
	//	verify(service.addProduct(Mock)));
		assertEquals(new ResponseEntity<ProductsResponse>(responseProductjson,HttpStatus.OK), actual);
	}

	@Test
	void testGetAllProducts() {
		when(service.getAllProducts()).thenReturn(getAllProductsJson); 
		ResponseEntity<GetAllProductsResponse> getAllProducts= controller.getAllProducts();
		//verify(controller).getAllProducts();
		assertEquals(new ResponseEntity<GetAllProductsResponse>(getAllProductsJson,HttpStatus.OK), getAllProducts);
	}

	@Test 
	void testGetProduct() {
		when(service.getProduct(anyInt())).thenReturn(responseProductjson);
		ResponseEntity<ProductsResponse> responseProduct= controller.getProduct(anyInt());
		assertEquals(new ResponseEntity<ProductsResponse>(responseProductjson,HttpStatus.OK), responseProduct);
	}

	@Test
	void testDeleteProduct() {
		controller.deleteProduct(anyInt());
		verify(service).deleteProduct(anyInt());
	}

	@Test
	void testUpdateProduct() {
		when(service.updateProduct(anyInt(), Mockito.any(ProductsRequest.class))).thenReturn(responseProductjson);
		addProductJson.setId(1);
		controller.updateProduct(1, addProductJson);
		verify(service).updateProduct(anyInt(), Mockito.any(ProductsRequest.class));
	}
	
	@Test
	void testValidationExceptionforUpdateProduct() {
		when(service.updateProduct(anyInt(), Mockito.any(ProductsRequest.class))).thenReturn(responseProductjson);
		addProductJson.setManufacturer(null);
		assertThrows(ConstraintViolationException.class, ()->controller.updateProduct(1, addProductJson));
		//verify(service).updateProduct(anyInt(), Mockito.any(ProductsRequest.class));
	}
	@Test
	void testValidationExceptionforAddProduct() {
		when(service.addProduct(Mockito.any(ProductsRequest.class))).thenReturn(responseProductjson);
		addProductJson.setManufacturer(null);
		assertThrows(ConstraintViolationException.class, ()->controller.addProduct( addProductJson));
		//verify(service).updateProduct(anyInt(), Mockito.any(ProductsRequest.class));
	}
}
