package edu.mum.service;

import java.util.List;

import edu.mum.domain.Session;

public interface SessionService {
	public void save(Session session);

	public List<Session> findAll();

	public Session findOne(Long id);
	
	public void deleteById(Long id);

}
