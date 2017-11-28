package edu.mum.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.domain.Person;
import edu.mum.service.PersonService;

@Controller
public class HomeController {

	@Autowired
	private PersonService personService;

	@RequestMapping({ "/", "/welcome" })
	public String welcome(Model model, Principal principal) {
		
		if (principal != null) {
			Person customer = personService.findByUsername(principal.getName());
			model.addAttribute("user", customer);
		}
		
		model.addAttribute("greeting", "Welcome to Appointment Management System");
		model.addAttribute("tagline", "Register your appointment");
		;

		return "welcome";
	}

}
