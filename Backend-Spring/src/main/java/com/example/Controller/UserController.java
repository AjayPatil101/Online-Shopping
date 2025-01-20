package com.example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.UserException;
import com.example.Model.User;
import com.example.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to find a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(404).body(null); // Or return a custom error response
        }
    }

    // Endpoint to get user profile by JWT
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        try { 
            User user = userService.findUserProfileByJwt(authorizationHeader);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(404).body(null); // Or return a custom error response
        }
    }
}
