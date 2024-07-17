package com.bus.repository;

import com.bus.exception.FeedbackException;
import com.bus.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	@Query("select f from Feedback f")
	List<Feedback> getAllFeedbacks() throws FeedbackException;
}
