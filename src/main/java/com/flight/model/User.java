package com.flight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flight.dto.Address;
import com.flight.enums.UserType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String name;

	private String mobileNumber;

	@Embedded
	private Address address;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
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
