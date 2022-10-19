package org.bn.travel_agency.repositories;

import org.bn.travel_agency.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Reservation findReservationById(long id);

}
