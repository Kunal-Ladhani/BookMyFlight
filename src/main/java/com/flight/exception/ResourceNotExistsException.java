package com.flight.exception;

import com.flight.constants.ExceptionCode;

public class ResourceNotExistsException extends ServiceException {

	public ResourceNotExistsException(String message) {
		super(ExceptionCode.U102, message);
	}

	public ResourceNotExistsException(ExceptionCode exceptionCode, String message) {
		super(exceptionCode, message);
	}

	public ResourceNotExistsException(ExceptionCode exceptionCode, Throwable cause, String message) {
		super(exceptionCode, cause, message);
	}

}
