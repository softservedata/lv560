package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Role;
import com.example.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/login", "/"})
    public String login(Model model, @RequestParam(value = "error", required = false) String error){
        boolean errorMessage = false;
        if (error != null){
            errorMessage = true;
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping({"/registration"})
    public String registration(Model model){
        return "signUp";
    }

    @PostMapping({"/registration"})
    public String addUser(@RequestParam("username")String username,
                          @RequestParam("full_name")String fullName,
                          @RequestParam("password")String password,
                          @RequestParam("email")String email,
                          Model model){
        User user = User.builder()
                .username(username)
                .password(password)
                .fullName(fullName)
                .email(email)
                .status(Status.ACTIVE)
                .role(Role.USER)
                .build();

        if (userService.userExists(user)){
            model.addAttribute("message", "User already exists");
            return "signUp";
        }

        userService.saveUser(user);
        return "mainPage";
    }

    @GetMapping("/mainPage")
    public String index(){
        return "mainPage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
}
