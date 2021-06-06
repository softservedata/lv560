package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.dto.OrderDTO;
import org.example.model.Booking;
import org.example.model.Room;
import org.example.dao.RoomDao;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService {
    private final RoomDao roomDao;

    @Autowired
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }


    public void saveRoom(Room room) {
        roomDao.saveRoom(room);
    }

    public Room findById(Integer integer) {
        return roomDao.findById(integer);
    }

    public void deleteRoom(Integer integer) {
        roomDao.deleteRoom(integer);
    }

    public List<Room> allHotelRooms(Integer integer) {
        return roomDao.getAllHotelRooms(integer);
    }

    public boolean availableRoomsInHotel(OrderDTO order) throws IllegalArgumentException {
        if (order.getArrivalTime().isEqual(order.getDepartureTime()) ||
                order.getArrivalTime().isAfter(order.getDepartureTime()) ||
                order.getArrivalTime().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Your time borders is not correct.");
        }

        List<Booking> bookings = order.getRoom().getBookings();
        List<Booking> filter = bookings
                .stream()
                .filter(booking -> !(order.getDepartureTime().isBefore(booking.getCheckIn()) ||
                        order.getArrivalTime().isAfter(booking.getCheckOut())))
                .collect(Collectors.toList());
        return filter.isEmpty();
    }

    public void saveRoomBooking(Booking booking) {
        roomDao.saveRoomBooking(booking);
    }
}
