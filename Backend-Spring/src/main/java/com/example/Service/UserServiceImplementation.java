package com.example.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Config.JwtProvider;
import com.example.Exception.UserException;
import com.example.Model.User;
import com.example.Repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService{
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User>user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("User Not Found With Id : "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFormToken(jwt);
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new UserException("User Not Found With Email : "+email);
		}
		return user;
	}

}
