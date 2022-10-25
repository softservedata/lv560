package org.bn.travel_agency.web.controllers;

import org.bn.travel_agency.entities.Hotel;
import org.bn.travel_agency.entities.Location;
import org.bn.travel_agency.entities.Reservation;
import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.services.HotelService;
import org.bn.travel_agency.services.LocationService;
import org.bn.travel_agency.services.ReservationService;
import org.bn.travel_agency.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private ReservationService reservationService;

	@GetMapping(value = "/users")
	public String showUsers(Model model) {
		return "redirect:users/page_1";
	}

	@GetMapping("/users/page_{number}")
	public String showUsersPageable(@PathVariable("number") int number, Model model) {
		model.addAttribute("users", userService.findAllUsers(number - 1, 101));
//		model.addAttribute("message", userService.findAllUsers().size());
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/index";
	}

	@GetMapping("/user/{id}")
	public String showUserInfo(@PathVariable("id") long id, Model model) {
		User user = userService.findUserById(id);

		model.addAttribute("user", user);
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/user_info";
	}

	@PostMapping("/user/{id}/update")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable long id) {
		userService.updateUserById(id, user);
		return "redirect:/admin/users";
	}

	@PostMapping("/user/{id}/delete")
	public String deleteUser(@PathVariable long id) {
		userService.deleteUserById(id);
		return "redirect:/admin/users";
	}

	@GetMapping(value = "/hotels")
	public String showHotels(Model model) {
		return "redirect:hotels/page_1";
	}

	@GetMapping("/hotels/page_{number}")
	public String showHotelsPageable(@PathVariable("number") int number, Model model) {

		model.addAttribute("hotels", hotelService.findAllHotels(number - 1, 101));
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/hotels";
	}

	@GetMapping("/hotels/new_hotel")
	public String addHotel(Model model) {

		model.addAttribute("hotel", new Hotel());
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/new_hotel";
	}

	@PostMapping("/hotels/save")
	public String saveHotel(@ModelAttribute("hotel") Hotel hotel, Model model) {

		Location location = locationService.findLocationByName(hotel.getLocationName());
		if (location == null) {
			locationService.save(new Location(hotel.getLocationName()));
		}
		location = locationService.findLocationByName(hotel.getLocationName());
		hotel.setLocation(location);
		hotelService.save(hotel);
		hotelService.createRooms(hotel, hotel.getNumberOfRooms(), hotel.getPriceForRoom());
		hotelService.save(hotel);
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "redirect:/admin/hotels";
	}

	@GetMapping("/hotel/{id}")
	public String showHotelInfo(@PathVariable("id") long id, Model model) {
		Hotel hotel = hotelService.findHotelById(id);
		if (hotel.getLocation() != null)
			hotel.setLocationName(hotel.getLocation().getName());

		model.addAttribute("hotel", hotel);
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/hotel_info";
	}

	@PostMapping("/hotel/{id}/update")
	public String updateUser(@ModelAttribute("hotel") Hotel hotel, @PathVariable long id) {
		hotelService.updateHotelById(id, hotel);
		return "redirect:/admin/hotels";
	}

	@PostMapping("/hotel/{id}/delete")
	public String deleteHotel(@PathVariable long id) {
		hotelService.deleteHotelById(id);
		return "redirect:/admin/hotels";
	}

	@GetMapping(value = "/reservations")
	public String showReservations(Model model) {
		return "redirect:reservations/page_1";
	}

	@GetMapping("/reservations/page_{number}")
	public String showReservationsPageable(@PathVariable("number") int number, Model model) {
		model.addAttribute("reservations", reservationService.findAllReservations(number - 1, 101));
		model.addAttribute("principalName", SecurityContextHolder.getContext().getAuthentication().getName());

		return "admin/user_reservations";
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
		model.addAttribute("principalName", user.getUsername());

		return "admin/reservation_info";
	}

	@PostMapping("/reservation/{id}/cancel")
	public String cancelReservation(@PathVariable("id") long id, Model model) {

		Reservation reservation = reservationService.findReservationById(id);

		reservationService.delete(reservation);

		return "redirect:/admin/reservations";
	}
}
