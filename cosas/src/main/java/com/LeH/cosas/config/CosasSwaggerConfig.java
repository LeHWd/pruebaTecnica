package com.LeH.cosas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class CosasSwaggerConfig {

@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cosas")
                        .version("1.0")
                        .description("Documentación de la API de Cosas con Springdoc OpenAPI"));
    }

}
