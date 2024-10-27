package com.flight.service;

import com.flight.exception.HotelException;
import com.flight.model.Hotel;



public interface HotelService {
	Hotel addHotel(Integer packageId, Hotel hotel, String authKey) throws HotelException;
	
	Hotel deleteHotel(Integer hotelId, String authKey) throws HotelException;
	
	Hotel updateHotel(Hotel hotel, String authKey) throws HotelException;
}
