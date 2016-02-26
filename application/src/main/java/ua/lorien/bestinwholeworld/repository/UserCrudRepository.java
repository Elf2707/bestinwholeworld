package ua.lorien.bestinwholeworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lorien.bestinwholeworld.model.User;

public interface UserCrudRepository extends JpaRepository<User, Long> {
	
	User findFirstByUserNameIgnoreCase(String userName);
	
	User findFirstByEmailIgnoreCase(String email);
}
