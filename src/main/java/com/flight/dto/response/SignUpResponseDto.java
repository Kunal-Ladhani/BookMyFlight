package com.flight.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@Builder
public class SignUpResponseDto {
	private String userId;

	private String message;

	private ZonedDateTime timestamp;
}