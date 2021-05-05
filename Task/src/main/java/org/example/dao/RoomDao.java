package org.example.dao;

import org.example.model.Booking;
import org.example.model.Room;

import java.util.List;

public interface RoomDao {

    List<Room> saveRoom(Room room);

    Room findById(Integer integer);

    List<Room> deleteRoom(Integer integer);

    List<Room> getAllHotelRooms(Integer integer);

    List<Booking> getOrdersByRoom(long id);

    void saveRoomBooking(Booking booking);
}
