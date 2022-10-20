package com.example.CinemaBoot.repositories;

import com.example.CinemaBoot.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> findById(long id);

}