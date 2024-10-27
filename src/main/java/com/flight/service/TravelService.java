package com.flight.service;

import java.util.List;

import com.flight.exception.TravelException;
import com.flight.model.Travel;

public interface TravelService {
	
	
	
	Travel addTravels(Travel travel, String authKey) throws TravelException;
	
	Travel updateTravels(Travel travel, String authKey) throws TravelException;

	Travel removeTravels(Integer travelId, String authKey) throws TravelException;
	
	Travel serchTravels(Integer travelId) throws TravelException;
	
	List<Travel> viewTravels() throws TravelException;
}
