package com.flight.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SessionDTO {
	
	private String authKey;
	private LocalDateTime sessionStartTime;
}
