package com.flight.dao;

import com.flight.model.User;
import com.flight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {

	private final UserRepository userRepository;

	@Autowired
	public UserDao(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Boolean checkUserExistenceByEmailOrMobileNumber(String email, String mobileNumber) {
		return userRepository.existsByEmailOrMobileNumber(email, mobileNumber);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
