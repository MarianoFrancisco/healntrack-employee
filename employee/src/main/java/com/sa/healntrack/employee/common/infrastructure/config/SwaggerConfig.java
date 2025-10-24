package com.sa.healntrack.employee.common.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info().
                title("Employee Microservice API")
                .description("Employee Microservice API documentation")
                .version("v1.0.0"));
    }
}
