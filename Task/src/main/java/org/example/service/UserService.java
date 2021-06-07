package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.model.User;
import org.example.dao.UserDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserDao userDao, @Qualifier("passwordEncoder") PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }


    public User findByName(String string) {
        return userDao.findByName(string);
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void saveUser(User user) {
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        userDao.save(user);
    }

    public boolean isUserExists(User user) {
        List<User> users = userDao.getAllUsers();
        return users.stream().anyMatch(u -> u.getName().equals(user.getName()));
    }
}
