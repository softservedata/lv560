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
//@Table(name = "readers", schema = "public")
@Table(name = "readers")
public class Reader implements Serializable {

    @Id
    @Column(name = "id")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @ManyToMany(mappedBy = "readers", fetch = FetchType.EAGER)
    //@ManyToMany(mappedBy = "readers", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

}
