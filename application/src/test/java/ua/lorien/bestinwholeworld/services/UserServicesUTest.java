package ua.lorien.bestinwholeworld.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static ua.lorien.bestinwholeworld.fortestrepository.UserForTestRepository.billKid;
import static ua.lorien.bestinwholeworld.fortestrepository.UserForTestRepository.johnDoe;
import static ua.lorien.bestinwholeworld.fortestrepository.UserForTestRepository.samantaFox;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.lorien.bestinwholeworld.config.AppConfig;
import ua.lorien.bestinwholeworld.model.User;
import ua.lorien.bestinwholeworld.service.UserService;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@Transactional(value = TxType.REQUIRED)
public class UserServicesUTest {

	@Autowired
	private UserService userServices;

	@Test
	public void addValidUser() {
		User user = johnDoe();

		userServices.add(user);
		assertNotNull(user.getId());

	}

	@Test(expected = Exception.class)
	public void addNullUser() {
		userServices.add(null);
		fail("Null user should not adds without Exeption");
	}

	@Test(expected = ConstraintViolationException.class)
	public void addUserWithLongName() {
		User user = johnDoe();
		// Name should not be more then 20 letters
		user.setUserName("qwertyuiopasdfghjklzxcvbnm");
		userServices.add(user);

		fail("User with very long name should not adds without Exeption");
	}

	@Test(expected = ConstraintViolationException.class)
	public void addUserWithShortName() {
		User user = johnDoe();
		// Name should not be more then 20 letters
		user.setUserName("q");
		userServices.add(user);

		fail("User with very short name should not adds without Exeption");
	}

	@Test
	public void deleteUser() {
		User user = johnDoe();

		userServices.add(user);
		assertNotNull(user.getId());
		assertNotNull(userServices.findById(user.getId()));

		userServices.delete(user.getId());
		assertNull(userServices.findById(user.getId()));
	}

	@Test
	public void findUserById() {
		User user = johnDoe();
		userServices.add(user);
		User newUser = userServices.findById(user.getId());

		assertNotNull(newUser);
		assertNotNull(newUser.getId());
	}

	@Test
	public void findUserByName() {
		userServices.add(johnDoe());
		User user = userServices.findByName(johnDoe().getUserName());

		assertNotNull(user);
		assertThat(user.getUserName(), is(equalTo("JohnDoe")));

	}

	@Test
	public void findUserByEmail() {
		userServices.add(johnDoe());
		User user = userServices.findByEmail(johnDoe().getEmail());

		assertNotNull(user);
		assertThat(user.getEmail(), is(equalTo("johnDoe@gmail.com")));
	}

	@Test
	public void findAllUsers() {
		userServices.add(johnDoe());
		userServices.add(samantaFox());
		userServices.add(billKid());

		List<User> users = userServices.findAll();

		assertNotNull(users);
		assertThat(users.size(), is(equalTo(3)));
		users.stream().mapToLong(u -> u.getId()).forEach(org.junit.Assert::assertNotNull);
	}

	@Test
	public void findUserNotExist() {
		User user = johnDoe();
		userServices.add(user);

		User newUser = userServices.findById(user.getId() + 1);
		assertNull(newUser);
	}
}
