package com.example.CinemaBoot.repositories;

import com.example.CinemaBoot.models.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findAll();

    Optional<Room> findById(long id);

}