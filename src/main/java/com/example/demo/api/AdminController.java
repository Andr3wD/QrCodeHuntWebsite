package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Person;
import com.example.demo.model.QrCode;
import com.example.demo.service.PersonService;
import com.example.demo.service.QrCodeService;

/**
 * This controller is for specifically dealing with admin related tasks.
 * 
 * @author Andrew
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	// TODO figure out how to add security

	private final QrCodeService codeService;
	private final PersonService personService;

	@Autowired
	public AdminController(QrCodeService codeService, PersonService personService) {
		this.codeService = codeService;
		this.personService = personService;
	}

	@PostMapping("/person")
	public String insertPerson(@ModelAttribute Person person, Model model) {
		personService.insertPerson(person);
		return "redirect:/admin"; // Prevent form resubmission. Causes a new get-request, which is handled by
											// getPage().
	}

	@PostMapping("/qrcode")
	public String insertCode(@ModelAttribute QrCode code, Model model) {
		codeService.insertCode(code);
		return "redirect:/admin"; // Prevent form resubmission. Causes a new get-request, which is handled by
											// getPage().
	}

	@GetMapping
	public String getPage(Model model) {
		// Put DB for view and take more codes in
		model.addAttribute("code", new QrCode());
		model.addAttribute("person", new Person());
		model.addAttribute("qrcodelist", codeService.getAllCodes());
		model.addAttribute("personlist", personService.getAllPersons());
		return "admin-page";
	}
}
