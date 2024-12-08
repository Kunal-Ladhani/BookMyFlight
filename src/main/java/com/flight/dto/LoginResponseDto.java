package com.flight.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

	private String authKey;

	private ZonedDateTime sessionStartTime;

}
