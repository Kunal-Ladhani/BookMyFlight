package com.flight.service;

import java.util.List;

import com.flight.exception.AccessDeniedException;
import com.flight.exception.InvalidRouteException;
import com.flight.exception.InvalidTicketException;
import com.flight.exception.PackageException;

import com.flight.model.Ticket;



public interface TicketService {
	
	// user or admin can create ticket
	Ticket createTicket(Ticket ticket, Integer packageId, Integer routeId, String authKey) throws InvalidTicketException, InvalidRouteException, PackageException, AccessDeniedException;
	
	// user or admin can see ticket
	Ticket getTicket(Integer ticketId, String authKey) throws InvalidTicketException, AccessDeniedException;
	
	// user or admin can delete ticket
	Ticket deleteTicket(Integer ticketId, String authKey) throws InvalidTicketException, AccessDeniedException;
		
	/* ADMIN can see all tickets in database
	 * USER can see their tickets
	 */
	List<Ticket> getAllTickets(Integer packageId, String authKey) throws InvalidTicketException, AccessDeniedException;
}
