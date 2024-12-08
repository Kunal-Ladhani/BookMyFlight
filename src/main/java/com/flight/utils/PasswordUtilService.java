package com.flight.utils;

import com.flight.exception.HashingException;
import com.flight.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Slf4j
@Component
public class PasswordUtilService {

	@Value("${security.password.hashing_algorithm}")
	private String passwordHashingAlgorithm;

	@Value("${security.password.iteration_count}")
	private Integer iterationCount;

	@Value("${security.password.key_length}")
	private Integer keyLength;

	public String getHash(String password) throws HashingException {
		try {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);

			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
			SecretKeyFactory factory = SecretKeyFactory.getInstance(passwordHashingAlgorithm);

			byte[] hash = factory.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(hash);
		} catch (Exception e) {
			log.error("Error while hashing: {}", e.getMessage());
			throw new HashingException("Error while generating hash string.");
		}
	}
}
