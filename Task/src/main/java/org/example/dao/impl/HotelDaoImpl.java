package org.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.*;
import org.example.dao.HotelDao;


import javax.persistence.EntityNotFoundException;
import java.util.List;


@Repository
public class HotelDaoImpl implements HotelDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HotelDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hotel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> findByCountry(String string) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery("from Hotel h where h.countryName=:s")
                    .setParameter("s", string)
                    .list();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("We could not find any hotels in this country");
        }


    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> listOfHotels() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Hotel r").list();
    }


    @Override
    public Hotel findById(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Hotel.class, integer);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(hotel);
    }

    @Override
    public void deleteHotel(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.get(Hotel.class, integer);
        session.delete(hotel);
    }
}
