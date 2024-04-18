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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/products")
public class ProductController 
	extends BaseController<ProductDTO, Product, ProductConverter, ProductServiceImpl> {
	
	@Operation(summary = "Get the products available for sale", description = "Returns all the products with the state 'ONSALE'")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
	        @ApiResponse(responseCode = "404", description = "Not found - There where no products available for sale")
	    })
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