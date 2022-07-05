package com.example.dao;

import com.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * @param id - id that will be inserted into query
     * @return User object
     */
    User findById(Integer id);

    /**
     * @param userName - username that will be inserted into query
     * @return User object
     */
    User findByUserName(String userName);

    /**
     * @return List<Users>, containing all users
     */
    List<User> getAllUsers();

    /**
     * Saves user in database
     * @param user - user to save
     */
    void save(User user);

    /**
     * @param user - user to delete
     */
    void delete(User user);

    /**
     * @param user - user to update
     */
    void update(User user);
}
