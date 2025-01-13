package com.example.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Exception.CartItemException;
import com.example.Exception.UserException;
import com.example.Model.Cart;
import com.example.Model.CartItem;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Repository.CartItemRepository;
import com.example.Repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {
	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;

	public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService,
			CartRepository cartRepository) {
		super();
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
		CartItem createCartitem = cartItemRepository.save(cartItem);
		return createCartitem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item = findCartItemById(id);
		User user = userService.findUserById(item.getId());
		if (user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity() * item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
		}
		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartitem = cartItemRepository.isCartItemExist(cart, product, size, userId);
		return cartitem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemById(cartItemId);
		User user = userService.findUserById(cartItem.getUserId());
		User reqUser = userService.findUserById(userId);
		if (user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		} else {

			throw new UserException("you Can't Remove Another User item...");
		}
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
		if (opt.isPresent()) {
			return opt.get();

		}
		throw new CartItemException("CartItem Not Fount with Id : " + cartItemId);
	}

}
