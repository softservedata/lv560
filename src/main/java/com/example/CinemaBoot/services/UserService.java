package com.example.CinemaBoot.services;

import com.example.CinemaBoot.exceptions.UserNotFoundException;
import com.example.CinemaBoot.models.User;
import com.example.CinemaBoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for id=" + id);
        }
        return user.get();
    }

}
