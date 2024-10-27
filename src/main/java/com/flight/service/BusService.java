package com.flight.service;

import com.flight.exception.BusException;
import com.flight.exception.TravelException;
import com.flight.model.Bus;

import java.util.List;

public interface BusService {

	Bus addBus(Bus bus, Integer travelId, String authKey) throws TravelException;

	List<Bus> getAllBusFromAllAgency() throws BusException;

}
