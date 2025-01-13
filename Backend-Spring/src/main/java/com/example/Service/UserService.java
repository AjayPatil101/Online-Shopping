package com.example.Service;

import com.example.Exception.UserException;
import com.example.Model.User;

public interface UserService {
	public User findUserById(Long userId)throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
}
