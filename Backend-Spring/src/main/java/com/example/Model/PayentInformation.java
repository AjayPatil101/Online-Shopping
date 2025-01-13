package com.example.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class PayentInformation {
	@Column(name="cartholder_name")
	private String cartholderName;
	
	@Column(name="cart_number")
	private String cartNumber;
	
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	private String cvv;

	public PayentInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayentInformation(String cartholderName, String cartNumber, LocalDate expirationDate, String cvv) {
		super();
		this.cartholderName = cartholderName;
		this.cartNumber = cartNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}

	public String getCartholderName() {
		return cartholderName;
	}

	public void setCartholderName(String cartholderName) {
		this.cartholderName = cartholderName;
	}

	public String getCartNumber() {
		return cartNumber;
	}

	public void setCartNumber(String cartNumber) {
		this.cartNumber = cartNumber;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "PayentInformation [cartholderName=" + cartholderName + ", cartNumber=" + cartNumber
				+ ", expirationDate=" + expirationDate + ", cvv=" + cvv + "]";
	}
	
	
	
}
