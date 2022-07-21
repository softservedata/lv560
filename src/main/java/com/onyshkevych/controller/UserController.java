package com.onyshkevych.controller;

import com.onyshkevych.service.ResultService;
import com.onyshkevych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResultService resultService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String userpass) {
        return userService.registerUser(username, userpass);
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String name, @RequestParam String password) {
        return userService.loginUser(name,password);

    }

    @PostMapping("/admin/login")
    public String loginManager(@RequestParam String name,
                               @RequestParam String password) {
        return userService.loginManager(name,password);

    }

    @PostMapping("/admin/users")
    public String viewAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @PostMapping("/admin/users/checkres")
    public String viewUsersResult(@RequestParam String name, Model model) {
        return resultService.viewUsersResult(name, model);
    }

    @PostMapping("/admin/users/getuserinfo")
    public String getUserInfo(@RequestParam Integer user_id, Model m) {
        return userService.getUserInfo(user_id, m);
    }
}
