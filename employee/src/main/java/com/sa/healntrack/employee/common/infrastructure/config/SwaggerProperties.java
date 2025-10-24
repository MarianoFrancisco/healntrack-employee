package com.sa.healntrack.employee.common.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {
    private String apiGatewayUrl;
}
