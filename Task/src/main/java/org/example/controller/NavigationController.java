package org.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NavigationController {

    @GetMapping({"/login", "/"})
    public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        boolean errorMessage = false;
        if (error != null) {
            errorMessage = true;
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
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

