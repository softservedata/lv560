package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.utils.*;

@Controller
public class NavigationController {

    private final UserService userService;

    @Autowired
    public NavigationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/login", "/"})
    public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        boolean errorMessage = false;
        if (error != null) {
            errorMessage = true;
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/registration")
    public String userRegistration(Model model) {
//        model.addAttribute("errorMessage", "");
        return "signUp";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model) {
        User user = User.builder().
                name(username).
                password(password).
                status(Status.ACTIVE).
                role(Role.USER).
                build();
        if (userService.isUserExists(user)) {
            model.addAttribute("message", "User is already exist");
            return "signUp";
        }

        userService.saveUser(user);
        return "redirect:/mainMenu";
    }

    @GetMapping("/mainMenu")
    public String mainMenuPage() {
        return "mainMenu";
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/management")
    public String managementPage() {
        return "managementPage";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "forbidden";
    }
}

