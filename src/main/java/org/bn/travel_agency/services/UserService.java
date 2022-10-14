package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.User;

import java.util.List;

public interface UserService {
	void save(User user);

	User findByUsername(String username);

	List<User> findAll();

}
