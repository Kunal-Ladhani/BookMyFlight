package com.flight.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "User_Session")
public class CurrentUserLoginSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	private Integer userId;

	private String authKey;

	private LocalDateTime sessionStartTime;
}
