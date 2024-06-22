package com.bus.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.bus.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.exception.AccessDeniedException;
import com.bus.exception.InvalidRouteException;
import com.bus.model.Bus;
import com.bus.model.CurrentUserLoginSession;
import com.bus.model.Route;
import com.bus.model.Travel;
import com.bus.repository.PackageRepository;
import com.bus.repository.RouteRepository;
import com.bus.repository.SessionRepository;
import com.bus.repository.TicketRepository;
import com.bus.repository.TravelRepository;
import com.bus.repository.UserRepository;



@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	RouteRepository routeRepo;

	@Autowired
	TicketRepository ticketRepo;

	@Autowired
	PackageRepository pkgRepo;

	@Autowired
	SessionRepository sessionRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TravelRepository travelRepo;

	/* ADMIN ONLY ACCESS */
	@Override
	public Route addRoute(Route route, String authKey) throws AccessDeniedException, InvalidRouteException {
		Route createdRoute = null;
		
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		Optional<Route> optRoute = routeRepo.findById(route.getRouteId());
		
		if(optRoute.isPresent()) {
			throw new InvalidRouteException("Route No "+route.getRouteId()+" aleady exists.");
		}
		
		if(userType.equalsIgnoreCase("admin")) {
			
//			Optional<Travel> travelOpt = travelRepo.findById(travelId);
//			
//			if(travelOpt.isPresent()) {
//				Travel travel = travelOpt.get();
//				route.setTravelId(travel);				
//			}
			

			
			Set<Bus> buses=route.getTravelId().getBuses();
			for(Bus b:buses) {
				b.setTDetails(route.getTravelId());
			}
			createdRoute = routeRepo.save(route);
		} else {
			throw new AccessDeniedException("User is unauthorized for performing this function.");
		}
		
		return createdRoute;
	}

	/* ADMIN ONLY ACCESS */
	@Override
	public Route updateRoute(Route route, String authKey) throws InvalidRouteException, AccessDeniedException {
		Route updatedRoute = null;
		
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		Optional<Route> optRoute = routeRepo.findById(route.getRouteId());
		
		// check if user is ADMIN
		if(userType.equalsIgnoreCase("admin")) {
			// check if route is present
			if(optRoute.isPresent()) {
				updatedRoute = routeRepo.save(route);				
			} else {
				throw new InvalidRouteException("Route No "+route.getRouteId()+" does not exists.");
				
			}
		} else {
			throw new AccessDeniedException("User is unauthorized for performing this function.");
		}
		
		return updatedRoute;
	}

	/* ADMIN ONLY ACCESS */
	@Override
	public Route removeRoute(Integer routeId, String authKey) throws InvalidRouteException, AccessDeniedException {
		Route deletedRoute = null;
		
		Optional<CurrentUserLoginSession> culs = sessionRepo.findByAuthkey(authKey);
		
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		
		Optional<Route> optRoute = routeRepo.findById(routeId);
		
		// check if user is ADMIN
		if(userType.equalsIgnoreCase("admin")) {
			// check if route is present
			if(optRoute.isPresent()) {
				deletedRoute = optRoute.get();
				routeRepo.delete(optRoute.get());				
			} else {
				throw new InvalidRouteException("Route No "+routeId+" does not exists.");
				
			}
		} else {
			throw new AccessDeniedException("User is unauthorized for performing this function.");
		}
		
		return deletedRoute;
	}

	@Override
	public Route searchRoute(Integer routeId) throws InvalidRouteException {
		Route searchedRoute = null;
		
		Optional<Route> optRoute = routeRepo.findById(routeId);
		
		// check if route is present
		if(optRoute.isPresent()) {
			searchedRoute = optRoute.get();
		} else {
			throw new InvalidRouteException("Route No "+routeId+" does not exists.");	
		}
		
		return searchedRoute;
	}

	@Override
	public List<Route> viewRouteList() throws InvalidRouteException {
		List<Route> searchedRoutes = routeRepo.findAll();
		
		// check if route is present
		if(searchedRoutes.isEmpty() || searchedRoutes == null) {
			throw new InvalidRouteException("Routes do not exist.");	
		}
		
		return searchedRoutes;
	}
	
}
