package ua.lorien.bestinwholeworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lorien.bestinwholeworld.model.Game;

public interface GameCrudRepository extends JpaRepository<Game, Long> {
	Game findFirstByNameIgnoreCase(String name);
	
	List<Game> findAll();
	
	List<Game> findByDevCompanyNameIgnoreCase(String devCompanyName);
	
	List<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName);
}
