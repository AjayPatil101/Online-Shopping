package com.example.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.ProdectException;
import com.example.Model.Product;
import com.example.Model.Rating;
import com.example.Model.User;
import com.example.Repository.RatingRepository;
import com.example.Request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {
	private RatingRepository ratingRepository;
	private ProductService productService;

	public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
		super();
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProdectException {
		Product product = productService.findProductById(req.getProductId());
		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreateAt(LocalDate.now());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProdactRating(Long prodactId) {
		
		return ratingRepository.getAllProductsRating(prodactId);
	}

}
