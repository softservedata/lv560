package org.pupa.controllers;

import org.pupa.models.Role;
import org.pupa.models.User;
import org.pupa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String homePage(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("username",user.getUsername());
        model.addAttribute("isManager", user.isManager());
        return "index.html";
    }

    // Login form
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userData", new User());
        return "register.html";
    }

    @PostMapping("/register")
    public String addUserToDB(Model model,@ModelAttribute(name = "userData") User user){
        User foundPerson = userService.findByUsername(user.getUsername());
        if(foundPerson != null || user.getUsername().length() < 4 || user.getPassword().length() < 4){
            model.addAttribute("registerError", true);
            return "register.html";
        }
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout.html";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied.html";
    }
}
