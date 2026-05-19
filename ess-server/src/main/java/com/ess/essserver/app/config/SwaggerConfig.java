package com.ess.essserver.app.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // Define the security scheme for bearer token authentication
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"); // e.g., for JWT tokens

        // Attach the security scheme to OpenAPI Components
        Components components = new Components().addSecuritySchemes("BearerAuth", securityScheme);

        // Add a security requirement to secure all endpoints by default
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("BearerAuth");

        return new OpenAPI()
                .components(components)
                .addSecurityItem(securityRequirement) // Apply globally
                .info(new Info()
                        .title("event Scheduling System API")
                        .version("1.0")
                        .description("API for managing bookings"));
    }
}