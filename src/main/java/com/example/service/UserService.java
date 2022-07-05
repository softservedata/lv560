package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("passwordEncoder")PasswordEncoder passwordEncoder, UserDao userDao){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(Integer id){
        return userDao.findById(id);
    }

    public User findByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public void saveUser(User user){
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        userDao.save(user);
    }

    public void updateUser(User user){
        userDao.update(user);
    }

    public void deleteUser(User user){
        userDao.delete(user);
    }

    public boolean userExists(User user){
        return userDao.getAllUsers().stream()
                .anyMatch(u -> u.getUsername().equals(user.getUsername()));
    }

}
