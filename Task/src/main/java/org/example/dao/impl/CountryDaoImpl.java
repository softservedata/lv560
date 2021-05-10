package org.example.dao.impl;

import org.example.dao.CountryDao;
import org.example.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CountryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Country.class, id);
    }

    @Override
    public Country findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Country country WHERE name = :name", Country.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Country> listOfCountries() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Country", Country.class)
                .getResultList();
    }
}
