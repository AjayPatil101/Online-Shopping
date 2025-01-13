package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Exception.ProdectException;
import com.example.Model.Product;
import com.example.Model.Review;
import com.example.Model.User;
import com.example.Repository.ProductRepository;
import com.example.Repository.ReviewRepository;
import com.example.Request.ReviewRequest;

public class ReviewServiceImplementation implements ReviewService {
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProdectException {
		Product product = productService.findProductById(req.getProductId());
		Review review = new Review();
		review.setProduct(product);
		review.setUser(user);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		return reviewRepository.save(review);
		}

	@Override
	public List<Review> getProdactReview(Long prodactId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(prodactId);
	}

}
