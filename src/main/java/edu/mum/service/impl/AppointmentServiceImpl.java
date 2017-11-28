package edu.mum.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.domain.Appointment;
import edu.mum.domain.Session;
import edu.mum.repository.AppointmentRepository;
import edu.mum.service.AppointmentService;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_COUNSELOR')")
	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	public List<Appointment> findAll() {
		return (List<Appointment>) appointmentRepository.findAll();
	}
	
	public List<Appointment> findAllCounsellorAppointment(Long userID) {
		return (List<Appointment>) appointmentRepository.findAllCounsellorAppointment(userID);
	}

	public Appointment findOne(Long id) {
		return appointmentRepository.findOne(id);
	}

	public void deleteById(Long id) {
		appointmentRepository.deleteAppointment(id);
	}

	public boolean checkIfAppointmentExists(Long customerID, Long sessionID) {
		return appointmentRepository.appointmentExists(customerID, sessionID);
	}
	
	public int checkAppointmentCount(Long sessionID) {
		return appointmentRepository.countAppointment(sessionID);
	}
	
	public void setOccupiedSession(Session session) {
		int countSession = checkAppointmentCount(session.getId());
		int occupied = session.getCapacity() - countSession;
		session.setOccupied(occupied);
	}

}
