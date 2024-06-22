package com.bus.service;

import com.bus.exception.BookingException;
import com.bus.model.Booking;

import java.util.List;

public interface BookingService {

	Booking makeBooking(Booking bookings, Integer pkgId) throws BookingException;

	Booking cancelBooking(Integer bookingId) throws BookingException;

	List<Booking> viewBookings(Integer userId) throws BookingException;

	/*
	 * Administrator Access
	 */
	List<Booking> viewAllBookings(String authKey) throws BookingException;

}
