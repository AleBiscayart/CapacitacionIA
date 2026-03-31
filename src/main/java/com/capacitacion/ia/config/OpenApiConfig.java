package com.capacitacion.ia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de la documentación OpenAPI / Swagger UI.
 *
 * Acceso a la UI: http://localhost:8080/swagger-ui.html
 * JSON spec:     http://localhost:8080/v3/api-docs
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Productos")
                        .description("CRUD completo de Productos con Spring Boot 3")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Capacitación IA")
                                .email("capacitacion@ia.com")));
    }
}

