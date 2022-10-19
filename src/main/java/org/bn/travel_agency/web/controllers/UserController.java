package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.entities.Hotel;
import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.services.HotelService;
import org.bn.travel_agency.services.RoomService;
import org.bn.travel_agency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private HotelService hotelService;
	@Autowired
	private RoomService roomService;

	@Autowired
	private UserService userService;

	@GetMapping("/hotels")
	public String showHotels() {
		return "redirect:hotels/page_1";
	}

	@GetMapping("/hotels/page_{number}")
	public String showHotelsPageable(@PathVariable("number") int number, Model model) {

		model.addAttribute("hotels", hotelService.findAllHotels(number - 1, 101));
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("principalAmountOfMoney", userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getAmountOfMoney());

		return "user/index";
	}

	@GetMapping("/hotel/{id}")
	public String showHotelInfo(@PathVariable("id") long id, Model model) {
		addReservationPageData(id, model);

		return "user/reservation";
	}

	@GetMapping("/hotel/{id}/room/{roomId}")
	public String reserveRoomProcess(@PathVariable("id") long id,
									 @PathVariable("roomId") long roomId,
									 Model model) {
		addReservationPageData(id, model);
		Room room = roomService.findRoomById(roomId);
		model.addAttribute("room", room);
		model.addAttribute("reservations", room.getReservations());
		return "user/reservation";
	}

	@PostMapping("/hotel/{id}/reserve")
	public String reserveHotel(@PathVariable("id") long id,
							   @RequestParam long roomId,
							   @RequestParam String startDate,
							   @RequestParam String endDate) {
		Room room = roomService.findRoomById(roomId);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		if (userService.reserveRoom(user.getId(), roomId, startDate, endDate)) {
			return "redirect:/user/hotels";
		} else
			return "redirect:/user/hotel/" + id + "/room/" + roomId;
	}

	private void addReservationPageData(long hotelId, Model model) {
		Hotel hotel = hotelService.findHotelById(hotelId);
		model.addAttribute("hotel", hotel);
		model.addAttribute("rooms", hotel.getRooms().stream()
				.sorted((room1, room2) -> (int) (room2.getId() - room1.getId()))
				.collect(Collectors.toList()));
		model.addAttribute("locationName", hotel.getLocation().getName());
		model.addAttribute("numberOfColumns", Math.round(Math.sqrt(hotel.getRooms().size())));
		double cubeWidth = 1.8 * (24. / (Math.round(Math.sqrt(hotel.getRooms().size())))) / 3 * 0.85;

		model.addAttribute("cubeWidth", cubeWidth);
		model.addAttribute("columnWidth", Math.ceil(cubeWidth) + 1);

		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("principalAmountOfMoney", userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getAmountOfMoney());
	}
}
