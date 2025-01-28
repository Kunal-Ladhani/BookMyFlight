package com.flight.controller;

import com.flight.dto.LoginRequestDto;
import com.flight.dto.LoginResponseDto;
import com.flight.dto.LogoutResponseDto;
import com.flight.dto.request.SignUpRequestDto;
import com.flight.dto.response.SignUpResponseDto;
import com.flight.exception.HashingException;
import com.flight.exception.InvalidCredentialException;
import com.flight.exception.ResourceNotExistsException;
import com.flight.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

	private final AuthenticationService authenticationService;

	@Autowired
	public AuthController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping(value = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
		return new ResponseEntity<>(authenticationService.signup(signUpRequestDto), HttpStatus.CREATED);
	}

	@PostMapping("/log-in")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) throws InvalidCredentialException, ResourceNotExistsException, HashingException {
		return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
	}

	@PostMapping("/log-out")
	public ResponseEntity<LogoutResponseDto> userLogout(@RequestHeader(value = "x-auth-key") String authKey) throws ResourceNotExistsException {
		return new ResponseEntity<>(authenticationService.logout(authKey), HttpStatus.OK);
	}
}
