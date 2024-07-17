package com.bus.repository;

import com.bus.model.Packages;
import com.bus.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	List<Ticket> findByPackages(Packages packages);
}
