package com.example.CinemaBoot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String genre;

    @OneToMany(mappedBy = "movie")
    @Getter(onMethod_=@JsonBackReference)
    private List<Session> sessions;

}
