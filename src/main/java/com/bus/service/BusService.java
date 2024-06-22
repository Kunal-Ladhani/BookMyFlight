package com.bus.service;

import com.bus.exception.BusException;
import com.bus.exception.TravelException;
import com.bus.model.Bus;

import java.util.List;

public interface BusService {

	Bus addBus(Bus bus, Integer travelId, String authKey) throws TravelException;

	List<Bus> getAllBusFromAllAgency() throws BusException;

}
