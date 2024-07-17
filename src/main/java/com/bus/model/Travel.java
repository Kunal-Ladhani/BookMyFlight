package com.bus.model;

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

	//	@Size(min=3,max=9,message="length should be atleast 3 character")
	private String travelName;

	//	@Size(min=3,max=10,message="{name.invalid}")
	private String agentName;

	@Embedded
	private Address addr;

	private Integer contact;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tDetails", fetch = FetchType.EAGER)
	private Set<Bus> buses = new HashSet<>();


}
