package com.spring.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.spring.repository")
public class SpringConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	// Khai báo view
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}



	// Config hibernate
	// Step 1: config datasource (thông tin kết nối DB)
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		datasource.setUrl("jdbc:sqlserver://DESKTOP-EFE4DRG\\SQLEXPRESS:1433;databaseName=Mock;sendTimeAsDateTime=false");
		datasource.setUsername("sa");
		datasource.setPassword("04052001ha");
		
		
		return datasource;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

	@Bean
	public Properties hibernateProperties() {
	    Properties hibernateProperties = new Properties();
	    hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // create, create-drop
	    hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
	    hibernateProperties.setProperty("hibernate.show_sql", "true");
	    return hibernateProperties;
	}

	// Step 2: config entityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(getDataSource());
		entityManagerFactory.setPackagesToScan(new String[] { "com.spring.entity" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactory.setJpaProperties(hibernateProperties());
		return entityManagerFactory;
	}

	// Step 3: Config entity manager
	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	// Step 4: Transaction support
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/assets/");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error").setViewName("error");
	}
}
