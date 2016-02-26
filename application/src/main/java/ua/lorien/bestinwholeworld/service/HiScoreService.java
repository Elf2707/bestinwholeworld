package ua.lorien.bestinwholeworld.service;

import java.util.List;

import ua.lorien.bestinwholeworld.model.HiScore;

public interface HiScoreService {
	HiScore add(HiScore hiScore);

	void delete(Long id);

	void deleteAll();
	
	List<HiScore> findAll();

	HiScore findById(Long id);
}