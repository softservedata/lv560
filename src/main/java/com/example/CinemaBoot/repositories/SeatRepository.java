package com.example.CinemaBoot.repositories;

import com.example.CinemaBoot.models.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {

    Optional<Seat> findById(long id);

    Optional<Seat> findByRoomIdAndNumber(long roomId, int number);

}