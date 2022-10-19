package com.example.CinemaBoot.repositories;

import com.example.CinemaBoot.models.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

    List<Session> findAll();

    List<Session> findAllByDate(Date date);

    Optional<Session> findByDateAndTime(Date date, Date time);

    List<Session> findDistinctByDateAfter(Date startDate);

}
