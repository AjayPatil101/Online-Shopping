package com.example.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Exception.OrderException;
import com.example.Model.Address;
import com.example.Model.Cart;
import com.example.Model.CartItem;
import com.example.Model.Order;
import com.example.Model.OrderItem;
import com.example.Model.User;
import com.example.Repository.AddressRepository;
import com.example.Repository.OrderItemRepository;
import com.example.Repository.OrderRepository;
import com.example.Repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;

	public OrderServiceImplementation(OrderRepository orderRepository, CartService cartService,
			AddressRepository addressRepository, UserRepository userRepository, OrderItemService orderItemService,
			OrderItemRepository orderItemRepository) {
		super();
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.orderItemService = orderItemService;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public Order createOrder(User user, Address shippAddress) {
		shippAddress.setUser(user);
		Address address = addressRepository.save(shippAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			OrderItem createdOrderltem = orderItemRepository.save(orderItem);
			orderItems.add(createdOrderltem);
		}
		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItem(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscounte(cart.getDiscounte());
		createdOrder.setTotalltem(cart.getTotalltem());
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreateAt(LocalDateTime.now());

		Order saveOrder = orderRepository.save(createdOrder);

		for (OrderItem item : orderItems) {
			item.setOrder(saveOrder);
			orderItemRepository.save(item);
		}

		return saveOrder;
	}

	@Override
	public Order findOrderById(Long orderld) throws OrderException {
		Optional<Order> opt = orderRepository.findById(orderld);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("Order not Exist with id : "+orderld); 
	}

	@Override
	public List<Order> usersOrderHistory(Long userld) {
		List<Order> orders = orderRepository.getUserorder(userld);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderld) throws OrderException {
		Order order = findOrderById(orderld);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderld) throws OrderException {
		Order order = findOrderById(orderld);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderld) throws OrderException {
		Order order = findOrderById(orderld);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderld) throws OrderException {
		Order order = findOrderById(orderld);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order cancledOrder(Long orderld) throws OrderException {
		Order order = findOrderById(orderld);
		order.setOrderStatus("CANCLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderld) throws OrderException {
		Order order = findOrderById(orderld);
		orderRepository.delete(order);		
	}

}