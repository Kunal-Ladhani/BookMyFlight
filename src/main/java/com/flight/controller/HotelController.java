package com.flight.controller;


import com.flight.exception.HotelException;
import com.flight.model.Hotel;
import com.flight.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@PostMapping("/hotel/add/{key}/{packageId}")
	public ResponseEntity<Hotel> hotelAdd(@RequestBody Hotel hotel, @PathVariable("key") String key, @PathVariable("packageId") Integer packageId) throws HotelException {
		Hotel returnedHotel = hotelService.addHotel(packageId, hotel, key);
		return new ResponseEntity<Hotel>(returnedHotel, HttpStatus.CREATED);
	}

	@DeleteMapping("/hotel/delete/{hotelId}/{key}")
	public ResponseEntity<Hotel> deleteHotel(@PathVariable("hotelId") Integer hotelId, @PathVariable("key") String key) throws HotelException {
		Hotel returnedHotel = hotelService.deleteHotel(hotelId, key);
		return new ResponseEntity<Hotel>(returnedHotel, HttpStatus.OK);
	}

	@PutMapping("/hotel/update/{key}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable("key") String key) throws HotelException {
		Hotel returnedHotel = hotelService.updateHotel(hotel, key);
		return new ResponseEntity<Hotel>(returnedHotel, HttpStatus.OK);
	}

}
