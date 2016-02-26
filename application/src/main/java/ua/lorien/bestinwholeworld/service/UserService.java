package ua.lorien.bestinwholeworld.service;

import java.util.List;

import ua.lorien.bestinwholeworld.model.User;

public interface UserService {
	User add(User user);

	void delete(Long id);

	void deleteAll();
	
	List<User> findAll();

	User findById(Long id);

	User findByEmail(String email);

	User findByName(String name);
}
