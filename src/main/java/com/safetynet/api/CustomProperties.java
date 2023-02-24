package com.safetynet.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.safetynet.api")
public class CustomProperties {
    private String jsonPath;
}
