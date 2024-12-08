package com.flight.exception;

import com.flight.constants.ExceptionCode;

public class HashingException extends ServiceException {
	public HashingException() {
		super(ExceptionCode.U101, ExceptionCode.U101.defaultMessage);
	}

	public HashingException(String message) {
		super(ExceptionCode.U101, message);
	}

	public HashingException(String message, ExceptionCode exceptionCode) {
		super(exceptionCode, message);
	}

	public HashingException(String message, Throwable cause, ExceptionCode exceptionCode) {
		super(exceptionCode, cause, message);
	}

}
