package edu.mum.service;

import java.util.List;

import edu.mum.domain.Appointment;
import edu.mum.domain.Session;

public interface AppointmentService {
	public void save(Appointment appointment);

	public List<Appointment> findAll();

	public Appointment findOne(Long id);

	public void deleteById(Long id);
	
	public boolean checkIfAppointmentExists(Long customerID, Long sessionID);
	
	public int checkAppointmentCount(Long sessionID);
	
	public List<Appointment> findAllCounsellorAppointment(Long userID);
	
	public void setOccupiedSession(Session session);

}
