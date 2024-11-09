package com.flight.dto.request;

import com.flight.dto.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

	@NotBlank
	@NotEmpty
	@Size(min = 3, max = 20, message = "Name must contain at least 3 characters")
	private String name;

	@NotNull
	@Pattern(regexp = "[0-9]{10}", message = "Mobile number should be of 10 digits")
	private String mobileNumber;

	@Email(message = "Invalid Email Address.")
	private String email;

	@Pattern(regexp = "[A-Za-z0-9@]{6,15}", message = "Password must be 6 to 15 characters and must have at least 1 alphabate and 1 number")
	@NotNull
	@NotBlank
	@NotEmpty
	private String password;

	@Valid
	private Address address;
}
