package edu.mum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Appointment;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
	@Modifying
	@Query("delete from Appointment a where a.id = ?1")
	public void deleteAppointment(Long id);
	
	@Query("SELECT count(a)>0 from Appointment a where a.customer.id = ?1 and a.session.id = ?2")
	public boolean appointmentExists(Long customerID, Long sessionID);
	
	@Query("SELECT count(a) from Appointment a where a.session.id = ?1")
	public int countAppointment(Long sessionID);
	
	@Query("SELECT a from Appointment a join a.session s where s.person.id = ?1")
	public List<Appointment> findAllCounsellorAppointment(Long userID);
	
}
