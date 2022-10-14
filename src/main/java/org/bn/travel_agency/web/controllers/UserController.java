package org.bn.travel_agency.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/hotels")
	public String showHotels(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		model.addAttribute("name", auth.getName());
		return "user/index";
	}
}
