package com.flight.repository;

import com.flight.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailHash(String emailHash);

	boolean existsByEmailHashOrMobileNumber(String emailHash, String mobileNumber);

}
