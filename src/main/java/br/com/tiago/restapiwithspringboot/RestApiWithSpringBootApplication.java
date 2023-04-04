package br.com.tiago.restapiwithspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RestApiWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithSpringBootApplication.class, args);
	}

}