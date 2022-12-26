package ua.hryshko.dao;

import ua.hryshko.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

     List<User> listUsers();

     User readById(Long id);

     void update(User user);

     void removeUser(Long id);

     User findByEmail(String userEmail);
}
