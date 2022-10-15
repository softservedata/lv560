package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.services.UserService;
import org.bn.travel_agency.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors())
			return "registration";

		userService.save(userForm);

		return "redirect:/";
	}
}
