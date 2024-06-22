package com.bus.service;

import java.util.List;

import com.bus.exception.FeedbackException;
import com.bus.model.Feedback;


public interface FeedbackService {
	
	Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException;
	
	Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException;
	
	List<Feedback> findByCustomerId(Integer customerId, String authKey) throws FeedbackException;
	
	List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException;
	
	

}
