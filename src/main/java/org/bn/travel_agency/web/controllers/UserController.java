package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private HotelService hotelService;

	@GetMapping("/hotels")
	public String showHotels() {
		return "redirect:hotels/page_1";
	}

	@GetMapping("/hotels/page_{number}")
	public String showHotelsPageable(@PathVariable("number") int number, Model model) {

		model.addAttribute("hotels", hotelService.findAllHotels(number - 1, 101));
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "user/index";
	}

	@GetMapping("/hotel/{id}")
	public String showHotelInfo(@PathVariable("id") long id, Model model) {

		model.addAttribute("hotel", hotelService.findHotelById(id));
		model.addAttribute("rooms", hotelService.findHotelById(id).getRooms());
		model.addAttribute("locationName", hotelService.findHotelById(id).getLocation().getName());

		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "user/hotel_info";
	}
}
