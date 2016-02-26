package ua.lorien.bestinwholeworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lorien.bestinwholeworld.model.HiScore;

public interface HiScoreCrudRepository extends JpaRepository<HiScore, Long> {
}
