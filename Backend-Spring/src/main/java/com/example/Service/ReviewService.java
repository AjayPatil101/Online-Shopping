package com.example.Service;

import java.util.List;

import com.example.Exception.ProdectException;
import com.example.Model.Review;
import com.example.Model.User;
import com.example.Request.ReviewRequest;

public interface ReviewService {
	public Review createReview(ReviewRequest req,User user)throws ProdectException;
	public List<Review> getProdactReview(Long prodactId);
}
