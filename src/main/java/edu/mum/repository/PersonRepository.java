package edu.mum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	public Person findById(Long id);
	
	public Person findByUsername(String username);
	
	@Query("SELECT p FROM Person p join p.authority a where a.authority = :role")
	public List<Person> findAllPersonWithRole(@Param("role") String role);
}
