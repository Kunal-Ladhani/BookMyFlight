package com.bus.service.impl;

import com.bus.exception.HotelException;
import com.bus.model.CurrentUserLoginSession;
import com.bus.model.Hotel;
import com.bus.model.Packages;
import com.bus.model.User;
import com.bus.repository.HotelRepository;
import com.bus.repository.PackageRepository;
import com.bus.repository.SessionRepository;
import com.bus.repository.UserRepository;
import com.bus.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PackageRepository packageRepository;

	@Override
	public Hotel addHotel(Integer packageId, Hotel hotel, String authKey) throws HotelException {

		Optional<CurrentUserLoginSession> opt = sessionRepository.findByAuthkey(authKey);

		if (!opt.isPresent()) {
			throw new HotelException("The admin is not logged in. Please Log in first");
		} else {
			CurrentUserLoginSession currentUser = opt.get();
			Optional<User> opt2 = userRepository.findById(currentUser.getUserId());
			User user = opt2.get();
			if (user.getUserType().equals("admin")) {

				Optional<Packages> optPackage = packageRepository.findById(packageId);
				if (!optPackage.isPresent()) {
					throw new HotelException("Package with given pakcage Id does not exist");
				}
				Packages packages = optPackage.get();

				hotel.setPackages(packages);
				Hotel returnedHotel = hotelRepository.save(hotel);

				return returnedHotel;

			} else {
				throw new HotelException("Kindly log in as Admin");
			}

		}
	}

	@Override
	public Hotel deleteHotel(Integer hotelId, String authKey) throws HotelException {

		Optional<CurrentUserLoginSession> opt = sessionRepository.findByAuthkey(authKey);

		if (!opt.isPresent()) {
			throw new HotelException("The admin is not logged in. Please Log in first");
		} else {
			CurrentUserLoginSession currentUser = opt.get();
			Optional<User> opt2 = userRepository.findById(currentUser.getUserId());
			User user = opt2.get();
			if (user.getUserType().equals("admin")) {
				Optional<Hotel> hotelOpt = hotelRepository.findById(hotelId);
				if (!hotelOpt.isPresent()) {
					throw new HotelException("No hotel present with the given hotel Id;");
				} else {
					Hotel hotel = hotelOpt.get();
					Packages packages = hotel.getPackages();

					hotel.setPackages(null);
					hotelRepository.delete(hotel);
					packageRepository.save(packages);

					return hotel;
				}

			} else {
				throw new HotelException("Kindly log in as Admin");
			}

		}
	}

	@Override
	public Hotel updateHotel(Hotel hotel, String authKey) throws HotelException {

		Optional<CurrentUserLoginSession> opt = sessionRepository.findByAuthkey(authKey);

		if (!opt.isPresent()) {
			throw new HotelException("The admin is not logged in. Please Log in first");
		} else {
			CurrentUserLoginSession currentUser = opt.get();
			Optional<User> opt2 = userRepository.findById(currentUser.getUserId());
			User user = opt2.get();
			if (user.getUserType().equals("admin")) {
				Optional<Hotel> hotelOpt = hotelRepository.findById(hotel.getHotelId());
				if (!hotelOpt.isPresent()) {
					throw new HotelException("No hotel present with the given details;");
				} else {
					Hotel returnedHotel = hotelOpt.get();
					if (hotel.getAddress() != null) {
						returnedHotel.setAddress(hotel.getAddress());
					}
					if (hotel.getHotelDescription() != null) {
						returnedHotel.setHotelDescription(hotel.getHotelDescription());
					}
					if (hotel.getHotelName() != null) {
						returnedHotel.setHotelName(hotel.getHotelName());
					}
					if (hotel.getHotelType() != null) {
						returnedHotel.setHotelType(hotel.getHotelType());
					}
					if (hotel.getRent() != 0) {
						returnedHotel.setRent(hotel.getRent());
					}
					if (hotel.getStatus() != null) {
						returnedHotel.setStatus(hotel.getStatus());
					}
					return hotelRepository.save(returnedHotel);

				}

			} else {
				throw new HotelException("Kindly log in as Admin");
			}

		}
	}
}
