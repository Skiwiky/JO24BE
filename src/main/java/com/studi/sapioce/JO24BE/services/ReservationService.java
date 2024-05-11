package com.studi.sapioce.JO24BE.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.DataBank;
import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.dto.UserPaiementDTO;
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
				billet.setReservatioKey(userDTO.getKeyUser()+ billet.getBilletKey());
			}
			System.out.println("affiche "+ userDTO.getBillets());
			userRepository.save(userDTO); // Sauvegarde de la réservation avec les billets liés

		} catch (Exception e) {
			// TODO: handle exception
		}

		return userDTO;
	}
}
