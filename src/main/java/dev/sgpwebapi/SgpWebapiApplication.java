package dev.sgpwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SgpWebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgpWebapiApplication.class, args);
	}

	
	/**
	 * Pour contrer le problème suivant
	 * Failed to load http://localhost:8080/api/collaborateurs: 
	 * No 'Access-Control-Allow-Origin' header is present on the requested resource. 
	 * Origin 'http://127.0.0.1:9999' is therefore not allowed access.
	 * */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "PUT");
			}
		};
	}
}
