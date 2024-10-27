package com.flight.service;

import java.util.List;

import com.flight.exception.FeedbackException;
import com.flight.model.Feedback;


public interface FeedbackService {
	
	Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException;
	
	Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException;
	
	List<Feedback> findByCustomerId(Integer customerId, String authKey) throws FeedbackException;
	
	List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException;
	
	

}
