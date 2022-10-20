package com.example.CinemaBoot.services;

import com.example.CinemaBoot.dto.UserGet;
import com.example.CinemaBoot.exceptions.UserNotFoundException;
import com.example.CinemaBoot.models.User;
import com.example.CinemaBoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserGet> getAll() {
        return userRepository.findAll().stream().map(UserGet::new).collect(Collectors.toList());
    }

    public UserGet getById(long id) {
        return new UserGet(findById(id));
    }

    public User findById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found for id=" + id);
        }
        return user.get();
    }

}
