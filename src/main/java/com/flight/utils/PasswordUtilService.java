package com.flight.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
@Slf4j
public class PasswordUtilService {

	public String hashPassword(String password) throws Exception {
		SecureRandom random = new SecureRandom();
//		byte[] salt = "kunal".getBytes();
		byte[] salt = new byte[16];
		random.nextBytes(salt);

		var iterationCount = 65536;
		var keyLength = 256;
		String HASHING_ALGORITHM = "PBKDF2WithHmacSHA256";

		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
		SecretKeyFactory factory = SecretKeyFactory.getInstance(HASHING_ALGORITHM);

		byte[] hash = factory.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(hash);
	}

}
