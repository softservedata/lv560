package org.bn.travel_agency.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Username or password is incorrect");
		}

		if (logout != null) {
			model.addAttribute("message", "You've successfully logged out");
		}

		return "login";
	}

	@GetMapping(value = {"/"})
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
			return "redirect:/admin/users";
		else
			return "redirect:/user/hotels";
	}

}
