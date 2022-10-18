package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.exceptions.UserNotFoundException;
import com.example.CinemaBoot.models.Room;
import com.example.CinemaBoot.models.User;
import com.example.CinemaBoot.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        Optional<User> user = userService.getById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for id=" + id);
        }
        return user.get();
    }

}
