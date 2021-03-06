package ua.lorien.bestinholeworld.webapi.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.fifthGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.firstGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.secondGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.simpleGameInJson;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.simpleGameWithNullNameInJson;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.thirdGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.zombieBird;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.NestedServletException;

import ua.lorien.bestinholeworld.webapi.commonutils.json.JsonConverter;
import ua.lorien.bestinwholeworld.Application;
import ua.lorien.bestinwholeworld.config.AppConfig;
import ua.lorien.bestinwholeworld.game.exception.GameNotFoundException;
import ua.lorien.bestinwholeworld.model.Game;
import ua.lorien.bestinwholeworld.service.GameService;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, AppConfig.class })
@WebAppConfiguration
@EnableWebMvc
@ComponentScan(basePackages = "ua.lorien.bestinwholeworld.controller")
public class GameControllerUTest {

	@Autowired
	private GameService gameService;

	@Autowired
	private WebApplicationContext webCtx;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build();
	}

	@After
	public void close() {
		gameService.deleteAll();
	}

	@Test
	public void getAllGames() {
		// Adding three games
		gameService.add(zombieBird());
		gameService.add(firstGame());
		gameService.add(secondGame());
		gameService.add(thirdGame());
		gameService.add(fifthGame());
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(get("/api/games").headers(requestHeaders).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)))
					.andExpect(jsonPath("$.[0].name").value("Zombie Bird"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getAllGamesGetNull() {
		try {
			when(gameService.findAll()).thenReturn(null);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(get("/api/games").headers(requestHeaders).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isNoContent()).andExpect(jsonPath("$", hasSize(0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addNewValidGameInPostRequest() {
		gameService.add(zombieBird());
		try {
			List<Game> games = gameService.findAll();
			assertThat(games.size(), is(equalTo(1)));
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(post("/api/games/add").headers(requestHeaders).content(simpleGameInJson()))
					.andExpect(status().isOk());
			games = gameService.findAll();
			assertThat(games.size(), is(equalTo(2)));
			assertThat(games.get(2).getName(), is(equalTo("My Game")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addNewGameWithNullName() {
		gameService.add(zombieBird());
		try {
			List<Game> games = gameService.findAll();
			assertThat(games.size(), is(equalTo(1)));
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(post("/api/games/add").headers(requestHeaders).content(simpleGameWithNullNameInJson()))
					.andExpect(status().isBadRequest());
			games = gameService.findAll();
			assertThat(games.size(), is(equalTo(1)));
			assertThat(games.get(1).getName(), not(equalTo("My Game")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateValidGame() {
		Game game = zombieBird();
		gameService.add(game);

		Long id = game.getId();

		assertThat(game.getDescription(), is(equalTo("It's a nice game about cool green bird")));

		// Change game description
		game.setDescription("Bla bla bla description");

		try {

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(
					put("/api/games/" + id).headers(requestHeaders).content(JsonConverter.objToJsonString(game)))
					.andExpect(status().isOk());

			game = gameService.findById(id);
			assertThat(game.getDescription(), is(equalTo("Bla bla bla description")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NestedServletException.class)
	public void updateGameWithNameOfAnotherGame() throws Exception {
		Game game = zombieBird();
		Game anotherGame = firstGame();
		gameService.add(game);
		gameService.add(anotherGame);

		assertThat(game.getId(), not(equalTo(anotherGame.getId())));
		game.setName(anotherGame.getName());

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(put("/api/games/" + game.getId()).headers(requestHeaders)
					.content(JsonConverter.objToJsonString(game))).andExpect(status().isOk());

			fail("You should'nt get here because of exception");
	}
	
	@Test
	public void deleteExistingGame() throws Exception {
		Game game = zombieBird();
		Game anotherGame = firstGame();
		gameService.add(game);
		gameService.add(anotherGame);

		List<Game> games = gameService.findAll();
		assertThat(games, hasSize(2));
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", "application/json; charset=utf-8");
		mockMvc.perform(delete("/api/games/" + game.getId()).headers(requestHeaders)).andExpect(status().isOk());
		
		games = gameService.findAll();
		
		assertThat(games, hasSize(1));
		assertThat(games.get(0).getName(), is(equalTo(anotherGame.getName())));
	}
	
	@Test(expected = GameNotFoundException.class)
	public void deleteNotExistingGame() throws Throwable {
		Game game = zombieBird();
		gameService.add(game);

		List<Game> games = gameService.findAll();
		assertThat(games, hasSize(1));
		
		try{
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json; charset=utf-8");
			mockMvc.perform(delete("/api/games/" + game.getId() + 11111L).headers(requestHeaders)).andExpect(status().isOk());
			fail("You should'nt get here because of GameNotFoundException exception");
		}catch( Exception exp ){
			throw exp.getCause();
		}
	}
}
