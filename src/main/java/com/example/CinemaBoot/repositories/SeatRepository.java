package com.example.CinemaBoot.repositories;

import com.example.CinemaBoot.models.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {
}