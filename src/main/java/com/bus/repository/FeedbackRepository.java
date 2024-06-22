package com.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bus.exception.FeedbackException;
import com.bus.model.Feedback;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

	@Query("select f from Feedback f")
	List<Feedback> getAllFeedbacks() throws FeedbackException;
}
