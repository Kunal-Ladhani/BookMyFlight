package com.flight.exception;

import com.flight.constants.ExceptionCode;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {

	private final ExceptionCode exceptionCode;

	public ServiceException() {
		super(ExceptionCode.U101.defaultMessage);
		exceptionCode = ExceptionCode.U101;
	}

	public ServiceException(String message) {
		super(message);
		this.exceptionCode = ExceptionCode.U101;
	}

	public ServiceException(ExceptionCode exceptionCode, String message) {
		super(message);
		this.exceptionCode = exceptionCode;
	}

	public ServiceException(ExceptionCode exceptionCode, Throwable cause, String message) {
		super(message, cause);
		this.exceptionCode = exceptionCode;
	}

}
