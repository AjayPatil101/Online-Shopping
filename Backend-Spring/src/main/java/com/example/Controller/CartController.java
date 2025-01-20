package com.example.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.ProdectException;
import com.example.Exception.UserException;
import com.example.Model.Cart;
import com.example.Model.User;
import com.example.Request.AddItemRequest;
import com.example.Response.ApiResponse;
import com.example.Service.CartService;
import com.example.Service.UserService;


@RestController
@RequestMapping("/api/cart")
public class CartController {


    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    // Create a cart for a user
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCart(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Extract user from JWT token
            User user = userService.findUserProfileByJwt(authorizationHeader);
            Cart createdCart = cartService.createCart(user);
            return ResponseEntity.ok(new ApiResponse("Cart created successfully", true));
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to create cart: " + e.getMessage(), false));
        }
    }

    // Add an item to the cart
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCartItem(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddItemRequest request) {
        try {
            // Extract user from JWT token
            User user = userService.findUserProfileByJwt(authorizationHeader);
            String message = cartService.addCartItem(user.getId(), request);
            return ResponseEntity.ok(new ApiResponse(message, true));
        } catch (ProdectException | UserException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Failed to add item to cart: " + e.getMessage(), false));
        }
    }

    // Get the cart for a user
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Cart getCart(@RequestHeader("Authorization") String authorizationHeader) throws UserException {
        // Extract user from JWT token
        User user = userService.findUserProfileByJwt(authorizationHeader);
        return cartService.findUserCart(user.getId());
    }
}
