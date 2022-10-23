package com.example.CinemaBoot.services;

import com.example.CinemaBoot.dto.room.RoomCreate;
import com.example.CinemaBoot.exceptions.RoomNotFoundException;
import com.example.CinemaBoot.models.Room;
import com.example.CinemaBoot.models.Seat;
import com.example.CinemaBoot.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Room getById(long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new RoomNotFoundException("Room not found for id:" + id);
        }
        return room.get();
    }

    @Transactional
    public Map<String, Long> createRoom(RoomCreate roomDto) {
        Room room = new Room();
        room.setName(roomDto.getName());

        List<Seat> seatList = new ArrayList<>();
        for (int i = 1; i <= roomDto.getSeatNumbers(); i++) {
            Seat seat = new Seat();
            seat.setRoom(room);
            seatList.add(seat);
        }

        room.setSeats(seatList);
        long id = roomRepository
                .save(room)
                .getId();
        return Map.of("id", id);
    }

}