package com.onyshkevych.dao;

import com.onyshkevych.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface UserDAO {
    /**
     * Saving User in db
     *  @param  user  user entity
     */
    void saveUser(User user);
    /**
     * Getting all users from db.
     *
     */
    List<User> getAllUsers();
    /**
     * Getting user entity by name.
     *
     * @param  name   name of user you need
     * @return         User entity
     */
    @Transactional
    User getUserByName(String name);
    /**
     * Getting user entity by role.
     *
     * @param  user_role   role of user you need
     * @return         User entity
     */
    @Transactional
    User getUserByRole(Integer user_role);
    /**
     * Getting user entity by id.
     *
     * @param  user_id   id of user you need
     * @return         User entity
     */
     User getUserById(Integer user_id);
}
