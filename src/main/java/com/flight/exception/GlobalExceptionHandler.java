package com.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

	// GENERAL EXCEPTION HANDLER 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> OtherExceptionHandler(Exception exp, WebRequest req) {
		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage(exp.getMessage());
		authEx.setDescription(req.getDescription(false));

		return new ResponseEntity<>(authEx, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MANV EXCEPTION HANDLER
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exp) {

		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage("Validation Error");
		authEx.setDescription(exp.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(authEx, HttpStatus.BAD_REQUEST);
	}

	//ACCESS DENIED EXCEPTION HANDLER
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> AccessDeniedExceptionHandler(AccessDeniedException exp, WebRequest req) {
		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage(exp.getMessage());
		authEx.setDescription(req.getDescription(false));

		return new ResponseEntity<>(authEx, HttpStatus.FORBIDDEN);
	}

	//INVALID CREDENTIALS EXCEPTION HANDLER 
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ErrorDetails> InvalidCredentialExceptionHandler(InvalidCredentialException ice, WebRequest req) {
		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage(ice.getMessage());
		authEx.setDescription(req.getDescription(false));

		return new ResponseEntity<>(authEx, HttpStatus.BAD_REQUEST);
	}

	//USER EXCEPTION HANDLER 
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> DuplicateSignUpExceptionHandler(ResourceAlreadyExistsException uae, WebRequest req) {
		ErrorDetails authEx = new ErrorDetails();
		authEx.setTimestamp(LocalDateTime.now());
		authEx.setMessage(uae.getMessage());
		authEx.setDescription(req.getDescription(false));

		return new ResponseEntity<>(authEx, HttpStatus.BAD_REQUEST);
	}

}
