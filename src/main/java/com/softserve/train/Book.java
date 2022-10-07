package com.softserve.train;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@With
@Entity
//@Table(name = "books", schema = "public")
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy = GenerationType.AUTO) // Manage Hibernate,
    // start with ...
    // @GeneratedValue(generator="increment")
    // @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @Column(name = "title", length = 64)
    private String title;

    //@ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Reader> readers = new ArrayList<>();

}
