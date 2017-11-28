package edu.mum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Session;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
	
}
