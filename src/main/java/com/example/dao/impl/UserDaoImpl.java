package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .getSingleResult();

        logger.info("found user with id: {}", id);
        return user;
    }

    @Override
    public User findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.createQuery("FROM User user WHERE user.username = :username")
                .setParameter("username", userName)
                .getSingleResult();

        logger.info("found user with username: {}", userName);

        return user;

    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createQuery("From User u").getResultList();

        logger.info("found list of users");
        return list;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);

        logger.info("saved user");
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);

        logger.info("deleted user");
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);

        logger.info("updated user");
    }
}
