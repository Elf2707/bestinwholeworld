package ua.lorien.bestinwholeworld.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.firstGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.fouthGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.secondGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.thirdGame;
import static ua.lorien.bestinwholeworld.fortestrepository.GameForTestRepository.zombieBird;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.lorien.bestinwholeworld.config.AppConfig;
import ua.lorien.bestinwholeworld.model.Comment;
import ua.lorien.bestinwholeworld.model.Game;
import ua.lorien.bestinwholeworld.model.HiScore;
import ua.lorien.bestinwholeworld.service.CommentService;
import ua.lorien.bestinwholeworld.service.GameService;
import ua.lorien.bestinwholeworld.service.HiScoreService;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional(value = TxType.REQUIRED)
public class GameServicesUTest {

	@Autowired
	GameService gameServices;
	
	@Autowired
	HiScoreService hiScoreServices;
	
	@Autowired
	CommentService commentServices;
	
	@After
	public void close(){
		gameServices.deleteAll();
	}

	@Test
	public void addValidGame() {
		Game game = zombieBird();

		gameServices.add(game);
		
		assertNotNull(game.getId());
		assertNotNull(gameServices.findById(game.getId()));
		
		List<Comment> comments = game.getComments();
		comments.stream().map(c -> c.getId()).forEach(org.junit.Assert::assertNotNull);
		assertThat(comments.size(), is(equalTo(2)));
		
		List<HiScore> hiScores = game.getHiScores();
		hiScores.stream().map(s -> s.getId()).forEach(org.junit.Assert::assertNotNull);
		assertThat(hiScores.size(), is(equalTo(5)));
	}
	
	@Test
	public void deleteGame(){
		Game game = zombieBird();

		gameServices.add(game);
		
		assertNotNull(game.getId());
		assertNotNull(gameServices.findById(game.getId()));
		
		//Get ids of first comment and first hiscore
		Long commentId = game.getComments().get(0).getId();
		assertNotNull(commentId);
		Long hiScoreId = game.getHiScores().get(0).getId();
		assertNotNull(hiScoreId);
		
		//Test DB for existing comment and hiScore
		assertNotNull(commentServices.findById(commentId));
		assertNotNull(hiScoreServices.findById(hiScoreId));
		
		//Delete game with cascade delete comments and hiScores
		gameServices.delete(game.getId());
		
		assertNull(gameServices.findById(game.getId()));
		assertNull(commentServices.findById(commentId));
		assertNull(commentServices.findById(hiScoreId));
	}
	
	@Test
	public void deleteAllGames(){
		gameServices.add(zombieBird());
		gameServices.add(firstGame());
		gameServices.add(secondGame());
		gameServices.add(thirdGame());
		gameServices.add(fouthGame());
		
		List<Game> games = gameServices.findAll();
		assertThat(games, hasSize(5));
		gameServices.deleteAll();
		games = gameServices.findAll();
		assertThat(games, hasSize(0));
	}
}
