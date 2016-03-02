package ua.lorien.bestinwholeworld.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.lorien.bestinwholeworld.game.exception.GameNotFoundException;
import ua.lorien.bestinwholeworld.game.exception.GameWithSameNameAlreadyExistsException;
import ua.lorien.bestinwholeworld.model.Game;

public interface GameService {
	Game add(Game game);

	Game update(Game game) throws GameNotFoundException, GameWithSameNameAlreadyExistsException;
	
	void delete(Long id) throws GameNotFoundException;

	List<Game> findAll();
	
	Page<Game> findAll(Pageable pageable);

	Game findById(Long id);

	Game findByName(String name);

	List<Game> findByDevCompanyName(String devCompanyName);

	List<Game> findByNameAndDevCompanyName(String name, String DevCompanyName);
	
	Page<Game> findByDevCompanyNameIgnoreCase(String devCompanyName, Pageable pageable);
	
	List<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName);
	
	Page<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName, Pageable pageable);
	
	void deleteAll();
}
