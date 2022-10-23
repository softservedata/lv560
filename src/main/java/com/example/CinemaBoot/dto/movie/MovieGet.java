package com.example.CinemaBoot.dto.movie;

import com.example.CinemaBoot.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieGet {

    private long id;

    private String name;

    private String genre;

    public MovieGet(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.genre = movie.getGenre();
    }

}
