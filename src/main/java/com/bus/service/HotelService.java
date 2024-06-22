package com.bus.service;

import com.bus.exception.HotelException;
import com.bus.model.Hotel;



public interface HotelService {
	
	Hotel addHotel(Integer packageId, Hotel hotel, String authKey) throws HotelException;
	
	Hotel deleteHotel(Integer hotelId, String authKey) throws HotelException;
	
	Hotel updateHotel(Hotel hotel, String authKey) throws HotelException;
	
	

}
