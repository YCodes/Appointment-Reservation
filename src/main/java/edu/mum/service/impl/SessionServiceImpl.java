package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Session;
import edu.mum.repository.SessionRepository;
import edu.mum.service.SessionService;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(Session session) {
		sessionRepository.save(session);
	}

	public List<Session> findAll() {
		return (List<Session>) sessionRepository.findAll();
	}

	public Session findOne(Long id) {
		return sessionRepository.findOne(id);
	}
	
	public void deleteById(Long id) {
		sessionRepository.delete(id);
	}
	
	

}
