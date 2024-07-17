package com.bus.service;

import com.bus.exception.InvalidCredentialException;
import com.bus.exception.UserAlreadyExistsException;
import com.bus.dto.SessionDTO;
import com.bus.model.User;
import com.bus.dto.UserDTO;

public interface UserAuthenticationService {

	User userSignUp(User user) throws UserAlreadyExistsException;

	SessionDTO userLogin(UserDTO user) throws InvalidCredentialException;

	String userLogout(String authKey) throws InvalidCredentialException;

	boolean updateUser(User user) throws InvalidCredentialException;

	// Admin access only
	User deleteUser(Integer userId, String authKey) throws InvalidCredentialException;

	User makeUserAdmin(String userEmail, String passcode) throws InvalidCredentialException;
}
