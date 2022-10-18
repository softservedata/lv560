package com.example.CinemaBoot.services;

import com.example.CinemaBoot.models.Room;
import com.example.CinemaBoot.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(long id) {
        return roomRepository.findById(id);
    }

}
