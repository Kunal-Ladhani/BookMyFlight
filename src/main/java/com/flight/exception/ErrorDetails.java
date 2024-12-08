package com.flight.exception;

import com.flight.constants.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private ExceptionCode errorCode;
	private LocalDateTime timestamp;
	private String message;
	private String description;
}
