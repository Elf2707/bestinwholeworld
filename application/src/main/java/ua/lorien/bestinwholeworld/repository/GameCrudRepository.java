package ua.lorien.bestinwholeworld.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ua.lorien.bestinwholeworld.model.Game;

public interface GameCrudRepository extends JpaRepository<Game, Long> {
	
	Game findFirstByNameIgnoreCase(String name);
	
	List<Game> findAll();
	 
	Page<Game> findAll(Pageable pageable);
	
	List<Game> findByDevCompanyNameIgnoreCase(String devCompanyName);

	Page<Game> findByDevCompanyNameIgnoreCase(String devCompanyName, Pageable pageable);
	
	List<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName);
	
	Page<Game> findByNameAndDevCompanyNameAllIgnoreCase(String name, String devCompanyName, Pageable pageable);
}
