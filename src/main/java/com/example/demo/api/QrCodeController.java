package com.example.demo.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.QrCodeService;

@RequestMapping("") 
@Controller
public class QrCodeController {

	private final QrCodeService codeService;

	@Autowired
	public QrCodeController(QrCodeService codeService) {
		this.codeService = codeService;
	}
	
	@GetMapping(path = "/submit")
	public String foundCode(@RequestParam(required = false) UUID id, Model model) {
		//TODO add way to check if user is logged in or not and hand out page accordingly.
		if (id != null) {
			model.addAttribute("hint", codeService.getNextCode(id).getHint()); //For guests just loop around the database
			return "guest-found-qr-page";
		} else {
			// Show submit qrcode page
			return "guest-found-qr-page";
		}
		
	}
	
	

}
