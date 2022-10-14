package org.bn.travel_agency.repositories;

import org.bn.travel_agency.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	List<User> findAll();
}
