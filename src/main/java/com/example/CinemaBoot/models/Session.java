package com.example.CinemaBoot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "session_movie",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @Getter(onMethod_=@JsonManagedReference)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @OneToMany(mappedBy = "session")
    @Getter(onMethod_=@JsonBackReference)
    private List<Book> books;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;

    public void setOccupiedSeats() {
        for (Book book : getBooks()) {
            for (Seat seat : book.getSeats()) {
                seat.setOccupied(true);
            }
        }
    }

    public List<Seat> getOccupiedSeats() {
        setOccupiedSeats();
        List<Seat> occupiedSeats = new ArrayList<>();
        for (Book book : getBooks()) {
            occupiedSeats.addAll(book
                    .getSeats()
                    .stream()
                    .filter(Seat::isOccupied)
                    .collect(Collectors.toList()));
        }
        return occupiedSeats;
    }

}
