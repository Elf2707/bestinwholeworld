package ua.lorien.bestinwholeworld.fortestrepository;

import static ua.lorien.bestinwholeworld.fortestrepository.HiScoresForTestRepository.*;
import static ua.lorien.bestinwholeworld.fortestrepository.CommentForTestRepository.*;

import ua.lorien.bestinwholeworld.model.Game;

public class GameForTestRepository {

	public static Game zombieBird() {
		Game game = new Game("Zombie Bird", "Jsoft");
		game.setDescription("It's a nice game about cool green bird");

		game.addComment(fifthComment());
		game.addComment(secondComment());

		game.addHiScore(malkinHiScore());
		game.addHiScore(sedinHiScore());
		game.addHiScore(larkinHiScore());
		game.addHiScore(letangHiScore());
		game.addHiScore(crosbyHiScore());

		return game;
	}

	public static Game firstGame() {
		Game game = new Game("First Game", "First Company");
		game.setDescription("Cool first game");

		game.addComment(firstComment());
		game.addComment(thirdComment());
		game.addComment(fifthComment());

		game.addHiScore(malkinHiScore());
		game.addHiScore(oveHiScore());
		game.addHiScore(sedinHiScore());
		game.addHiScore(warsoffskyHiScore());

		return game;
	}

	public static Game secondGame() {
		Game game = new Game("Second Game", "Second Company");
		game.setDescription("Cool second game");

		game.addComment(firstComment());
		game.addComment(secondComment());
		game.addComment(thirdComment());
		game.addComment(fourthComment());
		game.addComment(fifthComment());

		game.addHiScore(larkinHiScore());
		game.addHiScore(letangHiScore());
		game.addHiScore(kuznetcovHiScore());
		game.addHiScore(panarinHiScore());

		return game;
	}

	public static Game thirdGame() {
		Game game = new Game("Third game", "Third Company");
		game.setDescription("Cool third game");

		game.addComment(firstComment());

		game.addHiScore(crosbyHiScore());
		game.addHiScore(brownHiScore());

		return game;
	}

	public static Game fouthGame() {
		Game game = new Game("Fouth game", "Fouth Company");
		game.setDescription("Cool fouth game");

		game.addComment(fifthComment());
		game.addComment(secondComment());

		game.addHiScore(sibrucHiScore());
		game.addHiScore(sedinHiScore());

		return game;
	}

	public static Game fifthGame() {
		Game game = new Game("Fifth game", "Fifth Company");
		game.setDescription("Cool fifth game");

		game.addComment(fourthComment());
		game.addComment(fifthComment());

		game.addHiScore(oveHiScore());
		game.addHiScore(kuznetcovHiScore());
		game.addHiScore(panarinHiScore());

		return game;
	}

	public static Game sixthGame() {
		Game game = new Game("Sixth game", "Sixth Company");
		game.setDescription("Cool sixth game");

		game.addComment(firstComment());
		game.addComment(fifthComment());

		game.addHiScore(orlovHiScore());
		game.addHiScore(panarinHiScore());
		game.addHiScore(anisimovHiScore());
		game.addHiScore(datscukHiScore());
		game.addHiScore(dushenHiScore());

		return game;
	}

	public static Game seventhGame() {
		Game game = new Game("Seventh game", "Seventh Company");
		game.setDescription("Cool seventh game");

		game.addComment(firstComment());
		game.addComment(fifthComment());

		game.addHiScore(crosbyHiScore());
		game.addHiScore(brownHiScore());
		game.addHiScore(malkinHiScore());
		game.addHiScore(hikkyHiScore());
		game.addHiScore(horvatHiScore());

		return game;
	}

	public static String simpleGameInJson() {
		return "{" + "\"name\": \"My Game\", \"devCompanyName\": \"jSoft\", \"description\" : \"My First Very Cool Game\""
				+ "}";
	}
	
	public static String simpleGameWithNullNameInJson() {
		return "{" + "\"name\": null, \"devCompanyName\": \"jSoft\", \"description\" : \"My First Very Cool Game\""
				+ "}";
	}
}
