package com.example.Service;

import com.example.Exception.CartItemException;
import com.example.Exception.UserException;
import com.example.Model.Cart;
import com.example.Model.CartItem;
import com.example.Model.Product;

public interface CartItemService {
	public CartItem createCartItem(CartItem cartItem);
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem)throws CartItemException,UserException;
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	public void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	
}
