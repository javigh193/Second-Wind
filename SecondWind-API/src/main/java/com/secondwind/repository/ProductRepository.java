package com.secondwind.repository;

import java.util.List;

import com.secondwind.entity.Product;
import com.secondwind.entity.ProductStatus;

public interface ProductRepository extends BaseRepository<Product, Long> {
	
	List<Product> findByStatus(ProductStatus status);
}
