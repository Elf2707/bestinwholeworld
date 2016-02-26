package ua.lorien.bestinwholeworld.config.persistence;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Profile("production")
@PropertySource("classpath:/db-production.properties")
@Configuration
public class PersistenceProdPostgres {
	
	@Value(value = "${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;
	
    @Value("${spring.jpa.properties.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
    
    @Value("${spring.jpa.properties.hibernate.show_sql}")
    private String showSql;
    
    //For postgress Db
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    
    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Value("${spring.datasource.username}")
    private String userName;
    
    @Value("${spring.datasource.password}")
    private String password;
    		
    @Bean
	public DataSource dataSource() {
    	HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(dbUrl);
        config.setUsername(userName);
        config.setPassword(password);

        return new HikariDataSource(config);
	}
    
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("ua.lorien.bestinwholeworld.model");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, hibernateDialect);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, showSql);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        
		return entityManagerFactoryBean;
	}
    
    @Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
