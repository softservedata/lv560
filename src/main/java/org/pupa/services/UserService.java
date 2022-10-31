package org.pupa.services;

import org.hibernate.Hibernate;
import org.pupa.models.*;
import org.pupa.repositories.RoleRepository;
import org.pupa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService{
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //user.addRole(new Role("ROLE_USER"));
        user.addRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    public void updateUsernameAndRolesOfUser(User user){
        User foundUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + user.getId()));

        HashSet<Role> newRoles = new HashSet<>();
        for (Role r: user.getRoles()
             ) {
            newRoles.add(roleRepository.findByName(r.getName()));
        }
        foundUser.setRoles(newRoles);

        foundUser.setUsername(user.getUsername());
        userRepository.save(user);
    }
    
    public void setUsernameById(String username, Long id){
        userRepository.setUsernameById(username,id);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    @Transactional
    public Optional<User> findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        Hibernate.initialize(user.getPassedTests());
        for (PassedTest p: user.getPassedTests()
             ) {
            Hibernate.initialize(p.getTest());
            Hibernate.initialize(p.getTest().getQuestions());
        }
        Hibernate.initialize(user.getRoles());
        return Optional.of(user);
    }

    @Transactional
    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        for (User user: users
             ) {
            Hibernate.initialize(user.getRoles());
            Hibernate.initialize(user.getPassedTests());
        }
        return users;
    }

    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user != null){
            Hibernate.initialize(user.getPassedTests());
            for (PassedTest p: user.getPassedTests()
            ) {
                Hibernate.initialize(p.getTest());
                Hibernate.initialize(p.getTest().getQuestions());
            }
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }
}
