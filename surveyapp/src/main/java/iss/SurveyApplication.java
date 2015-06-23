package iss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SurveyApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SurveyApplication.class);
	}
	
	
	
	public static void main(String[] args){
		SpringApplication.run(SurveyApplication.class, args);
		//ApplicationContext ac = new ClassPathXmlApplicationContext("db.xml");
		
	}
	
	

}
