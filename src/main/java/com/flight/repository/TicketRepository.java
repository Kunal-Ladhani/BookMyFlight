package com.flight.repository;

import com.flight.model.Packages;
import com.flight.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	List<Ticket> findByPackages(Packages packages);
}
