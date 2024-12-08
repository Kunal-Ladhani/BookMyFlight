package com.flight.dao;

import com.flight.exception.ResourceNotExistsException;
import com.flight.model.Session;
import com.flight.model.User;
import com.flight.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SessionDao {

	private final SessionRepository sessionRepository;

	@Autowired
	public SessionDao(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public Session save(Session session) {
		return sessionRepository.save(session);
	}

	public void delete(Session session) {
		sessionRepository.delete(session);
	}

	public boolean existsByUser(User user) {
		return sessionRepository.existsByUser(user);
	}

	public Session findByAuthKey(String authKey) throws ResourceNotExistsException {
		return sessionRepository.findOneByAuthKey(authKey)
				.orElseThrow(() -> new ResourceNotExistsException("Session does not exists with auth key:" + authKey));
	}

}
