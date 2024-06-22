package com.bus.service;

import java.util.List;

import com.bus.exception.TravelException;
import com.bus.model.Travel;

public interface TravelService {
	
	
	
	Travel addTravels(Travel travel, String authKey) throws TravelException;
	
	Travel updateTravels(Travel travel, String authKey) throws TravelException;

	Travel removeTravels(Integer travelId, String authKey) throws TravelException;
	
	Travel serchTravels(Integer travelId) throws TravelException;
	
	List<Travel> viewTravels() throws TravelException;
}
