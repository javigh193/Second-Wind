package com.secondwind.dto.converter;

import org.springframework.stereotype.Component;

import com.secondwind.dto.OrderDTO;
import com.secondwind.entity.Order;
import com.secondwind.entity.Product;
import com.secondwind.entity.User;

@Component
public class OrderConverter extends BaseConverterImpl<OrderDTO, Order>  {
	
//	@Override
//	public Order dtoToEntity(OrderDTO DTO) {
//		Order order = new Order();
////		product.setTitle(productDTO.getTitle());
////		product.setDescription(productDTO.getDescription());
////		product.setPrice(productDTO.getPrice());
//		return order;	
//	}
	
//	@Override
//	public Order dtoToEntity(OrderDTO orderDTO, Order order) {
//		order.setPrice(orderDTO.getPrice());
////		product.setDescription(productDTO.getDescription());
////		product.setPrice(productDTO.getPrice());
//		return order;	
//	}
	
	@Override
	public OrderDTO entityToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setStatus(order.getStatus());
		orderDTO.setLastUpdatedOn(order.getLastUpdatedOn().toString());
		orderDTO.setCreatedOn(order.getCreatedOn().toString());
		orderDTO.setProductTitle(order.getProduct().getTitle());
		orderDTO.setProductDescription(order.getProduct().getDescription());
		orderDTO.setPrice(order.getPrice());
		orderDTO.setBuyerFirstName(order.getBuyer().getFirstName());
		orderDTO.setBuyerLastName(order.getBuyer().getLastName());
		orderDTO.setBuyerLastName(order.getBuyer().getEmail());
		return orderDTO;
	}
	
	public OrderDTO entitiesToDTO(Order order, User buyer, Product product) {
		OrderDTO orderDTO = new OrderDTO();
		
		orderDTO.setStatus(order.getStatus());
		orderDTO.setPrice(order.getPrice()); 
		orderDTO.setCreatedOn(order.getCreatedOn().toString());
		orderDTO.setLastUpdatedOn(order.getLastUpdatedOn().toString());
		orderDTO.setBuyerFirstName(buyer.getFirstName());
		orderDTO.setBuyerLastName(buyer.getLastName());
		orderDTO.setBuyerEmail(buyer.getEmail());
		orderDTO.setProductTitle(product.getTitle());
		orderDTO.setProductDescription(product.getDescription());		
		
		return orderDTO;
	}
}
