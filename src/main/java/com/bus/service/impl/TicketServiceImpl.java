package com.bus.service.impl;

import java.util.List;
import java.util.Optional;

import com.bus.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.exception.AccessDeniedException;
import com.bus.exception.InvalidCredentialException;
import com.bus.exception.InvalidRouteException;
import com.bus.exception.InvalidTicketException;
import com.bus.exception.PackageException;
import com.bus.model.CurrentUserLoginSession;
import com.bus.model.Packages;
import com.bus.model.Route;
import com.bus.model.Ticket;
import com.bus.repository.PackageRepository;
import com.bus.repository.RouteRepository;

import com.bus.repository.SessionRepository;
import com.bus.repository.TicketRepository;
import com.bus.repository.UserRepository;


@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepo;

	@Autowired
	PackageRepository pkgRepo;

	@Autowired
	SessionRepository sessionRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RouteRepository routeRepo;

	@Override
	public List<Ticket> getAllTickets(Integer packageId, String authKey) throws InvalidTicketException, AccessDeniedException {
		List<Ticket> tickets = null;
		
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
				
		// CHECK IF API CALLER IS VALID USER		
		if(!userType.equalsIgnoreCase("user") && !userType.equalsIgnoreCase("admin")) {
			throw new AccessDeniedException("Access Denied");		
		}
		
		
		if(userType.equalsIgnoreCase("user") && packageId != null) {
			Optional<Packages> pkg = pkgRepo.findById(packageId);
			Packages packages = pkg.get();
			tickets = ticketRepo.findByPackages(packages);
		} else if(userType.equalsIgnoreCase("admin") && packageId == null) {
			tickets = ticketRepo.findAll();			
		} else if(userType.equalsIgnoreCase("admin") && packageId != null) {
			Optional<Packages> pkg = pkgRepo.findById(packageId);
			Packages packages = pkg.get();
			tickets = ticketRepo.findByPackages(packages);
		} else {
			throw new InvalidTicketException("Please enter package Id.");
		}
		
		if(tickets.isEmpty() || tickets == null) {
			throw new InvalidTicketException("Tickets Not Available.");
		}
		
		return tickets;
	}
	
	@Override
	public Ticket getTicket(Integer ticketId, String authKey) throws InvalidTicketException, AccessDeniedException {
		
		// CHECK IF API CALLER IS VALID USER
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		if(!userType.equalsIgnoreCase("user") && !userType.equalsIgnoreCase("admin")) {
			throw new AccessDeniedException("Access Denied");		
		}
		
		Ticket ticket = null;
		
		Optional<Ticket> tkt = ticketRepo.findById(ticketId);
		
		if(!tkt.isPresent()) {
			throw new InvalidTicketException("No such ticket exists.");
		} else {
			ticket = tkt.get();
		}
			
		return ticket;
	}
	
	@Override
	public Ticket createTicket(Ticket ticket, Integer packageId, Integer routeId, String authKey) throws InvalidTicketException, PackageException, InvalidRouteException, AccessDeniedException  {
		
		// CHECK IF API CALLER IS VALID USER
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		if(!userType.equalsIgnoreCase("user") && !userType.equalsIgnoreCase("admin")) {
			throw new AccessDeniedException("Access Denied");		
		}

		Optional<Packages> pkgOpt = pkgRepo.findById(packageId);
		if(pkgOpt.isEmpty()) {
			throw new PackageException("Package with package id = "+packageId+" does not exist.");
		}
		
		Optional<Route> routeOpt = routeRepo.findById(routeId);
		if(routeOpt.isEmpty()) {
			throw new InvalidRouteException("Route with route id = "+routeId+" does not exist.");
		}
		
		ticket.setPackages(pkgOpt.get());
		ticket.setRoute(routeOpt.get());
		
		Ticket ticketCreated  = ticketRepo.save(ticket);
		
		return ticketCreated;
	}

	
	@Override
	public Ticket deleteTicket(Integer ticketId, String authKey) throws InvalidTicketException, AccessDeniedException {
		// CHECK IF API CALLER IS VALID USER
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		if(!userType.equalsIgnoreCase("user") && !userType.equalsIgnoreCase("admin")) {
			throw new AccessDeniedException("Access Denied");		
		}
		
		Ticket ticket = null;
		
		Optional<Ticket> user = ticketRepo.findById(ticketId);		
		
		if(user.isPresent()) {
			ticket = user.get();
			
			ticketRepo.delete(ticket);
		} else {
			throw new InvalidTicketException("Ticket does not exist....");			
		}
		
		return ticket;
	}
}
