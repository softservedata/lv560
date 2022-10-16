package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Hotel;

import java.util.List;

public interface HotelService {
	void save(Hotel hotel);

	List<Hotel> findAllHotels(Integer page, Integer pageSize);

	Hotel findHotelById(Long id);

	void updateHotelById(long id, Hotel newHotelEntity);

	void deleteHotelById(long id);

	void createRooms(Hotel hotel, int count, int priceForRoom);
}
