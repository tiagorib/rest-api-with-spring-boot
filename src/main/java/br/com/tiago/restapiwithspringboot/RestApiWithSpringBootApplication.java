package br.com.tiago.restapiwithspringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rest API Fatec", description = "REST API Fatec SpringBoot"))
public class RestApiWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithSpringBootApplication.class, args);
	}

}
