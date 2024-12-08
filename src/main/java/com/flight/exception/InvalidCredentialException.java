package com.flight.exception;

import com.flight.constants.ExceptionCode;

public class InvalidCredentialException extends ServiceException {

	public InvalidCredentialException(String message) {
		super(message);
	}

	public InvalidCredentialException(ExceptionCode exceptionCode, String message) {
		super(exceptionCode, message);
	}

	public InvalidCredentialException(ExceptionCode exceptionCode, Throwable cause, String message) {
		super(exceptionCode, cause, message);
	}


}
