package org.example.dao;

import org.example.model.Hotel;
import java.util.List;


public interface HotelDao {

    void saveHotel(Hotel hotel);

    Hotel findById(Integer integer);

    void updateHotel(Hotel hotel);

    void deleteHotel(Integer integer);

    List<Hotel> listOfHotels();

    List<Hotel> findByCountry(String string);
}
