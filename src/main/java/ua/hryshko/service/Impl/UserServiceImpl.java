package ua.hryshko.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.UserDAO;
import ua.hryshko.model.User;
import ua.hryshko.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Transactional
    public User readById(Long id) {
        return userDAO.readById(id);
    }

    @Transactional
    public void update(User user) {
         userDAO.update(user);
    }

    @Transactional
    public User findByEmail(String userEmail) {
        return userDAO.findByEmail(userEmail);
    }


    @Transactional
    public void removeUser(Long id) {
        userDAO.removeUser(id);
    }
}
