package org.example.dao.impl;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.User;
import org.example.dao.UserDao;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByName(String string) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createQuery("FROM User u WHERE u.name=:name")
                .setParameter("name", string)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User user", User.class).getResultList();
    }
}
