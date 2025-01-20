package com.example.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Exception.CartItemException;
import com.example.Exception.UserException;
import com.example.Model.Cart;
import com.example.Model.CartItem;
import com.example.Model.Product;
import com.example.Model.User;
import com.example.Repository.CartRepository;
import com.example.Repository.ProductRepository;
import com.example.Response.ApiResponse;
import com.example.Service.CartItemService;
import com.example.Service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartItemController(CartItemService cartItemService, CartRepository cartRepository,
                              ProductRepository productRepository, UserService userService) {
        this.cartItemService = cartItemService;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCartItem(
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            User user = userService.findUserProfileByJwt(authorizationHeader);
            cartItem.setUserId(user.getId());
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            return ResponseEntity.ok(new ApiResponse("Cart item created successfully", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to create cart item: " + e.getMessage(), false));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCartItem(
            @PathVariable Long id,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            User user = userService.findUserProfileByJwt(authorizationHeader);
            cartItemService.updateCartItem(user.getId(), id, cartItem);
            return ResponseEntity.ok(new ApiResponse("Cart item updated successfully", true));
        } catch (CartItemException | UserException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to update cart item: " + e.getMessage(), false));
        }
    }
    
    @GetMapping("/exists")
    public ResponseEntity<ApiResponse> isCartItemExist(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam String size,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            User user = userService.findUserProfileByJwt(authorizationHeader);
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
            CartItem cartItem = cartItemService.isCartItemExist(cart, product, size, user.getId());
            if (cartItem != null) {
                return ResponseEntity.ok(new ApiResponse("Cart item exists", true));
            } else {
                return ResponseEntity.ok(new ApiResponse("Cart item does not exist", false));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to check cart item existence: " + e.getMessage(), false));
        }
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> removeCartItem(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            User user = userService.findUserProfileByJwt(authorizationHeader);
            cartItemService.removeCartItem(user.getId(), cartItemId);
            return ResponseEntity.ok(new ApiResponse("Cart item removed successfully", true));
        } catch (CartItemException | UserException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to remove cart item: " + e.getMessage(), false));
        }
    }

 // Find a cart item by ID
    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> findCartItemById(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String authorizationHeader) throws UserException {
        try {
            User user = userService.findUserProfileByJwt(authorizationHeader);
            CartItem cartItem = cartItemService.findCartItemById(cartItemId);
            // Optional: Verify that the cart item belongs to the authenticated user
            if (!cartItem.getUserId().equals(user.getId())) {
                return ResponseEntity.status(403).body(null);
            }
            return ResponseEntity.ok(cartItem);
        } catch (CartItemException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}




