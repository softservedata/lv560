package org.example.dao.impl;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.User;
import org.example.dao.UserDao;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByName(String string) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User u WHERE u.name=:name")
                .setParameter("name", string)
                .getSingleResult();
        log.info("Find user by name=" + string + ": " + user.getId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createQuery("FROM User user", User.class).getResultList();
        log.info("Get list of all users");
        return list;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        log.info("Save user with username: " + user.getName());
        session.save(user);
    }
}
