package com.pacobravo.hexagonal.web.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pricesOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Prices API")
                        .description("API para consultar precios de productos")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio de GitHub")
                        .url("https://github.com/pacobravo89/arquitectura-hexagonal-java"));
    }
}

