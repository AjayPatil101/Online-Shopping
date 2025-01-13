package com.example.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="order_id")
	private String orderId;
	
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem>orderItem = new ArrayList<>();
	
	private LocalDateTime orderDate;
	private LocalDateTime deliveryDate;
	
	@OneToOne
	private Address shippingAddress;
	@Embedded
	private PaymentDetails paymentDetails = new PaymentDetails();
	private double totalPrice;
	private Integer totalDiscountedPrice;
	private Integer discounte;
	private String orderStatus;
	private int totalltem;
	private LocalDateTime createAt;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Long id, String orderId, User user, List<OrderItem> orderItem, LocalDateTime orderDate,
			LocalDateTime deliveryDate, Address shippingAddress, PaymentDetails paymentDetails, double totalPrice,
			Integer totalDiscountedPrice, Integer discounte, String orderStatus, int totalltem,
			LocalDateTime createAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.orderItem = orderItem;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.shippingAddress = shippingAddress;
		this.paymentDetails = paymentDetails;
		this.totalPrice = totalPrice;
		this.totalDiscountedPrice = totalDiscountedPrice;
		this.discounte = discounte;
		this.orderStatus = orderStatus;
		this.totalltem = totalltem;
		this.createAt = createAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}
	public void setTotalDiscountedPrice(Integer totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
	}
	public Integer getDiscounte() {
		return discounte;
	}
	public void setDiscounte(Integer discounte) {
		this.discounte = discounte;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getTotalltem() {
		return totalltem;
	}
	public void setTotalltem(int totalltem) {
		this.totalltem = totalltem;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
}
