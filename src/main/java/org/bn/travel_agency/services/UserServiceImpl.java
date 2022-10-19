package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Reservation;
import org.bn.travel_agency.entities.Role;
import org.bn.travel_agency.entities.Room;
import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.repositories.ReservationRepository;
import org.bn.travel_agency.repositories.RoomRepository;
import org.bn.travel_agency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(Set.of(new Role(1L)));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll().stream()
				.filter(user -> user.getRoles().contains(new Role(1L)))
				.collect(Collectors.toList());
	}

	@Override
	public List<User> findAllUsers(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return userRepository.findAll(pageable).getContent().stream()
				.filter(user -> user.getRoles().contains(new Role(1L)))
				.collect(Collectors.toList());
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public void updateUserById(long id, User newUserEntity) {
		User targetUser = userRepository.getUserById(id);

		targetUser.setFirstName(newUserEntity.getFirstName());
		targetUser.setLastName(newUserEntity.getLastName());
		targetUser.setAmountOfMoney(newUserEntity.getAmountOfMoney());

		userRepository.save(targetUser);
	}

	@Override
	public void deleteUserById(long id) {

		User user = userRepository.getUserById(id);
		if (!user.getReservations().isEmpty()) {
			for (Reservation reservation : user.getReservations()) {
				reservationService.delete(reservation);
			}
		}
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public boolean reserveRoom(long userId, long roomId, String startDate, String endDate) {
		User user = userRepository.getUserById(userId);
		Room room = roomRepository.getRoomById(roomId);

		long numberOfDaysBetween = 1 + ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
		long price = room.getPrice() * numberOfDaysBetween;

		if (user.getAmountOfMoney() >= price) {
			user.setAmountOfMoney((user.getAmountOfMoney() - price));
			reservationRepository.save(new Reservation(user, room, startDate, endDate, price));
			return true;
		} else {
			return false;
		}
	}
}
