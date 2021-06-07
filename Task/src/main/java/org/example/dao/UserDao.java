package org.example.dao;

import org.example.model.User;
import java.util.List;

public interface UserDao {
    User findByName(String string);
    List<User> getAllUsers();
    void save(User user);
}
