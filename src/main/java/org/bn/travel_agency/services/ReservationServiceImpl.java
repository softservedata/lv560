package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Reservation;
import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.repositories.ReservationRepository;
import org.bn.travel_agency.repositories.RoomRepository;
import org.bn.travel_agency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<Reservation> findAllReservations(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return new ArrayList<>(reservationRepository.findAll(pageable).getContent());
	}

	@Override
	public Reservation findReservationById(long id) {
		return reservationRepository.findReservationById(id);
	}

	@Override
	@Transactional
	public void delete(Reservation reservation){
		long id = reservation.getId();
		User user = reservation.getUser();
		Room room = reservation.getRoom();

		user.getReservations().remove(reservation);
		userRepository.save(user);

		room.getReservations().removeIf(c -> c.getId().equals(reservation.getId()));
		roomRepository.save(room);

		reservation.setRoom(null);
		reservation.setUser(null);

		reservationRepository.deleteById(id);
	}
}
