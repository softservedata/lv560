package com.example.CinemaBoot.services;

import com.example.CinemaBoot.exceptions.SeatNotFoundException;
import com.example.CinemaBoot.models.Seat;
import com.example.CinemaBoot.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Transactional(readOnly = true)
    public Seat getById(long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isEmpty()) {
            throw new SeatNotFoundException("No seat for id=" + id);
        }
        return seat.get();
    }

    @Transactional(readOnly = true)
    public Seat getByRoomIdAndNumber(long roomId, int number) {
        Optional<Seat> seat = seatRepository.findByRoomIdAndNumber(roomId, number);
        if (seat.isEmpty()) {
            throw new SeatNotFoundException("No seat for room id=" + roomId + ", and number=" + number);
        }
        return seat.get();
    }

}
