package ua.hryshko.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.RoleDAO;
import ua.hryshko.model.Role;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Transactional
    public List<Role> listRole() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();

    }

    @Transactional
    public Role readById (Long id) {
        return (Role) sessionFactory.getCurrentSession().load(Role.class, id);
    }


    @Override
    public void removeRole(Long id) {
        Role role = (Role) sessionFactory.getCurrentSession().load(Role.class, id);
        if (role != null) {
            sessionFactory.getCurrentSession().delete(role);
        }
    }
}
