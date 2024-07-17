package com.bus.service.impl;

import com.bus.exception.FeedbackException;
import com.bus.model.CurrentUserLoginSession;
import com.bus.model.Feedback;
import com.bus.model.User;
import com.bus.repository.FeedbackRepository;
import com.bus.repository.SessionRepository;
import com.bus.repository.UserRepository;
import com.bus.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Feedback addFeedback(Feedback feedback, String authKey) throws FeedbackException {
		Optional<CurrentUserLoginSession> currentUserOptional = sessionRepository.findByAuthkey(authKey);
		if (currentUserOptional.isEmpty()) {
			throw new FeedbackException("Kindly login first into your account");
		}
		CurrentUserLoginSession currentUserLoginSession = currentUserOptional.get();
		var userId = currentUserLoginSession.getUserId();
		var user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new FeedbackException("User not present.");
		}
		var currentUser = user.get();
		if (currentUser.getUserType().equals("admin")) {
			throw new FeedbackException("Only users can give feedback");
		}
		feedback.setUser(currentUser);
		return feedbackRepository.save(feedback);
	}

	@Override
	public Feedback findByFeedbackId(Integer feedbackId) throws FeedbackException {
		Optional<Feedback> optFeedback = feedbackRepository.findById(feedbackId);
		if (optFeedback.isEmpty()) {
			throw new FeedbackException("No Feedback present with the given Feedback Id");
		}
		return optFeedback.get();
	}

	@Override
	public List<Feedback> findByCustomerId(Integer customerId, String authKey) throws FeedbackException {
		Optional<CurrentUserLoginSession> currentUserOptional = sessionRepository.findByAuthkey(authKey);
		if (currentUserOptional.isEmpty()) {
			throw new FeedbackException("Kindly login first into your account");
		}
		CurrentUserLoginSession currentUserLoginSession = currentUserOptional.get();
		User user = userRepository.findById(currentUserLoginSession.getUserId()).get();
		if (user.getUserType().equals("User")) {
			throw new FeedbackException("Only admins can access this feature");
		}
		Optional<User> userOptional = userRepository.findByUserId(customerId);
		if (userOptional.isEmpty()) {
			throw new FeedbackException("No user present with the given customer Id");
		}
		User userRequired = userOptional.get();
		List<Feedback> list = userRequired.getFeedbacks();
		if (list.isEmpty()) {
			throw new FeedbackException("No feedbacks made by the customer");
		}
		return list;
	}

	@Override
	public List<Feedback> viewAllFeedbacks(String authKey) throws FeedbackException {
		Optional<CurrentUserLoginSession> currentUserOptional = sessionRepository.findByAuthkey(authKey);
		if (currentUserOptional.isEmpty()) {
			throw new FeedbackException("Kindly login first into your account");
		}
		CurrentUserLoginSession currentUserLoginSession = currentUserOptional.get();
		User user = userRepository.findById(currentUserLoginSession.getUserId()).get();
		if (user.getUserType().equals("User")) {
			throw new FeedbackException("Only admins can access this feature");
		}
		return feedbackRepository.getAllFeedbacks();
	}
}
