package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.model.ProductModel;

@Repository
public interface ProductsRepository extends JpaRepository<ProductModel, Integer> {

}
