package org.bn.travel_agency.repositories;

import org.bn.travel_agency.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	Room getRoomById(Long id);
}
