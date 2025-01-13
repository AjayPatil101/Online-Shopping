package com.example.Service;

import java.util.List;

import com.example.Exception.ProdectException;
import com.example.Model.Rating;
import com.example.Model.User;
import com.example.Request.RatingRequest;

public interface RatingService {
	public Rating createRating(RatingRequest req,User user)throws ProdectException;
	public List<Rating> getProdactRating(Long prodactId);
	
}
