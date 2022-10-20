package com.example.CinemaBoot.services;

import com.example.CinemaBoot.exceptions.RoomNotFoundException;
import com.example.CinemaBoot.models.Room;
import com.example.CinemaBoot.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

}
