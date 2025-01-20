package com.example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Exception.ProdectException;
import com.example.Model.Product;
import com.example.Request.createProductRequest;
import com.example.Service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AuthProductController {

    private final ProductService productService;

    @Autowired
    public AuthProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody createProductRequest request) {
        Product product = productService.createProduct(request);
        return ResponseEntity.ok(product);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try {
            String message = productService.deleteProduct(id);
            return ResponseEntity.ok(message);
        } catch (ProdectException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Update a product by ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product request) {
        try {
            Product updatedProduct = productService.updateProduct(id, request);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProdectException e) {
            return ResponseEntity.status(404).body(null); // Return custom error response if needed
        }
    }
}
