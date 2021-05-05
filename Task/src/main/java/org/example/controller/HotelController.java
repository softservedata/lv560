package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.model.*;
import org.example.service.HotelService;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/addHotel")
    public String addHotelPage(@ModelAttribute(name = "hotel") Hotel hotel) {
        return "addHotel";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @PostMapping("/addHotel")
    public String addHotelForm(Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotelList";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("deleteHotel/{id}")
    public String deleteHotel(@PathVariable("id") Integer integer) {
        hotelService.deleteHotel(integer);
        return "redirect:/hotelList";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/hotelList")
    public String hotelListPage(Model model) {
        List<Hotel> hotelList = hotelService.listOfHotels();
        model.addAttribute("hotels", hotelList);
        return "hotelList";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/updateHotel/{id}")
    public String updateHotelPage(@PathVariable("id") Integer integer, Model model) {
        Hotel hotel = hotelService.findById(integer);
        model.addAttribute("hotel", hotel);
        return "updateHotel";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @PostMapping("/updateHotel")
    public String updateHotel(Hotel hotel) {
        System.out.println(hotel);
        hotelService.updateHotel(hotel);
        return "redirect:/hotelList";
    }

    @GetMapping("/findHotelByCountry")
    public String findByCountry() {
        return "findByCountry";
    }

    @PostMapping("/findHotelByCountry")
    public String findByCountry(Model model, @RequestParam(value = "countryName") String string) {
        List<Hotel> hotels = hotelService.findByCountry(string);
        if (hotels.isEmpty()) {
            model.addAttribute("isEmpty", false);
            return "findByCountry";
        } else {
            model.addAttribute("hotels", hotels);
            return "allHotelsInCountry";
        }
    }
}
