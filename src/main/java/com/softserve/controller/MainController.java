package com.softserve.controller;

import com.softserve.dto.PersonDto;
import com.softserve.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    private PersonService personService;

    @Autowired
    public MainController(PersonService personService) {
        this.personService = personService;
    }

//    @GetMapping({"/", "/index"})
//    public String index() {
//        System.out.println("***message = " + message);
//        return "index.html";
//    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {
        model.addAttribute("persons", personService.getAll());
        return "personList";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName("Type FirstName");
        model.addAttribute("personDto", personDto);
        return "addPerson";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("PersonDto")
                             PersonDto personDto) {
        if (personDto.getFirstName() != null && personDto.getFirstName().length() > 1
                && personDto.getLastName() != null && personDto.getLastName().length() > 1) {
            personService.add(personDto);
            return "redirect:/personList";
        }
        System.out.println("***ERROR, errorMessage = " + errorMessage);
        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

}
