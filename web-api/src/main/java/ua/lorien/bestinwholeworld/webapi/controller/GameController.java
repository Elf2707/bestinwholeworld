package ua.lorien.bestinwholeworld.webapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addGame(@Valid @RequestBody Game newGame) {
		logger.info(GAME_CONTROLLER + "addGame" + newGame);
		gameService.add(newGame);
	}
}
