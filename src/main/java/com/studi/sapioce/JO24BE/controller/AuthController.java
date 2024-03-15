package com.studi.sapioce.JO24BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.repository.UserRepository;
import com.studi.sapioce.JO24BE.security.jwt.JwtUtils;
import com.studi.sapioce.JO24BE.security.payload.request.LoginRequest;
import com.studi.sapioce.JO24BE.security.payload.request.SignUpRequest;
import com.studi.sapioce.JO24BE.security.payload.response.JwtResponse;
import com.studi.sapioce.JO24BE.security.payload.response.MessageResponse;
import com.studi.sapioce.JO24BE.security.service.UserDetailsImpl;
import com.studi.sapioce.JO24BE.security.service.UserDetailsServiceImpl;
import com.studi.sapioce.JO24BE.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserService userService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// Charger les détails de l'utilisateur à partir de la base de données
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwToken(authentication);
		User user = (User) authentication.getPrincipal();

		user = userService.findByUsername(user.getUsername());

//        mise a vide du pwd
		user.setPassword(null);

		return ResponseEntity.ok(new JwtResponse(jwt, user));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
		if (userService.findUser(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: le username est deja existant."));
		}

		// créer un nouvel User
		User usersaved = userService.save(signUpRequest);
		// Charger les détails de l'utilisateur à partir de la base de données
//				UserDetails userDetails = userDetailsService.loadUserByUsername(usersaved.getUsername());
		// On connecte l'utilisateur
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usersaved.getUsername(), null));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Générer le JWT
		String jwt = jwtUtils.generateJwToken(authentication);
		usersaved = (User) authentication.getPrincipal();

//        mise a vide du pwd
		usersaved.setPassword(null);

		return ResponseEntity.ok(new JwtResponse(jwt, usersaved));
	}
}
