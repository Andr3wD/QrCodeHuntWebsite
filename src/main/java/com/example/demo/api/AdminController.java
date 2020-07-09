package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.QrCode;
import com.example.demo.service.QrCodeService;

/**
 * This controller is for specifically dealing with admin related tasks.
 * @author Andrew
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	// TODO figure out how to add security

	private final QrCodeService codeService;

	@Autowired
	public AdminController(QrCodeService codeService) {
		this.codeService = codeService;
	}

	@PostMapping
	public String insertCode(@ModelAttribute QrCode code, Model model) {
		codeService.insertCode(code);
		return "redirect:/admin"; // Prevent form resubmission. Causes a new get-request, which is handled by
											// getPage().
	}

	@GetMapping
	public String getPage(Model model) {
		// Put DB for view and take more codes in
		model.addAttribute("code", new QrCode());
		model.addAttribute("list", codeService.getAllCodes());
		return "admin-page";
	}
}
