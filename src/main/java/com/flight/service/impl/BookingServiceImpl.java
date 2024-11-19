package com.flight.service.impl;

import com.flight.enums.UserType;
import com.flight.exception.BookingException;
import com.flight.model.Booking;
import com.flight.model.CurrentUserLoginSession;
import com.flight.model.Packages;
import com.flight.model.User;
import com.flight.repository.BookingRepository;
import com.flight.repository.PackageRepository;
import com.flight.repository.SessionRepository;
import com.flight.repository.UserRepository;
import com.flight.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	PackageRepository pkgRepo;

	@Autowired
	SessionRepository sessionRepo;

	@Autowired
	BookingRepository bookRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public Booking makeBooking(Booking bookings, Integer pkgId) throws BookingException {
		Optional<Packages> packagesOpt = pkgRepo.findById(pkgId);

		if (packagesOpt.isPresent()) {
			List<User> users = bookings.getUsers();
			for (User user : users) {
//				user.getBookings().add(bookings);
			}
			bookings.setPackages(packagesOpt.get());
			return bookRepo.save(bookings);
		} else {
			throw new BookingException("Provide valid package... ");
		}
	}


	@Override
	public Booking cancelBooking(Integer bookingsId) throws BookingException {
		Optional<Booking> book = bookRepo.findById(bookingsId);
		if (book.isPresent()) {
			var booking = book.get();
			List<User> users = booking.getUsers();
			for (User user : users) {
//				user.getBookings().remove(booking);
			}
			bookRepo.delete(booking);
			return booking;
		} else {
			throw new BookingException("Booking not exists");
		}

	}
	
	/*
	 * entityManager.remove(group)
		for (User user : group.users) {
		     user.groups.remove(group);
		}
	 */

	@Override
	public List<Booking> viewBookings(Integer userId) throws BookingException {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			var user = userOpt.get();
//			List<Booking> bookings = user.getBookings();
//			if (bookings.isEmpty()) {
//				throw new BookingException("No booking exists..");
//			}
//			return bookings;
			return null;
		} else {
			throw new BookingException("No user exists..");
		}

	}


	@Override
	public List<Booking> viewAllBookings(String authKey) throws BookingException {
		Optional<CurrentUserLoginSession> currentUserLoginSessionOptional = sessionRepo.findByAuthkey(authKey);
		if (currentUserLoginSessionOptional.isEmpty()) {
			throw new BookingException("User session does not exist.");
		}
		var currentUserId = currentUserLoginSessionOptional.get().getUserId();
		Optional<User> currentUser = userRepo.findById(currentUserId);
		if (currentUser.isEmpty()) {
			throw new BookingException("User does not exist.");
		}
		var userType = currentUser.get().getUserType();
		if (userType.equals(UserType.CUSTOMER)) {
			throw new BookingException("Unauthorized Request...");
		} else {
			List<Booking> bookings = bookRepo.findAll();
			if (bookings.isEmpty()) {
				throw new BookingException("No bookings available...");
			} else {
				return bookings;
			}
		}
	}
}
