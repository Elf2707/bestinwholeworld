package ua.lorien.bestinwholeworld.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ua.lorien.bestinwholeworld.service.CommentService;
import ua.lorien.bestinwholeworld.service.GameService;
import ua.lorien.bestinwholeworld.service.HiScoreService;
import ua.lorien.bestinwholeworld.service.UserService;
import ua.lorien.bestinwholeworld.service.impl.CommentServiceImpl;
import ua.lorien.bestinwholeworld.service.impl.GameServiceImpl;
import ua.lorien.bestinwholeworld.service.impl.HiScoreServiceImpl;
import ua.lorien.bestinwholeworld.service.impl.UserServiceImpl;

@Configuration
@ComponentScan(basePackages = "ua.lorien.bestinwholeworld.config.persistence")
@PropertySource("classpath:/application.properties")
@EnableJpaRepositories(basePackages = { "ua.lorien.bestinwholeworld.repository" })
public class AppConfig {

	@Value("${spring.bestinwholeworld.logger-name}")
	private String loggerName;

	@Bean
	public UserService userServices() {
		return new UserServiceImpl();
	}

	@Bean
	public GameService gameServices() {
		return new GameServiceImpl();
	}

	@Bean
	public CommentService commentServices() {
		return new CommentServiceImpl();
	}

	@Bean
	public HiScoreService hiScoreServices() {
		return new HiScoreServiceImpl();
	}

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(loggerName);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
