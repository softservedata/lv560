package com.example.CinemaBoot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter(onMethod_=@JsonIgnore)
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    @Getter(onMethod_=@JsonManagedReference)
    private Session session;

    @ManyToMany
    @JoinTable(
            name = "book_seat",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    List<Seat> seats;

}
