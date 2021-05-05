package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.model.User;
import org.example.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allBookings")
    public String allUserAndOrders(Model model) {
        List<User> users = userService.allUserAndOrders();
        model.addAttribute("users",users);
        return "allBookings";
    }
}
