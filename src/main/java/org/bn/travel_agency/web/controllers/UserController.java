package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.entities.Hotel;
import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.services.HotelService;
import org.bn.travel_agency.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private HotelService hotelService;
	@Autowired
	private RoomService roomService;
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

	//@PostMapping("/hotel/{id}")
	@GetMapping("/hotel/{id}")
	public String showHotelInfo(@PathVariable("id") long id, Model model) {
		Hotel hotel = hotelService.findHotelById(id);
		model.addAttribute("hotel", hotel);
		model.addAttribute("rooms", hotel.getRooms());
		model.addAttribute("locationName", hotel.getLocation().getName());
		model.addAttribute("numberOfColumns", Math.round(Math.sqrt(hotel.getRooms().size())));
		double cubeWidth = 1.8 * (24. / (Math.round(Math.sqrt(hotel.getRooms().size())))) / 3 * 0.85;

		model.addAttribute("cubeWidth", cubeWidth);
		model.addAttribute("columnWidth", Math.ceil(cubeWidth) + 1);

		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "user/reservation";
	}

	@PostMapping("/hotel/{id}/reserve")
	public String reserveHotel(@PathVariable("id") long id,
							   @RequestParam String roomId,
							   @RequestParam String startDate,
							   @RequestParam String endDate) {
		Room room = roomService.findRoomById(id);

		return "redirect:/user/hotels";
	}
}
