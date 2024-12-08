package com.flight.constants;

import lombok.Getter;

@Getter
public enum ExceptionCode {
	A101("A101", "Authentication Error"),
	A102("A102", "A102"),
	U101("U101","Unknown exception occurred"),
	U102("U102", "User does not exists");



	public final String code;
	public final String defaultMessage;

	ExceptionCode(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}
}
