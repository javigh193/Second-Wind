package com.secondwind;

import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.secondwind.dto.ProductDTO;
import com.secondwind.entity.Product;
import com.secondwind.repository.ProductRepository;
import com.secondwind.repository.UserRepository;
import com.secondwind.service.impl.ProductServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private ProductServiceImpl productService;
	
	//No funciona, me da problemas mockear el servicio, pendiente de revisar.
	@Test
	public void ProductService_CreateProduct_ReturnsResponseEntity() {
		ProductDTO dto = new ProductDTO();
		dto.setTitle("Test title");
		dto.setDescription("Test description");
		dto.setPrice((float)200.50);
		dto.setCreatedOn("2024-04-18 14:04:01.544080");
		dto.setLastUpdatedOn("2024-04-18 14:04:01.544080");
		
		Product product = new Product();
		product.setTitle("Test title");
		product.setDescription("Test description");
		product.setPrice((float)200.50);
		product.setCreatedOn(Instant.now());
		product.setLastUpdatedOn(Instant.now());		
		
		ResponseEntity<?> expectedResponseEntity = ResponseEntity
				.status(HttpStatus.CREATED)
				.body(dto);			
		
		when(productRepository.save(Mockito.any(Product.class)))
			.thenReturn(product);
		
		ResponseEntity<?> actualResponseEntity = productService.save(dto);
		
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}
}
