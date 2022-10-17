package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public void save(Room room) {
		roomRepository.save(room);
	}

	@Override
	public Room findRoomById(Long id) {
		return roomRepository.getRoomById(id);
	}
}
