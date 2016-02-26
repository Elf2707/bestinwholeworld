package ua.lorien.bestinwholeworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.lorien.bestinwholeworld.model.HiScore;
import ua.lorien.bestinwholeworld.repository.HiScoreCrudRepository;
import ua.lorien.bestinwholeworld.service.HiScoreService;

public class HiScoreServiceImpl implements HiScoreService {

	@Autowired
	HiScoreCrudRepository hiScoreCrudRepository;
	
	@Override
	public HiScore add(HiScore hiScore) {
		return hiScoreCrudRepository.save(hiScore);
	}

	@Override
	public void delete(Long id) {
		hiScoreCrudRepository.delete(id);
	}

	@Override
	public List<HiScore> findAll() {
		return hiScoreCrudRepository.findAll();
	}

	@Override
	public HiScore findById(Long id) {
		return hiScoreCrudRepository.findOne(id);
	}

	@Override
	public void deleteAll() {
		hiScoreCrudRepository.deleteAll();
	}
}
