package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hotel_name")
    private String hotelName;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "rating_of_hotel")
    private Integer hotelRating;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Room> rooms;
}
