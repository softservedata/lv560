package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.entities.Hotel;
import org.bn.travel_agency.entities.Reservation;
import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.services.HotelService;
import org.bn.travel_agency.services.ReservationService;
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
	@Autowired
	private ReservationService reservationService;

	@GetMapping("/hotels")
	public String showHotels() {
		return "redirect:hotels/page_1";
	}

	@GetMapping("/hotels/page_{number}")
	public String showHotelsPageable(@PathVariable("number") int number, Model model) {

		model.addAttribute("hotels", hotelService.findAllHotels(number - 1, 101));
		model.addAttribute("principal", userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

		return "user/index";
	}

	@GetMapping("/hotel/{id}")
	public String showHotelInfo(@PathVariable("id") long id, Model model) {
		addReservationPageData(id, model);

		return "user/new_reservation";
	}

	@GetMapping("/hotel/{id}/room/{roomId}")
	public String reserveRoomProcess(@PathVariable("id") long id,
									 @PathVariable("roomId") long roomId,
									 Model model) {
		addReservationPageData(id, model);
		Room room = roomService.findRoomById(roomId);
		model.addAttribute("room", room);
		model.addAttribute("reservations", room.getReservations());
		return "user/new_reservation";
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

		long numberOfColumns = Math.round(Math.sqrt(hotel.getRooms().size()));
		if (hotel.getRooms().size()==2){
			numberOfColumns = 2;
		}

		double cubeWidth = 1.8 * (24. / (numberOfColumns)) / 3 * 0.85;
		model.addAttribute("numberOfColumns", numberOfColumns);
		model.addAttribute("cubeWidth", cubeWidth);
		model.addAttribute("columnWidth", Math.ceil(cubeWidth) + 1);

		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("principal", user);
	}

	@GetMapping("/reservations")
	public String showReservations(Model model) {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		model.addAttribute("reservations", user.getReservations());
		model.addAttribute("principal", user);

		return "user/my_reservations";
	}

	@GetMapping("/reservation/{id}")
	public String showReservationInfo(@PathVariable("id") long id, Model model) {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Reservation reservation = reservationService.findReservationById(id);
		Hotel hotel =reservation.getRoom().getHotel();

		long numberOfColumns = Math.round(Math.sqrt(hotel.getRooms().size()));
		if (hotel.getRooms().size()==2){
			numberOfColumns = 2;
		}
		double cubeWidth = 1.8 * (24. / (numberOfColumns)) / 3 * 0.85;
		model.addAttribute("numberOfColumns", numberOfColumns);
		model.addAttribute("cubeWidth", cubeWidth);
		model.addAttribute("columnWidth", Math.ceil(cubeWidth) + 1);

		model.addAttribute("rooms", hotel.getRooms().stream()
				.sorted((room1, room2) -> (int) (room2.getId() - room1.getId()))
				.collect(Collectors.toList()));
		model.addAttribute("reservation", reservation);
		model.addAttribute("principal", user);

		return "user/reservation_info";
	}

}
