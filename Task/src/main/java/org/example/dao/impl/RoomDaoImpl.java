package org.example.dao.impl;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.example.model.*;
import org.example.dao.RoomDao;

import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Room> saveRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.save(room);
        return session.createQuery("from Room r where r.hotel.id=:id")
                .setParameter("id", room.getHotel().getId())
                .getResultList();
    }

    @Override
    public Room findById(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Room.class, integer);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Room> deleteRoom(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.get(Room.class, integer);
        session.delete(room);
        return session.createQuery("from Room r where r.hotel.id=:id")
                .setParameter("id", room.getHotel().getId())
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Room> getAllHotelRooms(Integer integer) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Room r where hotel.id=:id")
                .setParameter("id", integer)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Booking> getOrdersByRoom(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Booking b where b.room.id=:id")
                .setParameter("id", id).getResultList();
    }

    @Override
    public void saveRoomBooking(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.save(booking);
    }
}
