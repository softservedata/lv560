package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.exceptions.RoomNotFoundException;
import com.example.CinemaBoot.models.Room;
import com.example.CinemaBoot.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAll();
    }

    @RequestMapping("/{id}")
    public Room getRoomById(@PathVariable long id) {
        Optional<Room> room = roomService.getById(id);
        if (room.isEmpty()) {
            throw new RoomNotFoundException("Room not found for id:" + id);
        }
        return room.get();
    }

}
