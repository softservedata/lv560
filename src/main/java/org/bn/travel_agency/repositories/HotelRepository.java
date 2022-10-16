package org.bn.travel_agency.repositories;

import org.bn.travel_agency.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	Hotel getHotelById(Long id);
}
