package com.products.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.products.model.GetAllProductsResponse;
import com.products.model.ProductsRequest;
import com.products.model.ProductsResponse;
import com.products.service.ProductsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@Validated

public class ProductsController {
	
	@Autowired
	ProductsService productsService;
	
	public static final Logger log= LogManager.getLogger(ProductsController.class);
	@PostMapping("/addproduct")
	@ApiOperation(value = "Api to add project", response = ProductsResponse.class)
	@ApiResponses(value = {@ApiResponse(code=200,message = "Success"), @ApiResponse(code=200,message = "Success"),@ApiResponse(code=400,message="Bad request")})
	public ResponseEntity<ProductsResponse> addProduct(@RequestBody ProductsRequest product) {
		log.info("add product request is recieved");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ProductsRequest>> violations = validator.validate(product);
		log.debug("started payload validation" );
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		log.debug("payload validation ended");
		ProductsResponse response=productsService.addProduct(product);
		log.info("product added to database successfully");
		return new ResponseEntity<ProductsResponse>(response,HttpStatus.OK);		
	}
	
	@GetMapping("/products")
	@ApiOperation(value = "API to get the list of all products", response = GetAllProductsResponse.class)
	@ApiResponses(value = {@ApiResponse(code=200,message = "Success"),@ApiResponse(code=400,message="Bad request")})
	public ResponseEntity<GetAllProductsResponse> getAllProducts(){
		
		GetAllProductsResponse products= productsService.getAllProducts();	
		return new ResponseEntity<GetAllProductsResponse>(products,HttpStatus.OK);	
	}
	
	@GetMapping("/product/{productId}")
	@ApiOperation(value = "API to get the  product by id", response = ProductsResponse.class)
	@ApiResponses(value = {@ApiResponse(code=200,message = "Success"),@ApiResponse(code=400,message="Bad request")})
	public ResponseEntity<ProductsResponse> getProduct(@Min(value = 1,message="product id must be greater than 0") @PathVariable("productId") int id) {
		ProductsResponse product = null;
		product = productsService.getProduct(id);
		return new ResponseEntity<ProductsResponse>(product,HttpStatus.OK); 
	}
	
	@DeleteMapping("/deleteproduct/{productId}")
	@ApiOperation(value = "API to delete the product by id", response = String.class)
	@ApiResponses(value = {@ApiResponse(code=200,message = "Success"),@ApiResponse(code=400,message="Bad request")})
	public ResponseEntity<String> deleteProduct(@Min(value = 1,message="product id must be greater than 0") @PathVariable("productId") int id) {
		productsService.deleteProduct(id);
		return new ResponseEntity<String>("Product deleted successfully",HttpStatus.OK);		
	}
	
	@RequestMapping(path = "updateproduct/{productId}",method = RequestMethod.PUT)
	@ApiOperation(value = "API to update product by id", response = ProductsResponse.class)
	@ApiResponses(value = {@ApiResponse(code=200,message = "Success"),@ApiResponse(code=400,message="Bad request")})
	public ResponseEntity<ProductsResponse> updateProduct(@Min(value = 1,message="product id must be greater than 0") @PathVariable("productId") int id,@RequestBody ProductsRequest product){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ProductsRequest>> violations = validator.validate(product);
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		} 
		ProductsResponse productresponse=productsService.updateProduct(id,product);
		return new ResponseEntity<ProductsResponse>(productresponse,HttpStatus.OK);
	}
	
}
