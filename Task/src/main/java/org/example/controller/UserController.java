package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('all_permissions')")
    @GetMapping("/allBookings")
    public String allUserAndOrders(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "allBookings";
    }
}
