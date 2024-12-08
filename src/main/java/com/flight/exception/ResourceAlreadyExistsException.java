package com.flight.exception;

import com.flight.constants.ExceptionCode;

public class ResourceAlreadyExistsException extends ServiceException {

	public ResourceAlreadyExistsException(String message) {
		super(ExceptionCode.U102, message);
	}

	public ResourceAlreadyExistsException(ExceptionCode exceptionCode, String message) {
		super(exceptionCode, message);
	}

	public ResourceAlreadyExistsException(ExceptionCode exceptionCode, Throwable cause, String message) {
		super(exceptionCode, cause, message);
	}

}
