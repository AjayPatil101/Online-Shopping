package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.OrderException;
import com.example.Model.Address;
import com.example.Model.Order;
import com.example.Model.User;
import com.example.Repository.CartRepository;
@Service
public class OrderServiceImplementation implements OrderService {

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	
	public OrderServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,
			ProductService productService) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> usersOrderHistory(Long userld) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placedOrder(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancledOrder(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderld) throws OrderException {
		// TODO Auto-generated method stub
		
	}

}
