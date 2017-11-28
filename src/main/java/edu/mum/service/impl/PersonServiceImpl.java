package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Person;
import edu.mum.repository.PersonRepository;
import edu.mum.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(Person person) {
		String encodedPassword = passwordEncoder.encode(person.getPassword());
		person.setPassword(encodedPassword);
		personRepository.save(person);
	}

	public List<Person> findAll() {
		return (List<Person>) personRepository.findAll();
	}

	public Person findOne(Long id) {
		return personRepository.findOne(id);
	}

	public Person findById(Long id) {
		return personRepository.findById(id);
	}

	public Person findByUsername(String username) {
		return personRepository.findByUsername(username);
	}

	public List<Person> findAllCounselor() {
		return (List<Person>) personRepository.findAllPersonWithRole("ROLE_COUNSELOR");
	}

}
