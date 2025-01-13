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
	public ResponseEntity<Page<Product>> findProductByCategoryHandler(
	        @RequestParam String category,
	        @RequestParam List<String> color,
	        @RequestParam List<Size> size,
	        @RequestParam Integer minPrice,
	        @RequestParam Integer maxPrice,
	        @RequestParam Integer minDiscount,
	        @RequestParam String sort,
	        @RequestParam String stock,
	        @RequestParam Integer pageNumber,
	        @RequestParam Integer pageSize) {

	    Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
	    System.out.println("complete products");
	    return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}
	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable("productId") Long productId) throws ProdectException {
	    Product product = productService.findProductById(productId);
	    return new ResponseEntity<>(product, HttpStatus.OK);
	}

}
