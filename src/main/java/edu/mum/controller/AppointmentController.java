package edu.mum.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.domain.Appointment;
import edu.mum.domain.Person;
import edu.mum.domain.Session;
import edu.mum.service.AppointmentService;
import edu.mum.service.EmailService;
import edu.mum.service.PersonService;
import edu.mum.service.SessionService;

@Controller
@RequestMapping({ "/appointments" })
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listAppointments(Model model, Principal principal) {
		Person customer = personService.findByUsername(principal.getName());

		List<Appointment> appointments = customer.getAppointments();

		for (Appointment appointment : appointments) {
			Session session = appointment.getSession();
			appointmentService.setOccupiedSession(session);
		}

		model.addAttribute("customer", customer);
		model.addAttribute("appointments", appointments);

		return "appointments/index";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String listAllAppointments(Model model) {

		List<Appointment> appointments = appointmentService.findAll();

		model.addAttribute("appointments", appointments);

		for (Appointment appointment : appointments) {
			Session session = appointment.getSession();
			appointmentService.setOccupiedSession(session);
		}

		return "appointments/all";
	}

	@RequestMapping(value = "/counselor", method = RequestMethod.GET)
	public String listAllCounsellorAppointments(Model model, Principal principal) {
		Person customer = personService.findByUsername(principal.getName());

		List<Appointment> appointments = appointmentService.findAllCounsellorAppointment(customer.getId());

		model.addAttribute("appointments", appointments);

		for (Appointment appointment : appointments) {
			Session session = appointment.getSession();
			appointmentService.setOccupiedSession(session);
		}

		return "appointments/all";
	}

	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
	public String cancelAppointment(@PathVariable("id") Long id, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {

		Appointment appointment = appointmentService.findOne(id);
		String referer = request.getHeader("Referer");

		Date date = appointment.getSession().getDate();
		Date time = appointment.getSession().getStartTime();

		@SuppressWarnings("deprecation")
		Date myDate = new Date(date.getYear(), date.getMonth(), date.getDate(), time.getHours(), time.getMinutes());

		long millisIn48Hours = 1000 * 60 * 60 * 48;
		Date hoursago = new Date(myDate.getTime() - millisIn48Hours);

		if (!request.isUserInRole("ROLE_ADMIN")) {
			if (!new Date().before(hoursago)) {
				redirectAttrs.addFlashAttribute("message",
						"You cannot cancel this appointment. The appointment is in less than 48 hours. Please contact admin for more info.");

				return "redirect:" + referer;
			}
		}

		appointmentService.deleteById(id);
		
		Session session = appointment.getSession();
		Person customer = personService.findById(appointment.getCustomer().getId());
		Person counseler = personService.findById(session.getPerson().getId());
		String customer_email = customer.getEmailAddress();
		String counseler_email = counseler.getEmailAddress();
		
		String message = "You Appointment for Transcendental Meditation has been cancelled. \n\n";

		emailService.doSendEmail(customer_email, counseler_email, session, message);
		redirectAttrs.addFlashAttribute("message",
				"You appointment has been cancelled. Please check email for more details.");

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getAddNewAppointmentForm(@ModelAttribute("appointment") Appointment appointment, Model model) {

		List<Session> sessions = sessionService.findAll();

		model.addAttribute("sessions", sessions);

		for (Session session : sessions) {
			appointmentService.setOccupiedSession(session);
		}

		return "appointments/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processAddNewAppointmentForm(@ModelAttribute("appointment") @Valid Appointment appointment,
			BindingResult result, Principal principal, Model model, RedirectAttributes redirectAttrs) {

		Long sessionID = appointment.getSession().getId();

		Session session = sessionService.findOne(sessionID);

		int countSession = appointmentService.checkAppointmentCount(sessionID);

		model.addAttribute("sessions", sessionService.findAll());

		if (result.hasErrors()) {
			return "appointments/create";
		}
		
		if (session.getDate().before(new Date())) {
			redirectAttrs.addFlashAttribute("message", "You cannot book the past session.");
			return "redirect:/appointments/create";
		}
		
		if (countSession >= session.getCapacity()) {
			redirectAttrs.addFlashAttribute("message", "The session is full.");
			return "redirect:/appointments/create";
		}

		Person customer = personService.findByUsername(principal.getName());
		appointment.setCustomer(customer);

		if (appointmentService.checkIfAppointmentExists(customer.getId(), appointment.getSession().getId())) {
			redirectAttrs.addFlashAttribute("message", "You have already booked this session");
			return "redirect:/appointments/create";
		}

		Person counseler = personService.findById(session.getPerson().getId());
		String customer_email = customer.getEmailAddress();
		String counseler_email = counseler.getEmailAddress();

		appointmentService.save(appointment);
		
		String message = "You have successfully booked the Appointment for Transcendental Meditation. \n\n";

		emailService.doSendEmail(customer_email, counseler_email, session, message);
		redirectAttrs.addFlashAttribute("message",
				"You have successfully booked the appointment. Please check email for more details.");

		return "redirect:/appointments";

	}

}