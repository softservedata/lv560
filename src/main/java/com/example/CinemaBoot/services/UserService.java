package com.example.CinemaBoot.services;

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

    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

}
