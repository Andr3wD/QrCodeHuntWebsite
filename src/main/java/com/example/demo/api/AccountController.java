package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RequestMapping("")
@Controller
public class AccountController {
	// TODO put secure cookie after giving temp UUID for user.

	private final PersonService personService;

	@Autowired
	public AccountController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/login")
	public String loginUser() {
		return null;
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute Person person) {

		// TODO verify that person was filled out correctly.
		personService.insertPerson(person);
		return null;
	}

	@GetMapping("/login")
	public String getPage(Model model) {
		model.addAttribute("person", new Person());
		return "guest-login_register-page";
	}

}
