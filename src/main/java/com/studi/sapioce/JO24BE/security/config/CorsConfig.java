package com.studi.sapioce.JO24BE.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        
        // Autoriser les origines spécifiques
        config.setAllowedOriginPatterns(Arrays.asList(
                "https://jo2024sapioce.web.app",
                "http://localhost:4200"
        ));
        
        // Autoriser tous les headers et les méthodes
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        
        // Ajouter les en-têtes nécessaires pour les requêtes CORS
        config.addExposedHeader("Authorization");
        
        // Appliquer la configuration à toutes les routes
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
