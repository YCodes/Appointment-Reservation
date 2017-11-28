package edu.mum.controller;

import java.util.Date;
import java.util.List;

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

import edu.mum.domain.Session;
import edu.mum.service.AppointmentService;
import edu.mum.service.PersonService;
import edu.mum.service.SessionService;

@Controller
@RequestMapping({ "/sessions" })
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listSessions(Model model) {

		List<Session> sessions = sessionService.findAll();

		model.addAttribute("sessions", sessions);

		for (Session session : sessions) {
			appointmentService.setOccupiedSession(session);
		}

		return "sessions/sessions";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getSessionById(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttrs) {
		model.addAttribute("persons", personService.findAllCounselor());
		Session session = sessionService.findOne(id);
		model.addAttribute("session", session);

		if (session.getDate().before(new Date())) {
			redirectAttrs.addFlashAttribute("message", "Session with passed date cannot be modified.");
			return "redirect:/sessions";
		}

		return "sessions/session";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateSession(@PathVariable("id") Long id, @Valid Session updateSession, BindingResult result,
			Model model) {
		model.addAttribute("persons", personService.findAllCounselor());

		if (result.hasErrors()) {
			return "sessions/session";
		}

		int count = appointmentService.checkAppointmentCount(updateSession.getId());

		if (updateSession.getCapacity() < count) {
			model.addAttribute("message", "Capacity must be greater than the occupied.");

			return "sessions/session";
		}

		sessionService.save(updateSession);

		return "redirect:/sessions";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSession(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {

		Session session = sessionService.findOne(id);

		if (session.getDate().before(new Date())) {
			redirectAttrs.addFlashAttribute("message", "Session with passed date cannot be deleted.");
			return "redirect:/sessions";
		}

		sessionService.deleteById(id);

		return "redirect:/sessions";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getAddNewSessionForm(@ModelAttribute("session") Session session, Model model) {
		model.addAttribute("persons", personService.findAllCounselor());
		model.addAttribute("session", session);

		return "sessions/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processAddNewSessionForm(@ModelAttribute("session") @Valid Session session, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("persons", personService.findAllCounselor());
			return "sessions/create";
		}

		sessionService.save(session);

		return "redirect:/sessions";

	}

}
