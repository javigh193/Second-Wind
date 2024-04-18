package com.secondwind.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.secondwind.dto.ProductDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.ProductConverter;
import com.secondwind.entity.Product;
import com.secondwind.entity.ProductStatus;
import com.secondwind.entity.User;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.ProductRepository;
import com.secondwind.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductDTO, Long, Product, ProductConverter> {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ProductServiceImpl(BaseRepository<Product, Long> baseRepository, BaseConverter<ProductDTO, Product> baseConverter) {
		super(baseRepository, baseConverter);
	}
	
	@Override
	public ResponseEntity<?> save(@Valid @RequestBody ProductDTO dto) {
		try {
			Optional<User> optUser = userRepository.findByEmail(dto.getSellerEmail());
			if (optUser.isPresent()) {
				Product product = new Product();
				product.setTitle(dto.getTitle());
				product.setDescription(dto.getDescription());
				product.setPrice(dto.getPrice());
				product.setStatus(ProductStatus.ONSALE);
				product.setSeller(optUser.get());
				baseRepository.save(product);
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body(baseConverter.entityToDTO(product));
				}
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Entity not found");
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		}		
	}
	
	public ResponseEntity<?> addSeller(Long userId, Long productId) {
		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isPresent()) {
			Optional<User> optSelelr = userRepository.findById(userId);
			if (optSelelr.isPresent()) {
				User seller = optSelelr.get();
				Product product = optProduct.get();
				if (product.getSeller() != seller) {
					product.setSeller(seller);
					productRepository.save(product);
				}
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Producto actualizado con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}
	
	public ResponseEntity<?> removeSeller(Long productId) {
		Optional<Product> optProduct = productRepository.findById(productId);
		if (optProduct.isPresent()) {
			Product product = optProduct.get();
			product.setSeller(null);
			productRepository.save(product);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Vendedor eliminado con éxito");
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("No hay registros del tipo solicitado");
		}
	}
	
	public ResponseEntity<?> findByStatus(ProductStatus status) {
		List<Product> products = productRepository.findByStatus(status);
		if (products.isEmpty()) {	
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(null);	
		} else {	
			List<ProductDTO> dtos = new ArrayList<>();
			for (Product product : products) {
				ProductDTO dto = baseConverter.entityToDTO(product);
				dtos.add(dto);
			}
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(dtos);
		}
	}
}
