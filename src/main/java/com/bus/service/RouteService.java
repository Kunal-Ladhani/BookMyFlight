package com.bus.service;

import java.util.List;

import com.bus.exception.AccessDeniedException;
import com.bus.exception.InvalidRouteException;
import com.bus.model.Route;

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
