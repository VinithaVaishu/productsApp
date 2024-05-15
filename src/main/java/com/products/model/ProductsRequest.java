package com.products.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Component
public class ProductsRequest {
 
 private int id;

 @NotNull(message = "name field is required")
 @JsonProperty("name")
 private String name;

 @JsonProperty("price")
 @NotNull(message="price field is required")
 //@NotEmpty(message="price field is required")
 private float price;

 @JsonProperty("manufacturer")
 @NotNull(message="manufacturer field is required")
 private String manufacturer;

 @JsonProperty("description")
 @NotNull(message="description field is required")
 private String description;
 
 
 public ProductsRequest() {
	super();
	// TODO Auto-generated constructor stub
}







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



@Override
	public String toString() {
		return "ProductsRequest [name=" + name + ", price=" + price + ", manufacturer=" + manufacturer
				+ ", description=" + description + "]";
	}
 
}
