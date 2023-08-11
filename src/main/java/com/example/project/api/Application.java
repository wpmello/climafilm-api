package com.example.project.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ClimaFilm Api", version = "1", description = "Api desenvolvida para filtrar filmes em cartaz com base na temperatura atual de determinado estado ou país."))
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
