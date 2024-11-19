package com.flight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flight.dto.Address;
import com.flight.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	private String name;

	private String mobileNumber;

	@Embedded
	private Address address;

	@JsonIgnore
	private UserType userType;

	private String email;

	private String passwordHash;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Booking> bookings = new ArrayList<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Feedback> feedbacks = new ArrayList<>();
}
