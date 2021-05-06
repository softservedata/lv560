package org.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.dto.OrderDTO;
import org.example.model.*;
import org.example.service.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class RoomController {
    private final RoomService roomService;
    private final HotelService hotelService;
    private final UserService userService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService, UserService userService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/allHotelRooms/{id}")
    public String getHotelRooms(Model model, @PathVariable Integer id) {
        model.addAttribute("roomList", roomService.allHotelRooms(id));
        return "allHotelRooms";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/deleteRoom/{id}")
    public String deleteRoom(Model model, @PathVariable("id") Integer id) {
        Hotel hotel = roomService.findById(id).getHotel();
        List<Room> roomList = roomService.allHotelRooms(hotel.getId());
        roomService.deleteRoom(id);
        model.addAttribute("roomList", roomList);
        return "allHotelRooms";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/addRoom/{id}")
    public String addRoom(@PathVariable("id") Integer id, Model model) {
        Hotel hotel = hotelService.findById(id);
        Room room = new Room();
        room.setHotel(hotel);
        model.addAttribute("room", room);
        return "addRoom";
    }

    //fix this bicycle
    @PostMapping("/addRoom")
    public String addRoomForm(Room room, Model model) {
        roomService.saveRoom(room);
        List<Room> roomList = roomService.allHotelRooms(room.getHotel().getId());
        model.addAttribute("roomList", roomList);
        return "allHotelRooms";
    }

    @PostMapping("/allHotelRooms/{id}")
    public String allHotelRooms(@PathVariable("id") Integer id, Model model) {
        List<Room> rooms = roomService.allHotelRooms(id);
        model.addAttribute("rooms", rooms);
        return "checkRoom";
    }

    @PostMapping("/findAvailableRoom/{id}")
    public String availableRoomInHotel(Model model,
                                       @PathVariable("id") Integer id,
                                       @RequestParam(value = "checkIn") String checkin,
                                       @RequestParam(value = "checkOut") String checkout,
                                       @RequestParam(value = "hotelId")Integer hotelId) {

        Room room = roomService.findById(id);
        boolean isAvailable = false;
        try {
            isAvailable = roomService.availableRoomsInHotel(new OrderDTO(room, LocalDate.parse(checkin),
                    LocalDate.parse(checkout)));
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("rooms", roomService.allHotelRooms(hotelId));
            return "checkRoom";
        }

        if (isAvailable) {
            model.addAttribute("errorMessage", "");
            model.addAttribute("id", id);
            model.addAttribute("arrivalDate", checkin);
            model.addAttribute("departureTime", checkout);
            return "bookRoom";
        } else {
            model.addAttribute("errorMessage", "The room is not available!");
            model.addAttribute("rooms", roomService.allHotelRooms(hotelId));
            return "checkRoom";
        }
    }

    @PostMapping("/findAvailableRoom/{id}/roomBooking")
    public String bookingRoom(@PathVariable("id") Integer id,
                              @RequestParam("checkIn")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
                              @RequestParam("checkOut")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
                              Principal principal) {
        User user = userService.findByName(principal.getName());
        Room room = roomService.findById(id);
        Booking booking = new Booking(checkin, checkout, room, user);
        roomService.saveRoomBooking(booking);
        return "successBooking";
    }
}

