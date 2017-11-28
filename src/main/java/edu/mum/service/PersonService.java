package edu.mum.service;

import java.util.List;

import edu.mum.domain.Person;
 
public interface PersonService {
	public void save(Person person);
	public List<Person> findAll();
 	public Person findOne(Long id);
	public Person findById(Long id);
	public Person findByUsername(String username);
	public List<Person> findAllCounselor();
}
