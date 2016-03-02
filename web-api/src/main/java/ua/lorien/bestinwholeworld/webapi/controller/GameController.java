package ua.lorien.bestinwholeworld.webapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.lorien.bestinwholeworld.model.Game;
import ua.lorien.bestinwholeworld.service.GameService;

@Configuration
@RestController
@RequestMapping("/api/games")
public class GameController {

	private final String GAME_CONTROLLER = "Game controller: ";

	@Autowired
	private GameService gameService;

	@Autowired
	private Logger logger;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Game>> findAllGames() {
		logger.debug(GAME_CONTROLLER + "findAllGames");
		
		List<Game> games = gameService.findAll();
		if (games == null) {
			return new ResponseEntity<List<Game>>(games, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
	}

	@RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
	public ResponseEntity<Page<Game>> findAllGamesPageable(Pageable pageable) {
		logger.debug(GAME_CONTROLLER + "findAllGames");
		
		Page<Game> games = gameService.findAll(pageable);
		if (games == null) {
			return new ResponseEntity<Page<Game>>(games, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Page<Game>>(games, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addGame(@Valid @RequestBody Game newGame) {
		logger.debug(GAME_CONTROLLER + "addGame" + newGame);
		
		gameService.add(newGame);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Game> updateGame(@Valid @RequestBody Game game, @PathVariable Long id){
		logger.debug(GAME_CONTROLLER + "updateGame " + game);
		
		game.setId(id);
		Game gameAfterUpdate = gameService.update(game);
		if( gameAfterUpdate == null ){
			return new ResponseEntity<Game>(gameAfterUpdate, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Game>(game, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
	public void deleteGame(@PathVariable Long id){
		logger.debug(GAME_CONTROLLER + "deleteGame by id = " + id);
		
		gameService.delete(id);
	}
}
