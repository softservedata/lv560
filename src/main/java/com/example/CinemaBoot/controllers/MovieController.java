package com.example.CinemaBoot.controllers;

import com.example.CinemaBoot.dto.movie.MovieGet;
import com.example.CinemaBoot.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping("/all")
    public List<MovieGet> getAll() {
        return movieService.getAll();
    }

}
