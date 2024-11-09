package com.flight.service;

import com.flight.dto.request.SignUpRequestDto;
import com.flight.dto.response.SignUpResponseDto;
import com.flight.exception.InvalidCredentialException;
import com.flight.exception.UserAlreadyExistsException;
import com.flight.dto.SessionDTO;
import com.flight.model.User;
import com.flight.dto.UserDTO;

public interface AuthenticationService {

	SignUpResponseDto userSignUp(SignUpRequestDto signUpRequestDto) throws Exception;

	SessionDTO userLogin(UserDTO user) throws InvalidCredentialException;

	String userLogout(String authKey) throws InvalidCredentialException;

	boolean updateUser(User user) throws InvalidCredentialException;

	// Admin access only
	User deleteUser(Integer userId, String authKey) throws InvalidCredentialException;

	User makeUserAdmin(String userEmail, String passcode) throws InvalidCredentialException;
}
