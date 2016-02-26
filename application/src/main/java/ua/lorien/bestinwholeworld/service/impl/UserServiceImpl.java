package ua.lorien.bestinwholeworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.lorien.bestinwholeworld.model.User;
import ua.lorien.bestinwholeworld.repository.UserCrudRepository;
import ua.lorien.bestinwholeworld.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserCrudRepository userCrudRepository;

	@Override
	public User add(User user) {
		return userCrudRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userCrudRepository.delete(id);
	}

	@Override
	public List<User> findAll() {
		return userCrudRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userCrudRepository.findOne(id);
	}

	@Override
	public User findByEmail(String email) {
		return userCrudRepository.findFirstByEmailIgnoreCase(email);
	}

	@Override
	public User findByName(String name) {
		return userCrudRepository.findFirstByUserNameIgnoreCase(name);
	}

	@Override
	public void deleteAll() {
		userCrudRepository.deleteAll();
	}
}
