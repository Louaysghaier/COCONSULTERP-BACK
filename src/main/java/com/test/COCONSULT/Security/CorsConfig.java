package com.test.COCONSULT.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public Filter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Configure CORS for WebSocket endpoint
        CorsConfiguration wsConfig = new CorsConfiguration();
        wsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        wsConfig.addAllowedHeader("*");
        wsConfig.addAllowedMethod("*");
        wsConfig.setAllowCredentials(true);
        source.registerCorsConfiguration("/ws/**", wsConfig);

        // Configure CORS for API endpoints
        CorsConfiguration apiConfig = new CorsConfiguration();
        apiConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        apiConfig.addAllowedHeader("*");
        apiConfig.addAllowedMethod("*");
        apiConfig.setAllowCredentials(true);
        source.registerCorsConfiguration("/api/**", apiConfig);

        return new CorsFilter(source);
    }
}


