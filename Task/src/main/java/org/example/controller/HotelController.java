package org.example.controller;

import org.example.service.CountryService;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.model.*;
import org.example.service.HotelService;
import java.util.List;


@Controller
public class HotelController {

    private final HotelService hotelService;
    private final CountryService countryService;

    @Autowired
    public HotelController(HotelService hotelService, CountryService roomService) {
        this.hotelService = hotelService;
        this.countryService = roomService;
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/addHotel")
    public String addHotelPage(@ModelAttribute(name = "hotel") Hotel hotel, Model model) {
        model.addAttribute("countryList", countryService.listOfCountries());
        return "addHotel";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @PostMapping("/addHotel")
    public String addHotelForm(Hotel hotel, @RequestParam("countryName") Integer countryId) {
        hotel.setCountry(countryService.findById(countryId));
        hotelService.saveHotel(hotel);
        return "redirect:/hotelList";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/deleteHotel/{id}")
    public String deleteHotel(@PathVariable("id") Integer id) {
        hotelService.deleteHotel(id);
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
    public String updateHotelPage(@PathVariable("id") Integer id, Model model) {
        Hotel hotel = hotelService.findById(id);
        model.addAttribute("hotel", hotel);
        return "updateHotel";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @PostMapping("/updateHotel/{id}")
    public String updateHotel(@PathVariable Integer id, Hotel hotel) {
        List<Room> list = hotelService.findById(hotel.getId()).getRooms();
        hotel.setRooms(list);
        Country country = hotelService.findById(id).getCountry();
        hotel.setCountry(country);
        hotelService.updateHotel(hotel);
        return "redirect:/hotelList";
    }

    @GetMapping("/findHotelByCountry/{country}")
    public String get(@PathVariable String country, Model model) {
        Integer id = countryService.findByName(country).getId();
        List<Hotel> list= hotelService.findByCountry(id);
        if (list.isEmpty()) {
            model.addAttribute("errorMessage", "There is no hotels in this country");
            model.addAttribute("countryList", countryService.listOfCountries());
            return "forward:/findHotelByCountry";
        }
        model.addAttribute("hotels", list);
        return "allHotelsInCountry";
    }
}
