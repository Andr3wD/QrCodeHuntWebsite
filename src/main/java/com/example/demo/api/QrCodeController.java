package com.example.demo.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Report;
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
	 * TODO fix, change to name instead of UUID and from id to name So url mapping
	 * is rootdomain/submit. Additionally, the
	 * "@RequestParam(required = false) UUID id" is a little weird in that the
	 * parameter "id" is literally obtained from the url directly. So in
	 * rootdomain/submit?id=SOMEUUID, SOMEUUID would populate the "id" variable.
	 */
	@GetMapping(path = "/submit")
	public String foundCode(@RequestParam(required = false) String name, Model model, HttpServletRequest request) {
		// TODO add way to check if user is logged in or not and hand out page
		// accordingly.
		// TODO check that it only allows real submissions.
		if (name != null) {
			if (codeService.getCodeByName(name) != null) {
				// I suspect low # of users, so parsing to get next code using a name instead of
				// primary key is probably a better tradeoff than huge UUID space taking keys.
				if (request.isUserInRole("ROLE_USER")) {
					
					return "user-found-qr-page";
				} else {
					// For guests just loop around the database
					model.addAttribute("hint", codeService.getNextCodeByName(name).getHint());
					return "guest-found-qr-page";
				}

				
			} else { // Code doesn't exist. Show error page.
				model.addAttribute("errorObj", new Report()); // TODO complete
				return "error-qr-page";
			}

		} else {
			// TODO Show submit qrcode page
			return "guest-found-qr-page";
		}

	}

}
