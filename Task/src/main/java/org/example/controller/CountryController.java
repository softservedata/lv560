package org.example.controller;

import org.example.model.Country;
import org.example.model.Hotel;
import org.example.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class CountryController {

    private final CountryService countryService;

    @Autowired
    CountryController(CountryService service) {
        this.countryService = service;
    }

    @GetMapping("/findHotelByCountry")
    public String findByCountry(Model model) {
        List<Country> countries = countryService.listOfCountries();
        model.addAttribute("countryList", countries);
        return "findByCountry";
    }

    @PostMapping("/findHotelByCountry")
    public String findByCountryName(@RequestParam("name") String country) {
        return "redirect:/findHotelByCountry/" + country;
    }
}
