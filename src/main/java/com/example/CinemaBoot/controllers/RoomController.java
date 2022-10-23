package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.dto.room.RoomCreate;
import com.example.CinemaBoot.dto.room.RoomGet;
import com.example.CinemaBoot.models.Room;
import com.example.CinemaBoot.services.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    RoomService roomService;

    @GetMapping("/all")
    public List<RoomGet> getAllRooms() {
        logger.info("GET /api/room/all");
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public RoomGet getRoomById(@PathVariable long id) {
        logger.info("GET /api/room/" + id);
        return roomService.getById(id);
    }

    @PostMapping("/create")
    public Map<String, Long> createRoom(@RequestBody RoomCreate roomDto) {
        logger.info("GET /api/room/create, room=" + roomDto);
        return roomService.createRoom(roomDto);
    }

}
