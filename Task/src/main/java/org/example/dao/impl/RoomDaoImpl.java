package org.example.dao.impl;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.*;
import org.example.dao.RoomDao;

import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao {

    private final Logger log = LoggerFactory.getLogger(HotelDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public RoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.save(room);
        log.info("Save room with id " + room.getId());
    }

    @Override
    public Room findById(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.get(Room.class, integer);
        log.info("Find room by id=" + integer);
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> list = sessionFactory.getCurrentSession()
                .createQuery("SELECT r FROM Room r", Room.class)
                .getResultList();
        log.info("Get room list");
        return list;
    }


    @Override
    public void deleteRoom(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.get(Room.class, integer);
        log.info("Delete room(id = " + integer + ") " );
        session.delete(room);
    }

    @Override
    public List<Room> getAllHotelRooms(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        List<Room> list = session.createQuery("FROM Room r WHERE hotel.id=:id", Room.class)
                .setParameter("id", integer)
                .getResultList();
        log.info("Get all room in hotel(hotel_id=" + integer + "): ");
        return list;
    }

    @Override
    public List<Booking> getOrdersByRoom(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<Booking> list = session.createQuery("FROM Booking b WHERE b.room.id=:id", Booking.class)
                .setParameter("id", id).getResultList();
        log.info("Get orders for room(id=" + id + ")");
        return list;
    }

    @Override
    public void saveRoomBooking(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.save(booking);
        log.info("Save room booking");
    }
}
