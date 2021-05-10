package org.example.dao;

import org.example.model.Booking;
import org.example.model.Room;

import java.util.List;

public interface RoomDao {

    void saveRoom(Room room);

    void deleteRoom(Integer id);

    List<Room> getAllRooms();

    Room findById(Integer id);

    List<Room> getAllHotelRooms(Integer hotelId);

    List<Booking> getOrdersByRoom(Integer id);

    void saveRoomBooking(Booking booking);
}
