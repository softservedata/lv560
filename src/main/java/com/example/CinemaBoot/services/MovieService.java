package com.example.CinemaBoot.services;

import com.example.CinemaBoot.dto.movie.MovieGet;
import com.example.CinemaBoot.exceptions.NotFoundException;
import com.example.CinemaBoot.models.Movie;
import com.example.CinemaBoot.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public MovieGet getById(long id) {
        return new MovieGet(findById(id));
    }

    @Transactional(readOnly = true)
    public Movie findById(long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new NotFoundException("Movie not found for id=" + id);
        }
        return movie.get();
    }

}
