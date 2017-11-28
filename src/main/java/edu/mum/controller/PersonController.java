package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.domain.Person;
import edu.mum.service.PersonService;

@Controller
@RequestMapping({ "/persons" })
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listMembers(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "persons/persons";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getMemberById(@PathVariable("id") Long id, Model model) {
		Person person = personService.findOne(id);
		model.addAttribute("person", person);

		return "persons/person";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getAddNewMemberForm(@ModelAttribute("newPerson") Person newPerson) {
		return "persons/addPerson";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processAddNewMemberForm(@ModelAttribute("newPerson") @Valid Person memberToBeAdded,
			BindingResult result) {

		if (result.hasErrors()) {
			return "persons/addPerson";
		}
		// Error caught by ControllerAdvice IF no authorization...
		personService.save(memberToBeAdded);

		return "redirect:/persons";
	}

}
