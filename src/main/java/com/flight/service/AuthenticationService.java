package com.flight.service;

import com.flight.dto.LoginRequestDto;
import com.flight.dto.LoginResponseDto;
import com.flight.dto.LogoutResponseDto;
import com.flight.dto.request.SignUpRequestDto;
import com.flight.dto.response.SignUpResponseDto;
import com.flight.exception.HashingException;
import com.flight.exception.InvalidCredentialException;
import com.flight.exception.ResourceNotExistsException;
import com.flight.model.User;

public interface AuthenticationService {

	SignUpResponseDto signup(SignUpRequestDto signUpRequestDto) throws Exception;

	LoginResponseDto login(LoginRequestDto user) throws InvalidCredentialException, ResourceNotExistsException, HashingException;

	LogoutResponseDto logout(String authKey) throws ResourceNotExistsException;

	boolean updateUserDetails(User user) throws InvalidCredentialException;

	// Admin access only
	User deleteUser(Integer userId, String authKey) throws InvalidCredentialException;

	User makeUserAdmin(String userEmail, String passcode) throws InvalidCredentialException;
}
