package ua.hryshko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.UserDAO;
import ua.hryshko.model.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
    @Transactional
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }


    @Transactional
    public User readById(Long id) {
        return (User) sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Transactional
    public User findByEmail(String email) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where email  = :email").setParameter("email",email).getSingleResult();
    }


    @Transactional
    public void update(User user) {
             sessionFactory.getCurrentSession().update(user);
    }


    @Transactional
    public void removeUser(Long id) {
        User user = (User) sessionFactory.getCurrentSession().load(User.class,id);
        if(user!=null ){

            sessionFactory.getCurrentSession().delete(user);
        }

    }
}
