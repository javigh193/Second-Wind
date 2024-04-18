package com.secondwind.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.ProductDTO;
import com.secondwind.dto.converter.ProductConverter;
import com.secondwind.entity.Product;
import com.secondwind.entity.ProductStatus;
import com.secondwind.service.impl.ProductServiceImpl;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/products")
public class ProductController 
	extends BaseController<ProductDTO, Product, ProductConverter, ProductServiceImpl> {
	
	@GetMapping("/forsale")
	public ResponseEntity<?>
		getAllForSale(){
			return service.findByStatus(ProductStatus.ONSALE);
	}
	
	@PutMapping("/{productId}/add-seller")
	public ResponseEntity<?> 
		addSeller(@PathVariable Long productId,  
				 @RequestParam @Positive @NotNull Long sellerId) {
			return service.addSeller(productId, sellerId);
	}
	
	@PutMapping("/{productId}/remove-seller")
	public ResponseEntity<?> 
		removeSeller(@PathVariable Long productId) {
			return service.removeSeller(productId);
	}
	
}