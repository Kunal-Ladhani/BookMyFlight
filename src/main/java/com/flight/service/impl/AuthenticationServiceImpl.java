package com.flight.service.impl;

import com.flight.dao.UserDao;
import com.flight.dto.SessionDTO;
import com.flight.dto.UserDTO;
import com.flight.dto.request.SignUpRequestDto;
import com.flight.dto.response.SignUpResponseDto;
import com.flight.exception.InvalidCredentialException;
import com.flight.exception.UserAlreadyExistsException;
import com.flight.model.User;
import com.flight.repository.SessionRepository;
import com.flight.repository.UserRepository;
import com.flight.service.AuthenticationService;
import com.flight.utils.PasswordUtilService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserDao userDao;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordUtilService passwordUtilService;

	private SignUpResponseDto handleSavedUser(String userId) {
		return SignUpResponseDto.builder()
				.timestamp(Instant.now())
				.message("User created in db")
				.userId(userId)
				.build();
	}

	@Override
	public SignUpResponseDto userSignUp(SignUpRequestDto signUpRequestDto) throws Exception {
		log.info("Sign up process started for: {}", signUpRequestDto);
		if (userDao.checkUserExistenceByEmailOrMobileNumber(signUpRequestDto.getEmail(), signUpRequestDto.getMobileNumber())) {
			throw new UserAlreadyExistsException("User already exists with this email or mobile number");
		}
		String hashedPassword = passwordUtilService.hashPassword(signUpRequestDto.getPassword());

		User newUser = User.builder()
				.name(signUpRequestDto.getName())
				.email(signUpRequestDto.getEmail())
				.mobileNumber(signUpRequestDto.getMobileNumber())
				.address(signUpRequestDto.getAddress())
				.passwordHash(hashedPassword)
				.build();

		String userId = userDao.saveUser(newUser).getUserId().toString();
		return handleSavedUser(userId);
	}

	@Override
	public SessionDTO userLogin(UserDTO user) throws InvalidCredentialException {
//		Optional<User> opt = userDao.findByEmail(user.getEmail());
//		if (!opt.isPresent()) {
//			throw new InvalidCredentialException("User doesn't exists...");
//		}
//		Optional<CurrentUserLoginSession> sessionExistence = sessionRepository.findByUserId(opt.get().getUserId());
//		if (sessionExistence.isPresent()) {
//			throw new InvalidCredentialException("User already logged in");
//		}
//		if (!(opt.get().getEmail().equals(user.getEmail()))) {
//			throw new InvalidCredentialException("Invalid Email Address...");
//		} else if (!(opt.get().getPasswordHash().equals(user.getPasswordHash()))) {
//			throw new InvalidCredentialException("Invalid Password...");
//		} else if (!(opt.get().getPasswordHash().equals(user.getPasswordHash()) && opt.get().getEmail().equals(user.getEmail()))) {
//			throw new InvalidCredentialException("Invalid Credentials...");
//		}
//		SessionDTO sdt = new SessionDTO();
//		CurrentUserLoginSession culs = new CurrentUserLoginSession();
//		String authKey = UUID.randomUUID().toString();
//		culs.setAuthKey(authKey);
//		culs.setSessionStartTime(LocalDateTime.now());
//		sdt.setAuthKey(culs.getAuthKey());
//		sdt.setSessionStartTime(culs.getSessionStartTime());
//		culs.setUserId(opt.get().getUserId());
//		sessionRepository.save(culs);
		return null;
	}

	@Override
	public String userLogout(String authKey) throws InvalidCredentialException {
//		Optional<CurrentUserLoginSession> culs = sessionRepository.findByAuthkey(authKey);
//		if (!culs.isPresent()) {
//			throw new InvalidCredentialException("User has not logged In with key : " + authKey);
//		}
//		sessionRepository.delete(culs.get());
//		return "Logged out successfully.";
		return null;
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
	public boolean updateUser(User user) throws InvalidCredentialException {
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
