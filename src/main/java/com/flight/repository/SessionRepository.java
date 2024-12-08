package com.flight.repository;

import com.flight.model.Session;
import com.flight.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

	// This will check if a session exists for a specific user
	boolean existsByUser(User user);

	Optional<Session> findOneByAuthKey(String authKey);

	void delete(@NonNull Session session);
}
