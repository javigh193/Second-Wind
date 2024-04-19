package com.secondwind.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondwind.dto.OrderDTO;
import com.secondwind.dto.converter.OrderConverter;
import com.secondwind.entity.Order;
import com.secondwind.service.impl.OrderServiceImpl;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/orders")
public class OrderController 
	extends BaseController<OrderDTO, Order, OrderConverter, OrderServiceImpl> {
	
}
