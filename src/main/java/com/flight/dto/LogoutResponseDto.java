package com.flight.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutResponseDto {
	private String message;
	private ZonedDateTime timestamp;
}
