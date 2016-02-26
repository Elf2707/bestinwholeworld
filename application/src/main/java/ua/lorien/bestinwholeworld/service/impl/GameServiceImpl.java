package ua.lorien.bestinwholeworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.lorien.bestinwholeworld.model.Game;
import ua.lorien.bestinwholeworld.repository.GameCrudRepository;
import ua.lorien.bestinwholeworld.service.GameService;

public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameCrudRepository gameCrudRepository;
	
	@Override
	public Game add(Game game) {
		return gameCrudRepository.save(game);
	}

	@Override
	public void delete(Long id) {
		gameCrudRepository.delete(id);
	}

	@Override
	public List<Game> findAll() {
		return gameCrudRepository.findAll();
	}

	@Override
	public Game findById(Long id) {
		return gameCrudRepository.findOne(id);
	}

	@Override
	public Game findByName(String name) {
		return gameCrudRepository.findFirstByNameIgnoreCase(name);
	}

	@Override
	public List<Game> findByDevCompanyName(String devCompanyName) {
		return gameCrudRepository.findByDevCompanyNameIgnoreCase(devCompanyName);
	}

	@Override
	public List<Game> findByNameAndDevCompanyName(String name, String devCompanyName) {
		return gameCrudRepository.findByNameAndDevCompanyNameAllIgnoreCase(name, devCompanyName);
	}

	@Override
	public void deleteAll() {
		gameCrudRepository.deleteAll();
	}
}
