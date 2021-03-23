package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.entity.Person;
import com.softserve.edu.service.OperationService;
import com.softserve.edu.service.PersonService;

@Controller
public class PersonController {

    private PersonService personService;
    private OperationService operationService;

    @Value("${welcome.message}")
    private String message;

    @Autowired
    public PersonController(PersonService personService,
            @Qualifier("AddOperationService")
            OperationService operationService) {
        this.personService = personService;
        this.operationService = operationService;
    }
    
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/info")
    public String getPersonInfo(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "ivan")
            String name) {
        List<Person> persons = personService.findByName(name);
        model.addAttribute("name", persons.size() > 0 ? persons.toString() : "nothing");
        return "info";
    }
    
    @GetMapping("/add")
    public String addPerson(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "ivan")
            String name,
            @RequestParam(value = "city", required = false, defaultValue = "Kyiv")
            String city) {
        if (personService.addPerson(new Person(name, city))) {
            model.addAttribute("message", "added successfully");
        } else {
            model.addAttribute("message", "failed to add");
        }
        return "index";
    }

    @GetMapping("/operation")
    public String getOperation(Model model,
            @RequestParam(value = "arg0", required = false, defaultValue = "0")
            String arg0,
            @RequestParam(value = "arg1", required = false, defaultValue = "0")
            String arg1) {
        String result = operationService.operation(arg0, arg1);
        model.addAttribute("name", result);
        return "info";
    }
}
