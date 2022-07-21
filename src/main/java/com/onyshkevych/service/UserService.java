package com.onyshkevych.service;

import com.onyshkevych.model.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {


    @Transactional
    String saveUser(User user);

    @Transactional
    List<User> getAllUsers();
    /**
     * Check is user already registered. If so - than transfers him to the page he need.
     *
     * @param  name   username to check if it is in database
     * @param  password   password to check if it is suitable to the username
     * @return         page user needed
     */

    @Transactional
    String loginUser(String name, String password);

    @Transactional
    User getUserByName(String name);

    @Transactional
    User getUserById(Integer user_id);

    @Transactional
    boolean isUserLogined(String userName, String userPass);

    /**
     * Registration user method. If Username isn't already in use -
     * it registers user and return him to the login page
     *
     * @param  username   username to check if it is not in database
     * @param  userpass   password to check if it is more than 8 symbols length
     * @return         page user needed
     */

    @Transactional
    String registerUser(String username, String userpass);

    @Transactional
    String loginManager(String manager_name, String manager_pass);
    /**
     * Method helps to get user info with his id.
     * @param  user_id   id of current user
     * @param  m   context
     * @return         page with user and his information
     */
    @Transactional
    String getUserInfo(Integer user_id, Model m);
}
