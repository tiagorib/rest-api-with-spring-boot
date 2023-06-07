package br.com.tiago.restapiwithspringboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sales API", description = "REST API") )
public class RestApiWithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithSpringBootApplication.class, args);
	}

}
