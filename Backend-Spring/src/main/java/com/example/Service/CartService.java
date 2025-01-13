package com.example.Service;

import com.example.Exception.ProdectException;
import com.example.Model.Cart;
import com.example.Model.User;
import com.example.Request.AddItemRequest;

public interface CartService {
	public Cart createCart(User user);
	public String addCartItem(Long userId,AddItemRequest req) throws ProdectException;
	public Cart findUserCart(Long usreId);
	
}
