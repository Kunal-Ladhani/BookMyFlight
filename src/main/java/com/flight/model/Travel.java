package com.flight.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Travels")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Travel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer travelId;

	private String travelName;

	private String agentName;

	@Embedded
	private Address addr;

	private Integer contact;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tDetails", fetch = FetchType.EAGER)
	private Set<Bus> buses = new HashSet<>();


}
