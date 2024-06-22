package com.bus.service;

import java.util.List;

import com.bus.exception.AccessDeniedException;
import com.bus.exception.InvalidRouteException;
import com.bus.exception.InvalidTicketException;
import com.bus.exception.PackageException;

import com.bus.model.Ticket;



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
