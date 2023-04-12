package br.com.tiago.restapiwithspringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@OpenAPIDefinition(info=@Info(title="REST API Fatec Jales",description = "REST API Fatec SpringBoot"))
public class RestApiWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithSpringBootApplication.class, args);
	}

}