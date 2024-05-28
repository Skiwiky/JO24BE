package com.studi.sapioce.JO24BE.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.DataBank;
import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.dto.BilletDTO;
import com.studi.sapioce.JO24BE.pojo.dto.UserDTO;
import com.studi.sapioce.JO24BE.pojo.dto.UserPaiementDTO;
import com.studi.sapioce.JO24BE.repository.BilletsRepository;
import com.studi.sapioce.JO24BE.repository.UserRepository;
import com.studi.sapioce.JO24BE.services.impl.PaiementService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

	@Autowired
	private PaiementService paiementService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BilletsRepository billetsRepository;

	public User createReservation(@RequestBody UserPaiementDTO userPaimentDTO) {
		User user = userRepository.findById(userPaimentDTO.getUser().getId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Utilisateur  non trouvé avec  ID: " + userPaimentDTO.getUser().getId()));

		// TODO a revoir absolument la construction de la methode
		DataBank dataBankDTO = userPaimentDTO.getDataBanks();
		User userDTO = userPaimentDTO.getUser();
		userDTO.setPassword(user.getPassword());

		float totalPrix = 0;

		// creation de la clé de chaque billet et calcul du prix global
		for (Billet billet : userDTO.getBillets()) {
			String billetKey = billet.getSport() + billet.getDateEvent() + billet.getLocalisation() + billet.getPrix();
			totalPrix += billet.getPrix();
			billet.setBilletKey(billetKey);
			billet.setUser(userDTO);
		}

		// Appel Mock de paiement
		// creation de la clé pour hh
		try {
			boolean paiementSuccess = paiementService.processPaiement(dataBankDTO, totalPrix);
//			if (paiementSuccess) {
//				throw new RuntimeException("Le paiement a échoué");
//			}
			for (Billet billet : userDTO.getBillets()) {
				billet.setReservatioKey(userDTO.getKeyUser() + billet.getBilletKey());
				billet.setShortKey(generateUniqueShortKey(billet.getReservatioKey()));
			}
			System.out.println("affiche " + userDTO.getBillets());
			userRepository.save(userDTO); // Sauvegarde de la réservation avec les billets liés

		} catch (Exception e) {
			// TODO: handle exception
		}

		return userDTO;
	}

	// methode pouer verifier si la cle est présente
	public UserDTO checkShortKey(String shortKey) {
		try {
			Billet billet = billetsRepository.findByShortKey(shortKey);
			if (billet != null) {
				long idUser = billet.getUser().getId();
				User user = userRepository.findById(idUser)
						.orElseThrow(() -> new EntityNotFoundException("User not found with ID : " + idUser));
				user.setPassword(null);
				 return convertToUserDTO(user);
			} else {
				throw new Exception("Réservation non trouvée.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block e.printStackTrace();

			e.printStackTrace();
			throw new RuntimeException("Erreur lors de la vérification de la clé.", e);
		}
	}

	// merthode pour généerer une clé reduite a partir d'une ReservationKey
	public String generateUniqueShortKey(String reservationKey) {
		String shortKey;
		do {
			shortKey = generateShortKey(reservationKey);
		} while (billetsRepository.existsByShortKey(shortKey));
		return shortKey;
	}

	private String generateShortKey(String reservationKey) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(reservationKey.getBytes(StandardCharsets.UTF_8));
			String base64 = Base64.getEncoder().encodeToString(hash);
			return base64.replaceAll("[^A-Za-z]", "").substring(0, 5).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error generating key", e);
		}
	}

	private UserDTO convertToUserDTO(User user) {
		List<BilletDTO> billetDTOs = user.getBillets().stream().map(this::convertToBilletDTO)
				.collect(Collectors.toList());

		return new UserDTO(String.valueOf(user.getId()), user.getUsername(), user.getFirstName(), user.getLastName(),
				user.getFavouriteSport(), billetDTOs);
	}

	private BilletDTO convertToBilletDTO(Billet billet) {
		return new BilletDTO(billet.getSport(), billet.getLocalisation(), billet.getDateEvent(), billet.getCategory(),
				billet.getPrix(), billet.getDateAchat(), billet.getReservatioKey(), billet.getShortKey());
	}

}
