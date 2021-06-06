package org.example.dao.impl;

import org.example.dao.CountryDao;
import org.example.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

    private final Logger log = LoggerFactory.getLogger(CountryDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public CountryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Country country = session.get(Country.class, id);
        log.info("Get country by id: " + country);
        return country;
    }

    @Override
    public Optional<Country> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        log.info("Get country by name " + name);
        try {
            return Optional.of(session.createQuery("FROM Country country WHERE name = :name", Country.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Country> listOfCountries() {
        Session session = sessionFactory.getCurrentSession();
        List<Country> list = session.createQuery("FROM Country", Country.class)
                .getResultList();
        log.info("Get list of countries " + list);
        return list;
    }
}
