package org.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Room> rooms;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(Integer hotelRating) {
        this.hotelRating = hotelRating;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", country=" + country +
                ", hotelName='" + hotelName + '\'' +
                ", hotelRating=" + hotelRating +
                ", rooms=" + rooms +
                '}';
    }
}
