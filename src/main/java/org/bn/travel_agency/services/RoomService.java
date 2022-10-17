package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Room;

public interface RoomService {
	void save(Room room);

	Room findRoomById(Long id);

}
