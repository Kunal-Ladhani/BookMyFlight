package com.flight.repository;

import com.flight.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	Optional<User> findByUserId(Integer userId);

	// --------------------------------------------------------------------------

	boolean existsByEmailOrMobileNumber(String email, String mobileNumber);


}
