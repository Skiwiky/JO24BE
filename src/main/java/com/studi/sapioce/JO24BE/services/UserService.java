package com.studi.sapioce.JO24BE.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.pojo.Utils.Utils;
import com.studi.sapioce.JO24BE.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	Utils utils;

	public User save(User user) {
		User userSaved = user;

		// On verifie si l'email est au bon format
		if (!Utils.estValidFormatString(utils.getFormatEmail(), userSaved.getPassword())) {
			throw new IllegalArgumentException("Le format de l'email est invalide.");
		}

		// on verifie si le mdp est au bon format
		if (!Utils.estValidFormatString(utils.getFormatPassword(), userSaved.getPassword())) {
			throw new IllegalArgumentException("Le format du mot de passe est invalide.");
		}
		userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
		userSaved.setUserKey(passwordEncoder.encode(userSaved.getFirstName() + "-" + userSaved.getLastName() + "-"
				+ userSaved.getBirthDate() + "-" + Instant.now().toEpochMilli()));

		try {
			userRepository.save(userSaved);
			logger.info("L'utilsateur a bien été enregistré.");
		} catch (Exception e) {
			logger.error("L'utilisateur n'a pas put etre enregistré: " + e);
		}

		return userSaved;
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		try {
			userList = userRepository.findAll();

		} catch (Exception e) {
			logger.error("Impossible de remonté la liste des utilisateur: " + e);
		}
		return userList;
	}

	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		User user = new User();
		try {
			user = userRepository.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User not found with ID : " + userId));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Impossible derecupérer l'utilateur " + userId + ": " + e);
		}

		return user;
	}

	public User updateUser(Long userId, User user) {
		// TODO Auto-generated method stub
		User userUpdated = new User();
		try {
			userUpdated = userRepository.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User not found with ID : " + userId));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Impossible derecupérer l'utilateur " + userId + ": " + e);
		}
		userUpdated = user;
		return userUpdated;
	}

	public ResponseMessage deleteUser(Long userId) {
		// TODO Auto-generated method stub
		// On verifie si l'utilisateur exist
		if (!userRepository.existsById(userId)) {
			return new ResponseMessage("Utilisateur non trouvé avec l'ID : " + userId);
		}
		try {
			userRepository.deleteById(userId);
			return new ResponseMessage("Utilisateur supprimé avec succès");
		} catch (Exception e) {
			logger.error("Impossible de supprimer l'utilisateur: " + e.getMessage());
			return new ResponseMessage("Erreur lors de la suppression de l'utilisateur");
		}
	}

	// On verifie si le mail est deja utilisé pour un autre utilisateur
	public boolean findUser(String email) {
		// TODO Auto-generated method stub
		// verifie et recupere le User a partir du mail
		if (!Utils.estValidFormatString(utils.getFormatEmail(), email)) {
			throw new IllegalArgumentException("Le format de l'email est invalide.");
		}
		if (!userRepository.existsByUsername(email)) {
			return false;
		}
		return true;
	}

}
