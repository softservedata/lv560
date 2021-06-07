package org.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.*;
import org.example.dao.HotelDao;


import javax.persistence.EntityNotFoundException;
import java.util.List;


@Repository
public class HotelDaoImpl implements HotelDao {

    private final Logger log = LoggerFactory.getLogger(HotelDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public HotelDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.save(hotel);
        log.info("Save hotel: id=" + hotel.getId());
    }

    @Override
    public List<Hotel> findByCountry(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        log.info("Find hotels from country with id=" + id);
        try {
            return session.createQuery("FROM Hotel h WHERE country_id=:id", Hotel.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("We could not find any hotels in this country");
        }
    }

    @Override
    public List<Hotel> listOfHotels() {
        Session session = sessionFactory.getCurrentSession();
        List<Hotel> list = session.createQuery("FROM Hotel r", Hotel.class).list();
        log.info("Get list of hotels");
        return list;
    }


    @Override
    public Hotel findById(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.get(Hotel.class, integer);
        log.info("Find hotel by id=" + integer);
        return hotel;
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(hotel);
        log.info("Update hotel: id=" + hotel.getId());
    }

    @Override
    public void deleteHotel(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.get(Hotel.class, integer);
        List<Room> rooms = hotel.getRooms();
        for (Room room: rooms) {
            room.getBookings().clear();
        }
        log.info("Delete hotel: id=" + hotel.getId());
        session.delete(hotel);
    }
}
