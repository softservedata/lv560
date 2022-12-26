package ua.hryshko.service;

import ua.hryshko.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> listUsers();

    User readById(Long id);

    void update(User user);

    User findByEmail(String userEmail);

    void removeUser(Long id);
}
