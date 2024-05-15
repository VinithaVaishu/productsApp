package com.products.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.exception.ProductNotFoundException;
import com.products.model.GetAllProductsResponse;
import com.products.model.ProductModel;
import com.products.model.ProductsRequest;
import com.products.model.ProductsResponse;
import com.products.repository.ProductsRepository;

@Service
public class ProductsService {
	@Autowired
	ProductModel model;
	@Autowired
	ProductsRepository productRepo;
	@org.springframework.transaction.annotation.Transactional()
	public ProductsResponse addProduct(ProductsRequest product) {
		// create product model object
		// map product request to product model in db//save product model to db // send the model response to controller
		model= mapProduct(product);
		ProductModel response= productRepo.save(model);
		ProductsResponse productResponse= mapResponse(response);
		return productResponse ;
	}
	
	public GetAllProductsResponse getAllProducts() {
		List<ProductModel> products= productRepo.findAll();
		GetAllProductsResponse allProducts=new GetAllProductsResponse();
		allProducts.setProducts(products.stream().map(p-> mapResponse(p)).collect(Collectors.toList()));
		allProducts.setNo_of_records(products.size());
		allProducts.setTotal_no_of_records(products.size());
		allProducts.setMore_data("No");
		return allProducts;
	}
	
	public ProductsResponse getProduct(int id){
		Optional<ProductModel> response=productRepo.findById(id);
		if(!response.isPresent())
			throw new ProductNotFoundException("Product Not found with the provided id "+id);
		ProductsResponse product= mapResponse(response.get());
		System.out.println(response.isPresent());
		return product;
	}
	 
	public void deleteProduct(int id) {
		// Handle emptyresultexception if entry is not found
		productRepo.deleteById(id);
	}
	
	public ProductsResponse updateProduct(int id, ProductsRequest product) {
		if(id!=product.getId() ||!productRepo.existsById(id)) {
			throw new ProductNotFoundException("provided product id is invalid");
			}
		
		model= mapProduct(product);
		ProductModel response= productRepo.save(model);
		ProductsResponse productResponse= mapResponse(response);
		
		return productResponse;
		
	}
	
	private ProductsResponse mapResponse(ProductModel response) {
		ProductsResponse prodRes= new ProductsResponse();
		prodRes.setId(response.getID());
		prodRes.setName(response.getNAME());
		prodRes.setPrice(response.getCOST());
		prodRes.setManufacturer(response.getMANUFACTURER());
		prodRes.setDescription(response.getDESCRIPTION());
		prodRes.setNoOfOrders(response.getNO_OF_ORDERS());
		prodRes.setAvailability(response.getAVAILABILITY());
		return prodRes;
	}
	private ProductModel mapProduct(ProductsRequest product) {
		ProductModel pm= new ProductModel();
		if (product.getId()!=0) {
			pm.setID(product.getId());
		}
		pm.setNAME(product.getName());
		pm.setCOST(product.getPrice());
		pm.setDESCRIPTION(product.getDescription());
		pm.setMANUFACTURER(product.getManufacturer());
		pm.setAVAILABILITY("Avaialable");
		pm.setNO_OF_ORDERS(0);
		return pm;
	}

	

	

	
}
