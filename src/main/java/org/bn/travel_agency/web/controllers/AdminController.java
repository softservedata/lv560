package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/users")
	public String showUsers(Model model) {
		return "redirect:users/page_1";
	}

	@GetMapping("/users/page_{number}")
	public String showUsersPageable(@PathVariable("number") int number, Model model) {
		model.addAttribute("users", userService.findAllUsers(number - 1, 3));
		model.addAttribute("message", userService.findAllUsers().size());
		model.addAttribute("name", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/index";
	}

}
