package ua.lorien.bestinwholeworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@PropertySource("classpath:/application.properties")
public class Application extends WebMvcConfigurerAdapter {

	@Value("${spring.bestinwholeworld.logger-name}")
	private String loggerName;

	public static void main(String[] args) {
		SpringApplication.run( Application.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.US);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}

// public class AppInitializer extends
// AbstractAnnotationConfigDispatcherServletInitializer {
//
// @Override
// protected Class<?>[] getRootConfigClasses() {
// return new Class<?>[] {AppConfig.class};
// }
//
// @Override
// protected Class<?>[] getServletConfigClasses() {
// return null;
// }
//
// @Override
// protected String[] getServletMappings() {
// return null;
// }
//
// }
