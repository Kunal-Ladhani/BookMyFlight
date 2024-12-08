package com.flight.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequestDto {
	@Email(message = "email format needs to be properly formatted")
	@NotBlank
	private String email;

	@Length(min = 6, message = "passwords needs to be min 6 characters long")
	@NotBlank
	private String password;
}
