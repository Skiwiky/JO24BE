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
		//UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwToken(authentication);

		// Récupérer les détails de l'utilisateur à partir de l'objet Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        user.setPassword(null);
        
        
        // Retourner la réponse avec le token JWT et les détails de l'utilisateur
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails, user));
 
	}

	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
        if(userService.findUser(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: le username est deja existant."));
        }
        

        //créer un nouvel User
        userService.save(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("L'utilisateur est créer!"));
    }
}
