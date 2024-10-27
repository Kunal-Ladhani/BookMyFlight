package com.flight.service;

import java.util.List;

import com.flight.exception.AccessDeniedException;
import com.flight.exception.InvalidRouteException;
import com.flight.model.Route;

public interface RouteService {
	
	/* ADMIN ONLY ACCESS */
	Route addRoute(Route route, String authKey) throws AccessDeniedException, InvalidRouteException;
	
	/* ADMIN ONLY ACCESS */
	Route updateRoute(Route route, String authKey) throws InvalidRouteException, AccessDeniedException;
	
	/* ADMIN ONLY ACCESS */
	Route removeRoute(Integer routeId, String authKey) throws InvalidRouteException, AccessDeniedException;
	
	Route searchRoute(Integer routeId) throws InvalidRouteException;
	
	List<Route> viewRouteList() throws InvalidRouteException;
	
}
