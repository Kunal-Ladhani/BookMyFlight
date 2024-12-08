package com.flight.controller;

import com.flight.exception.InvalidCredentialException;
import com.flight.model.User;
import com.flight.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController {

	private final AuthenticationService authenticationService;

	public ProfileController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


	// -------------------------------- PROFILE APIs -------------------------------------

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<> fetchUserDetails(@Valid @RequestBody User user) {
		return new ResponseEntity<>(authenticationService.fetchDetails(user), HttpStatus.OK);
	}

	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<> updateUserDetails(@Valid @RequestBody User user) {
		return new ResponseEntity<>(authenticationService.updateDetails(user), HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity<> deleteUser(@RequestParam Integer userid, @RequestHeader("x-auth-key") String authKey) throws InvalidCredentialException {
		return new ResponseEntity<>(authenticationService.deleteUser(userid, authKey), HttpStatus.OK);
	}

	@PatchMapping("/assign-admin")
	public ResponseEntity<> Admin(@RequestParam("email") String email, @RequestParam("code") String passcode) {
		return new ResponseEntity<>(authenticationService.makeUserAdmin(email, passcode), HttpStatus.OK);
	}
}
