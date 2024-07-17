package com.bus.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "Bus")
@Data
@ToString
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;

	@Enumerated(EnumType.STRING)
	private BusType busType;

	private Integer busNo;

	private Integer capacity;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Travel tDetails;
}
