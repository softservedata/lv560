package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Hotel;
import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.repositories.HotelRepository;
import org.bn.travel_agency.repositories.LocationRepository;
import org.bn.travel_agency.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public void save(Hotel hotel) {
		hotelRepository.save(hotel);
	}

	@Override
	public Hotel findHotelById(Long id) {
		return hotelRepository.getHotelById(id);
	}

	@Override
	public void updateHotelById(long id, Hotel newHotelEntity) {
		Hotel targetHotel = hotelRepository.getHotelById(id);

		targetHotel.setName(newHotelEntity.getName());
		targetHotel.setLocation(locationRepository.getLocationByName(newHotelEntity.getLocationName()));

		hotelRepository.save(targetHotel);
	}

	@Override
	public void deleteHotelById(long id) {
		hotelRepository.deleteById(id);
	}

	@Override
	public List<Hotel> findAllHotels(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return new ArrayList<>(hotelRepository.findAll(pageable).getContent());
	}

	@Override
	public void createRooms(Hotel hotel, int count, int priceForRoom) {
		for (int i = 0; i < count; i++) {
			Room temp = new Room(priceForRoom);
			temp.setHotel(hotel);
			roomRepository.save(temp);
			hotelRepository.save(hotel);
		}
	}
}
