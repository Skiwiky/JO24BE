package com.studi.sapioce.JO24BE.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.studi.sapioce.JO24BE.security.jwt.AuthEntryPointJwt;
import com.studi.sapioce.JO24BE.security.jwt.AuthTokenFilter;
import com.studi.sapioce.JO24BE.security.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;

	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors() // Activation du support CORS
				.and().csrf(csrf -> csrf.disable()) // Désactiver CSRF car API REST
				.exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Mode
																												// stateless
																												// pour
																												// API
																												// REST
				.authorizeHttpRequests(auth -> auth
						// Routes publiques
						.requestMatchers("/auth/**", "/reservations/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/billetsDisponble/v1/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/billetsDisponble/v1/**").permitAll()
						// Routes nécessitant des rôles spécifiques
						.requestMatchers(HttpMethod.PUT, "/billetsDisponble/v1/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/billetsDisponble/v1/**").hasRole("ADMIN")
						// Toutes les autres requêtes doivent être authentifiées
						.anyRequest().authenticated());

		// Ajout des filtres d'authentification et de token
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
