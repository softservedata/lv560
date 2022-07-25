package com.project.soft.service;

import com.project.soft.dao.RoleRepository;
import com.project.soft.dao.UserRepository;
import com.project.soft.entity.Role;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void save(User user) {
        Role role;
        String email = user.getEmail();

        if (email.contains("@admin")) {
            role = roleRepository.findRoleByName("manager");
        } else {
            role = roleRepository.findRoleByName("user");
        }
        user.setRole(role);

        userRepository.save(user);
    }

    public List<User> findUsersByTest(Test test) {
        if (test == null)
            throw new IllegalArgumentException("Test can't be null");

        return userRepository.findUsersByTest(test);
    }

    public User findUserById(Long id) {

        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("There's no user with id '%d'", id)
                )
        );
    }

}
