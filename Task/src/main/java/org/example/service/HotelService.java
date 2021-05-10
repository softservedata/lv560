package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.dao.HotelDao;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class HotelService {

    private final HotelDao hotelDao;

    @Autowired
    public HotelService(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public Hotel findById(Integer integer) {
        return hotelDao.findById(integer);
    }

    public void saveHotel(Hotel hotel) {
        hotelDao.saveHotel(hotel);
    }

    public List<Hotel> listOfHotels() {
        return hotelDao.listOfHotels();
    }

    public void updateHotel(Hotel hotel) {
        hotelDao.updateHotel(hotel);
    }

    public void deleteHotel(Integer integer) {
        hotelDao.deleteHotel(integer);
    }

    public List<Hotel> findByCountry(Integer id) {
        return hotelDao.findByCountry(id);
    }


}
