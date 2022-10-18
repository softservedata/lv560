package com.softserve.controller;

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


    @GetMapping({"/", "/index"})
    public String index() {
        System.out.println("***message = " + message);
        return "index.html";
    }

}
