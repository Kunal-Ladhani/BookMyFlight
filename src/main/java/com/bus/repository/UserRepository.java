package com.bus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.exception.UserAlreadyExistsException;
import com.bus.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User>  findByUserId(Integer userId);
	
}
