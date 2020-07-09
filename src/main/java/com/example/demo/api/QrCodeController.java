package com.example.demo.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.QrCodeService;

/**
 * This controller is for dealing with QR code pages. Specifically entering a QR
 * code, showing that a code has already been obtained, showing that a code is
 * new, giving info on codes (the number of times found, comments, etc...)
 * 
 * @author Andrew
 */
@RequestMapping("")
@Controller
public class QrCodeController {

	private final QrCodeService codeService;

	@Autowired
	public QrCodeController(QrCodeService codeService) {
		this.codeService = codeService;
	}

	/*
	 * So url mapping is rootdomain/submit. Additionally, the
	 * "@RequestParam(required = false) UUID id" is a little weird in that the
	 * parameter "id" is literally obtained from the url directly. So in
	 * rootdomain/submit?id=SOMEUUID, SOMEUUID would populate the "id" variable.
	 */
	@GetMapping(path = "/submit")
	public String foundCode(@RequestParam(required = false) UUID id, Model model) {
		// TODO add way to check if user is logged in or not and hand out page
		// accordingly.
		if (id != null) {
			model.addAttribute("hint", codeService.getNextCode(id).getHint()); // For guests just loop around the database
			return "guest-found-qr-page";
		} else {
			// TODO Show submit qrcode page
			return "guest-found-qr-page";
		}

	}

}
