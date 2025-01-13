package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Exception.ProdectException;
import com.example.Model.Cart;
import com.example.Model.CartItem;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Repository.CartRepository;
import com.example.Request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;

	public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,
			ProductService productService) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		cartRepository.save(cart);
		return cart;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProdectException {
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		CartItem isPesent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);

		if (isPesent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);

			int price = req.getQuantity() * product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());

			CartItem createCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createCartItem);
		}

		return "Item Add To Cart...";
	}

	@Override
	public Cart findUserCart(Long usreId) {
		Cart cart = cartRepository.findByUserId(usreId);
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		for (CartItem cartItem : cart.getCartItems()) {
			totalPrice = totalPrice + cartItem.getPrice();
			totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
			totalItem = totalItem + cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalltem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscounte(totalPrice-totalDiscountedPrice);
		
		return cartRepository.save(cart);
	}

}
