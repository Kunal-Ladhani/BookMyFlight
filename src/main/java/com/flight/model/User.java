package com.flight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flight.dto.Address;
import com.flight.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;

	@Embedded
	@NotNull
	private Address address;

	@JsonIgnore
	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false)
	private UserType userType;

	@Column(name = "email_hash", nullable = false)
	private String emailHash;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@JsonIgnore
	@Embedded
	private AbstractAuditEntity abstractAuditEntity;

	@JsonIgnore
	@OneToOne(mappedBy = "user", targetEntity = Session.class)
	private Session session;

}
