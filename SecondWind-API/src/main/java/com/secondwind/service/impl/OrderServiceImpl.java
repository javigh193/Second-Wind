package com.secondwind.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.secondwind.dto.OrderCreationDTO;
import com.secondwind.dto.OrderDTO;
import com.secondwind.dto.converter.BaseConverter;
import com.secondwind.dto.converter.OrderConverter;
import com.secondwind.entity.Order;
import com.secondwind.entity.OrderStatus;
import com.secondwind.entity.Product;
import com.secondwind.entity.ProductStatus;
import com.secondwind.entity.User;
import com.secondwind.repository.BaseRepository;
import com.secondwind.repository.ProductRepository;
import com.secondwind.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderDTO, Long, Order, OrderConverter> {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderConverter orderConverter;
	
	public OrderServiceImpl(BaseRepository<Order, Long> baseRepository, BaseConverter<OrderDTO, Order> baseConverter) {
		super(baseRepository, baseConverter);
	}
	
	@Override
	public ResponseEntity<?> save(@Valid @RequestBody OrderDTO dto) {
		try {
			Optional<Product> optProduct = productRepository.findById(dto.getProductId());
			if (optProduct.isPresent()) {
				Optional<User> optUser = userRepository.findByEmail(dto.getBuyerEmail());
				if (optUser.isPresent()) {
					Product product = optProduct.get();
					User buyer = optUser.get();
					Order order = new Order();
					order.setPrice(product.getPrice());
					order.setStatus(OrderStatus.PENDING);
					order.setBuyer(buyer);
					order.setProduct(product);
					baseRepository.save(order);
					product.setStatus(ProductStatus.ORDERED);
					productRepository.save(product);
					return ResponseEntity
							.status(HttpStatus.CREATED)
							.body(orderConverter.entityToDTO(order));
				}
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
}
