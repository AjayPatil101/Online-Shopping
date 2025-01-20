package com.example.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.OrderException;
import com.example.Model.Order;
import com.example.Service.OrderService;
import com.example.Response.ApiResponse;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderContoller {
	@Autowired
	private OrderService orderService;

	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrdersHandler() {
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{orderld}/confirmed")
	public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderld,
			@RequestHeader("Authorization") String jwt) throws OrderException {
		Order order = orderService.confirmedOrder(orderld);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PutMapping("/{orderld}/ship")
	public ResponseEntity<Order> ShippedOrderHandler(@PathVariable Long orderld,@RequestHeader("Authorization") String jwt) throws OrderException {
		Order order = orderService.shippedOrder(orderld);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PutMapping("/{orderld}/deliver")
	public ResponseEntity<Order> DeliverOrderHandler(@PathVariable Long orderld,
			@RequestHeader("Authorization") String jwt) throws OrderException {
		Order order = orderService.deliveredOrder(orderld);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PutMapping("/{orderld}/cancel")
	public ResponseEntity<Order> CancelOrderHandler(@PathVariable Long orderld,
			@RequestHeader("Authorization") String jwt) throws OrderException {

		Order order = orderService.cancledOrder(orderld);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@DeleteMapping("/{orderld}/delete")
	public ResponseEntity<ApiResponse> DeleteOrderHandler(@PathVariable Long orderld,
			@RequestHeader("Authorization") String jwt) throws OrderException {
		orderService.deleteOrder(orderld);
		ApiResponse res = new ApiResponse();
		res.setMessage("order deleted successfully");
		res.setStatus(true);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
