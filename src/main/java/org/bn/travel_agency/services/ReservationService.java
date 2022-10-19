package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Reservation;

import java.util.List;

public interface ReservationService {
	List<Reservation> findAllReservations(Integer page, Integer pageSize);

	Reservation findReservationById(long id);

	void delete(Reservation reservation);
}
