package org.bn.travel_agency.services;

import org.bn.travel_agency.entities.Role;
import org.bn.travel_agency.entities.User;
import org.bn.travel_agency.repositories.RoleRepository;
import org.bn.travel_agency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

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
		userRepository.deleteById(id);
	}
}
