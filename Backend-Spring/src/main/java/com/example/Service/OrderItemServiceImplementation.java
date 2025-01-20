package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Model.OrderItem;
import com.example.Repository.OrderItemRepository;
@Service
public class OrderItemServiceImplementation implements OrderItemService {
	
	public OrderItemRepository orderItemRepository;
	

	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
	}


	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepository.save(orderItem);
	}

}
