package com.products.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
public class GetAllProductsResponse {
	 private int no_of_records;
	 private int total_no_of_records;
	 
	 public GetAllProductsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNo_of_records() {
		return no_of_records;
	}
	public void setNo_of_records(int no_of_records) {
		this.no_of_records = no_of_records;
	}
	public int getTotal_no_of_records() {
		return total_no_of_records;
	}
	public void setTotal_no_of_records(int total_no_of_records) {
		this.total_no_of_records = total_no_of_records;
	}
	public String getMore_data() {
		return more_data;
	}
	public void setMore_data(String more_data) {
		this.more_data = more_data;
	}
	public List<ProductsResponse> getProducts() {
		return products;
	}
	public void setProducts(List<ProductsResponse> products) {
		this.products = products;
	}
	private String more_data;
	 private List<ProductsResponse> products;

}
