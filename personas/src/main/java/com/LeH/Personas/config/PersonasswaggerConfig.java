package com.LeH.personas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class PersonasswaggerConfig {

@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cosas")
                        .version("1.0")
                        .description("Documentación de la API de Personas con Springdoc OpenAPI"));
    }

}