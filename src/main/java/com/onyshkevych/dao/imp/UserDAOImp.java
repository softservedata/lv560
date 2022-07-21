package com.onyshkevych.dao.imp;

import com.onyshkevych.dao.UserDAO;
import com.onyshkevych.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
public class UserDAOImp implements UserDAO {

    private final SessionFactory sessionFactory;


    @Autowired
    public UserDAOImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User", User.class);

        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, name);
        return user;

    }

    @Override
    public User getUserByRole(Integer user_role) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, user_role);
        return user;
    }

    @Override
    public User getUserById(Integer user_id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, user_id);
        return user;

    }
}
