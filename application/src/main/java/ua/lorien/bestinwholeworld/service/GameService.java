package ua.lorien.bestinwholeworld.service;

import java.util.List;

import ua.lorien.bestinwholeworld.model.Game;

public interface GameService {
	Game add(Game game);

	void delete(Long id);

	List<Game> findAll();

	Game findById(Long id);

	Game findByName(String name);

	List<Game> findByDevCompanyName(String devCompanyName);

	List<Game> findByNameAndDevCompanyName(String name, String DevCompanyName);
	
	void deleteAll();
}
