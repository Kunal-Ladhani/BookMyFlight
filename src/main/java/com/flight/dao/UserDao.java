package com.flight.dao;

import com.flight.constants.ExceptionCode;
import com.flight.exception.HashingException;
import com.flight.exception.ResourceNotExistsException;
import com.flight.model.User;
import com.flight.repository.UserRepository;
import com.flight.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private final UserRepository userRepository;
	private final CryptoUtils cryptoUtils;

	@Autowired
	public UserDao(UserRepository userRepository, CryptoUtils cryptoUtils) {
		this.userRepository = userRepository;
		this.cryptoUtils = cryptoUtils;
	}

	public Boolean checkUserExistenceByEmailOrMobileNumber(String email, String mobileNumber) {
		return userRepository.existsByEmailHashOrMobileNumber(email, mobileNumber);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User findByEmail(String email) throws ResourceNotExistsException, HashingException {
		var emailHash = cryptoUtils.getHash(email);
		return userRepository.findByEmailHash(emailHash)
				.orElseThrow(() -> new ResourceNotExistsException(ExceptionCode.U102, "User does not exists for email: " + email));
	}

}
