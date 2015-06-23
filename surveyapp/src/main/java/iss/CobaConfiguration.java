package iss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class CobaConfiguration {
//	@Bean
//	public ViewResolver viewResolver() {
//		
//		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//		templateResolver.setTemplateMode("LEGACYHTML5");
//		templateResolver.setPrefix("view/");
//		templateResolver.setSuffix("html");
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(templateResolver);
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setTemplateEngine(engine);
//		viewResolver.setOrder(0);
//		return viewResolver;
//	}
//
//	@Bean
//	public InternalResourceViewResolver internalResourceViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
	
	
	@Bean
	public DriverManagerDataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/iss");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		return dataSource;
	}
	
	
	@Bean 
	public JdbcTemplate getJdbcTemplate(){
		return new JdbcTemplate(getDataSource());
	}
}
