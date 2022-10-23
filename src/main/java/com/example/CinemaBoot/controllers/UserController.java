package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.dto.user.UserGet;
import com.example.CinemaBoot.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserGet> getAllUsers() {
        logger.info("GET /api/admin/user/all");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserGet getUserById(@PathVariable long id) {
        logger.info("GET /api/admin/user/" + id);
        return userService.getById(id);
    }

}
