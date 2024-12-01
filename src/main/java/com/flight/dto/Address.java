package com.flight.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	@NotBlank
	private String line1;

	private String line2;

	private String line3;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	@Length(min = 6, max = 6, message = "Pincode must be 6 digits")
	private String pincode;

	@NotBlank
	private String country;
}
