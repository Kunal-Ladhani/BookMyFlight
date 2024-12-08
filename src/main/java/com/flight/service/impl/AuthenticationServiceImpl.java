package com.flight.service.impl;

import com.flight.dao.SessionDao;
import com.flight.dao.UserDao;
import com.flight.dto.LoginRequestDto;
import com.flight.dto.LoginResponseDto;
import com.flight.dto.LogoutResponseDto;
import com.flight.dto.request.SignUpRequestDto;
import com.flight.dto.response.SignUpResponseDto;
import com.flight.enums.UserType;
import com.flight.exception.HashingException;
import com.flight.exception.InvalidCredentialException;
import com.flight.exception.ResourceAlreadyExistsException;
import com.flight.exception.ResourceNotExistsException;
import com.flight.model.Session;
import com.flight.model.User;
import com.flight.service.AuthenticationService;
import com.flight.utils.PasswordUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserDao userDao;
	private final SessionDao sessionDao;
	private final PasswordUtilService passwordUtilService;

	public AuthenticationServiceImpl(UserDao userDao,
									 SessionDao sessionDao,
									 PasswordUtilService passwordUtilService) {
		this.userDao = userDao;
		this.sessionDao = sessionDao;
		this.passwordUtilService = passwordUtilService;
	}

	private SignUpResponseDto handleSavedUser(String userId) {
		return SignUpResponseDto.builder()
				.timestamp(ZonedDateTime.now())
				.message("sign up successful")
				.userId(userId)
				.build();
	}

	@Override
	public SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) throws ResourceAlreadyExistsException, HashingException {
		try {
			log.info("Sign up process started for: {}", signUpRequestDto);
			if (userDao.checkUserExistenceByEmailOrMobileNumber(signUpRequestDto.getEmail(), signUpRequestDto.getMobileNumber())) {
				throw new ResourceAlreadyExistsException(String.format("User already exists with email: %s or mobile number: %s", signUpRequestDto.getEmail(), signUpRequestDto.getMobileNumber()));
			}
			String hashedPassword = passwordUtilService.getHash(signUpRequestDto.getPassword());
			String hashedEmail = passwordUtilService.getHash(signUpRequestDto.getEmail());
			User newUser = User.builder()
					.userType(UserType.CUSTOMER)
					.name(signUpRequestDto.getName())
					.mobileNumber(signUpRequestDto.getMobileNumber())
					.address(signUpRequestDto.getAddress())
					.emailHash(hashedEmail)
					.passwordHash(hashedPassword)
					.build();
			String userId = userDao.saveUser(newUser).getId().toString();
			return handleSavedUser(userId);
		} catch (Exception e) {
			log.error("Error while signing up: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) throws InvalidCredentialException, ResourceNotExistsException, HashingException {
		try {
			User user = userDao.findByEmail(loginRequestDto.getEmail());
			boolean userSessionExists = sessionDao.existsByUser(user);
			if (userSessionExists)
				throw new InvalidCredentialException(String.format("Already session exists for userId: %s", user.getId()));
			String hashedPassword = passwordUtilService.getHash(loginRequestDto.getPassword());
			String hashedEmail = passwordUtilService.getHash(loginRequestDto.getEmail());
			if (!user.getEmailHash().equals(hashedEmail) || !user.getPasswordHash().equals(hashedPassword)) {
				throw new InvalidCredentialException("Entered credentials are incorrect!");
			}
			Session currentSession = Session.builder()
					.user(user)
					.authKey(UUID.randomUUID().toString())
					.sessionStartTime(ZonedDateTime.now())
					.build();
			Session savedSession = sessionDao.save(currentSession);
			return LoginResponseDto.builder()
					.authKey(savedSession.getAuthKey())
					.sessionStartTime(savedSession.getSessionStartTime())
					.build();
		} catch (Exception e) {
			log.error("Error occurred while trying to login: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public LogoutResponseDto logout(String authKey) throws ResourceNotExistsException {
		Session currentSession = sessionDao.findByAuthKey(authKey);
		sessionDao.delete(currentSession);
		return LogoutResponseDto.builder()
				.timestamp(ZonedDateTime.now())
				.message("Logged out successfully")
				.build();
	}

	@Override
	public User makeUserAdmin(String userEmail, String code) throws InvalidCredentialException {
//		if (!code.equals("admin")) {
//			throw new InvalidCredentialException("Invalid Passcode...");
//		} else if (userEmail.equals(null)) {
//			throw new InvalidCredentialException("Invalid Email Address");
//		}
//		Optional<User> user = userDao.findByEmail(userEmail);
//		if (!user.isPresent()) {
//			throw new InvalidCredentialException("User not found with Id " + userEmail);
//		}
//		user.get().setUserType("admin");
//		userDao.save(user.get());
//		return user.get();
		return null;
	}

	@Override
	public boolean updateUserDetails(User user) throws InvalidCredentialException {
//		Optional<User> checkUser = userDao.findByEmail(user.getEmail());
//		if (!checkUser.isPresent())
//			throw new InvalidCredentialException("User doesn't exists with Id " + user.getEmail());
//		User u = userDao.saveUser(user);
//		return u != null;
		return false;
	}

	@Override
	public User deleteUser(Integer userId, String authKey) throws InvalidCredentialException {
//		Optional<CurrentUserLoginSession> culs = sessionRepository.findByAuthkey(authKey);
//		if (!culs.isPresent()) {
//			throw new InvalidCredentialException("Invalid Authentication Key");
//		}
//		String userType = userDao.findById(culs.get().getUserId()).get().getUserType().toString();
//		if (userType.equalsIgnoreCase("user")) {
//			throw new InvalidCredentialException("Unauthorized Request...");
//		}
//		Optional<User> user = userDao.findById(userId);
//		if (!user.isPresent()) throw new InvalidCredentialException("User doesn't exits with Id " + userId);
//		userDao.delete(user.get());
//		return user.get();
		return null;
	}
}
