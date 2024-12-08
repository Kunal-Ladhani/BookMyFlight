package com.flight.dao;

import com.flight.constants.ExceptionCode;
import com.flight.exception.ResourceNotExistsException;
import com.flight.model.User;
import com.flight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public User findByEmail(String email) throws ResourceNotExistsException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotExistsException(ExceptionCode.U102, "User does not exists for email: " + email));
	}

}
