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

		// Activer les identifiants et l'authentification pour les requêtes CORS
		config.setAllowCredentials(true);

		// Spécifier l'URL autorisée
		config.setAllowedOriginPatterns(Arrays.asList("https://jo2024sapioce.web.app"));

		// Autoriser tous les headers et méthodes
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");

		// Ajouter les headers nécessaires pour l'exposition du token
		config.addExposedHeader("Authorization");

		// Appliquer la configuration à toutes les routes
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
