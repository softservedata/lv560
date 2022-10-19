package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.User;

import java.util.List;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	User findUserById(Long id);

	List<User> findAll();

	List<User> findAllUsers();

	List<User> findAllUsers(Integer page, Integer pageSize);

	void updateUserById(long id, User newUserEntity);

	void deleteUserById(long id);

	boolean reserveRoom(long userId, long roomId, String startDate, String endDate);
}
