package ua.lorien.bestinwholeworld.repository;

import static org.junit.Assert.assertNotNull;
import static ua.lorien.bestinwholeworld.fortestrepository.UserForTestRepository.johnDoe;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.lorien.bestinwholeworld.config.AppConfig;
import ua.lorien.bestinwholeworld.model.User;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "ua.lorien.bestinwholeworld.config.dbdatasource")
@ContextConfiguration(classes = { AppConfig.class })
@Transactional
public class UserRepositoryUTest {

	@Autowired
	private UserCrudRepository userCrudRepository;

	@Autowired
	private EntityManager em;

	@Autowired
	private Logger logger;

	@Test
	public void saveAndGetValidUser() {
		User saveUser = johnDoe();
		em.persist(saveUser);

		User getUser = userCrudRepository.findOne(saveUser.getId());

		assertNotNull(getUser);

		logger.info(saveUser.toString());
		logger.info(getUser.toString());
	}
}
