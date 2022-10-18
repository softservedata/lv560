package com.example.CinemaBoot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rowNumber;

    private int number;

    @Transient
    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @Getter(onMethod_=@JsonBackReference)
    private Room room;

    @ManyToMany(mappedBy = "seats")
    @Getter(onMethod_=@JsonIgnore)
    private List<Book> books;

}
