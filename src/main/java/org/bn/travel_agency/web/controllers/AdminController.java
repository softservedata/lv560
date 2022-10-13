package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String showAll(Model model) {
		model.addAttribute("x", userService.findAll());
		return "test";
	}
}
