package com.example.Controller;

import java.util.List;

import org.springframework.data.domain.Page; // Correct import for Spring Data's Page
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.ProdectException;
import com.example.Model.Product;
import com.example.Model.Size;
import com.example.Service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	public ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(required = false) String category,
			@RequestParam(required = false) List<String> color, @RequestParam(required = false,defaultValue = "0") Integer minPrice,
			@RequestParam(required = false) Integer maxPrice, @RequestParam(required = false) Integer minDiscount,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String stock,
			@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(required = false) List<String> size) {
		Page<Product> products = productService.getAllProduct(category, color, minPrice, maxPrice, minDiscount, sort,
				stock, pageNumber, pageSize, size);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable("productId") Long productId)
			throws ProdectException {
		Product product = productService.findProductById(productId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

}
