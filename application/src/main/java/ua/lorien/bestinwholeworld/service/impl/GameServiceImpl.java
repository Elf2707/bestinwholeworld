package ua.lorien.bestinwholeworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.lorien.bestinwholeworld.game.exception.GameNotFoundException;
import ua.lorien.bestinwholeworld.game.exception.GameWithSameNameAlreadyExistsException;
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
	public Game update(Game game) throws GameNotFoundException, GameWithSameNameAlreadyExistsException {
		//Find out if this game exists
		Game gameForUpdate = findById(game.getId());
		if( gameForUpdate == null ){
			throw new GameNotFoundException();
		}
		
		//Try to find game with the same name and different id 
		Game gameByName = findByName(game.getName());
		if( gameByName != null && gameByName.getId() != game.getId() ){
			throw new GameWithSameNameAlreadyExistsException();
		}
		gameCrudRepository.saveAndFlush(game);
		return game;
	}
	
	@Override
	public void delete(Long id) throws GameNotFoundException {
		Game game = findById(id);
		if( game == null ){
			throw new GameNotFoundException();
		}
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

	@Override
	public Page<Game> findAll(Pageable pageable) {
		if( pageable != null){
			return gameCrudRepository.findAll(pageable);
		}
		return null;
	}

	@Override
	public Page<Game> findByDevCompanyNameIgnoreCase(String devCompanyName, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
