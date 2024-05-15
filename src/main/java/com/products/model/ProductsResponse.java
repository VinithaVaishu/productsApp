package com.products.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductsResponse {
	 private int id;
	 private String name;
	 private float price;
	 private String manufacturer;
	 private String description;
	 private int noOfOrders;
	 private String availability;
	 
	 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getNoOfOrders() {
		return noOfOrders;
	}


	public void setNoOfOrders(int noOfOrders) {
		this.noOfOrders = noOfOrders;
	}


	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}


	@Override
	public String toString() {
		return "ProductsResponse [id=" + id + ", name=" + name + ", price=" + price + ", manufacturer=" + manufacturer
				+ ", description=" + description + ", noOfOrders=" + noOfOrders + ", availability=" + availability
				+ "]";
	}
	 
	 
}
