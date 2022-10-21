package com.example.CinemaBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = { "/", "/index" })
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = { "/admin" })
    public String admin(Model model) {
        return "admin";
    }

}
