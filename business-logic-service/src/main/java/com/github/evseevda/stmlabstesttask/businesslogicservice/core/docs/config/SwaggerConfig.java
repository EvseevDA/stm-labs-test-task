package com.github.evseevda.stmlabstesttask.businesslogicservice.core.docs.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication")
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer Authentication", securityScheme()
                                )
                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:8081")
                                        .description("Central service of application")
                        )
                )
                .info(
                        new Info()
                                .title("API")
                                .description("Contains central API methods of application")
                                .contact(
                                        new Contact()
                                                .name("Dmitry Evseev")
                                                .email("ne.evseev.da@gmail.com")
                                )
                                .version("0.9.0")
                );
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
