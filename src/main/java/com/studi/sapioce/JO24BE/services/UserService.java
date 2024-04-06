package com.studi.sapioce.JO24BE.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studi.sapioce.JO24BE.pojo.Adress;
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

	@Transactional
	public User save(User user) {
		User userSaved = user;

		// On verifie si l'email est au bon format
		if (!Utils.estValidFormatString(utils.getFormatEmail(), userSaved.getUsername())) {
			throw new IllegalArgumentException("Le format de l'email est invalide.");
		}

		// on verifie si le mdp est au bon format
//		if (!Utils.estValidFormatString(utils.getFormatPassword(), userSaved.getPassword())) {
//			throw new IllegalArgumentException("Le format du mot de passe est invalide.");
//		}
		userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
		userSaved.setUserKey(passwordEncoder
				.encode(userSaved.getFirstName() + "-" + userSaved.getLastName() + "-" + Instant.now().toEpochMilli()));
		userSaved.setRole("USER");

		try {
			userRepository.save(userSaved);
			logger.info("L'utilsateur a bien été enregistré.");
		} catch (Exception e) {
			logger.error("L'utilisateur n'a pas put etre enregistré: " + e);
		}

		return userSaved;
	}

	public List<User> getAllUser() {
		List<User> userList = new ArrayList<User>();
		try {
			userList = userRepository.findAll();

		} catch (Exception e) {
			logger.error("Impossible de remonté la liste des utilisateur: " + e);
		}
		return userList;
	}

	public User getUserById(Long userId) {
		User user = new User();
		try {
			user = userRepository.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User not found with ID : " + userId));
		} catch (Exception e) {
			logger.error("Impossible de recupérer l'utilateur " + userId + ": " + e);
		}

		return user;
	}

	@Transactional
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

		userToUserUpdated(user, userUpdated);

		try {
			userRepository.save(userUpdated);
			logger.info("L'utilsateur a bien été mise a jour.");
		} catch (Exception e) {
			logger.error("L'utilisateur n'a pas put etre mise a jour: " + e);
		}

		return userUpdated;
	}

	@Transactional
	public ResponseMessage deleteUser(Long userId) {
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
		// verifie et recupere le User a partir du mail
		if (!Utils.estValidFormatString(utils.getFormatEmail(), email)) {
			throw new IllegalArgumentException("Le format de l'email est invalide.");
		}
		if (!userRepository.existsByUsername(email)) {
			return false;
		}
		return true;
	}

	public User findByUsername(String username) {
		User user = new User();
		try {
			user = userRepository.findByUsername(username)
					.orElseThrow(() -> new EntityNotFoundException("User not found with ID : " + username));
		} catch (Exception e) {
			logger.error("Impossible derecupérer l'utilateur " + username + ": " + e);
		}

		return user;
	}

	private void userToUserUpdated(User user, User userUpdated) {
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			userUpdated.setPassword(user.getPassword());
		}

		if (user.getFirstName() != null)
			userUpdated.setFirstName(user.getFirstName());
		if (user.getLastName() != null)
			userUpdated.setLastName(user.getLastName());
		if (user.getUsername() != null)
			userUpdated.setUsername(user.getUsername());

		if (user.getDataBanks() != null && user.getDataBanks().isDataSaved() == true) {
			//TODO Mettre a jour les données bancaire
			userUpdated.getDataBanks().setNumberCard(user.getDataBanks().getNumberCard());
			userUpdated.getDataBanks().setNameCard(user.getDataBanks().getNameCard());
			userUpdated.getDataBanks().setDateClosed(user.getDataBanks().getDateClosed());
			userUpdated.getDataBanks().setCvv(user.getDataBanks().getCvv());

		}

	}

}
