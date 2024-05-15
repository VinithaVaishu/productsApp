package com.products.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.products.exception.ProductNotFoundException;
import com.products.model.GetAllProductsResponse;
import com.products.model.ProductModel;
import com.products.model.ProductsRequest;
import com.products.model.ProductsResponse;
import com.products.repository.ProductsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProductsServiceTest {

	@InjectMocks
	public ProductsService service;

	@Mock
	ProductsRepository repo;

	@BeforeEach
	void set() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testUpdateProduct() {
		
		ProductsRequest request = new ProductsRequest();
		request.setId(1);
		request.setName("TV");
		request.setDescription("MI 44 inch 4k resolution TV");
		request.setManufacturer("MI");
		request.setPrice(19000);
		ProductModel model = new ProductModel();
		model.setID(1);
		model.setNAME("TV");
		model.setMANUFACTURER("MI");
		model.setDESCRIPTION("MI 44 inch 4k resolution TV");
		model.setCOST(35000);
		model.setAVAILABILITY("yes");
		model.setNO_OF_ORDERS(1000);
		when(repo.save(Mockito.any(ProductModel.class))).thenReturn(model);
		when(repo.existsById(anyInt())).thenReturn(true);
		ProductsResponse response = service.updateProduct(1, request);
		assertEquals(1,response.getId());
		assertNotEquals(request.getPrice(),response.getPrice());
	}
	
	@Test
	void testGetProduct() {

		ProductModel model = new ProductModel();
		model.setID(1);
		model.setNAME("TV");
		model.setMANUFACTURER("MI");
		model.setDESCRIPTION("MI 44 inch 4k resolution TV");
		model.setCOST(35000);
		model.setAVAILABILITY("yes");
		model.setNO_OF_ORDERS(1000);
		when(repo.findById(1)).thenReturn(Optional.of(model));
		ProductsResponse response = service.getProduct(1);
		assertEquals(1, response.getId());
		assertEquals("TV" ,response.getName());
	}
	
	
	@Test
	void testAddProduct() {
	ProductsRequest request = new ProductsRequest();
	request.setName("refridgerator");
	request.setDescription("Whirlpool 5 start refredgirator");
	request.setManufacturer("whirlpool");
	request.setPrice(19000);
	ProductModel model = new ProductModel();
	model.setID(1);
	model.setNAME("TV");
	model.setMANUFACTURER("MI");
	model.setDESCRIPTION("MI 44 inch 4k resolution TV");
	model.setCOST(35000);
	model.setAVAILABILITY("yes");
	model.setNO_OF_ORDERS(1000);
	when(repo.save(Mockito.any(ProductModel.class))).thenReturn(model);
	ProductsResponse response = service.addProduct(request);
		assertNotNull(response.getId());
		
	}
	
	@Test
	void testGetAllProducts() {
	List<ProductModel> products= new ArrayList<ProductModel>();
	
	ProductModel product1= new ProductModel(1,"TV",28000,"MI","MI 42inches 4K resolution",2000,"yes");
	
	ProductModel product2= new ProductModel(1,"TV",28000,"MI","MI 42inches 4K resolution",2000,"yes");
	products.add(product2);
	products.add(product1);
	when(repo.findAll()).thenReturn(products);
	GetAllProductsResponse response = service.getAllProducts();
	assertNotNull(response.getTotal_no_of_records());
	assertNotNull(response.getProducts());
	assertNotNull(response.getMore_data());
	assertNotNull(response.getProducts());
	assertNotNull(response.getNo_of_records());
	
	}
	@Test
	void testDeleteProduct() {
		service.deleteProduct(1);
		verify(repo).deleteById(anyInt());
	}
	
	@Test
	void testGetProductwithInvalidId() {
	assertThrows(ProductNotFoundException.class,()->service.getProduct(0));	
	}
	
	@Test
	void testUpdateProductwithInvalidId() {
		ProductsRequest request=new ProductsRequest();
		request.setId(1);
	assertThrows(ProductNotFoundException.class,()->service.updateProduct(0, request));	
	}

}
