package com.studi.sapioce.JO24BE.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.Reservation;
import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.repository.ReservationRepository;
import com.studi.sapioce.JO24BE.repository.UserRepository;
import com.studi.sapioce.JO24BE.services.impl.PaiementService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PaiementService paiementService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Reservation createReservation(Reservation reservation) {
		List<Billet> billets = reservation.getBillets();
		User user = userRepository.findById(reservation.getIdUser()).orElseThrow(
				() -> new EntityNotFoundException("Utilisateur  non trouvé avec  ID: " + reservation.getIdUser()));

		// creation de la clé de chaque billet
		for (Billet billet : billets) {
			String billetKey = passwordEncoder
					.encode(billet.getSport() + billet.getDate() + billet.getLocalisation() + billet.getPrice());
			billet.setBilletKey(billetKey);
		}

		// Appel Mock de paiement
		// creation de la clé pour hh
		try {
			// TODO mettre en place lappel de paiement
			boolean paiementSuccess = paiementService.processPaiement(reservation.getDatatBank(),
					reservation.getTotalPrix());
			if (!paiementSuccess) {
				throw new RuntimeException("Le paiement a échoué");
			} else {
				for (Billet billet : billets) {
					billet.setFinalKey(billet.getBilletKey() + user.getUserKey());
				}
				// Save la reservation une fois le paiement
				try {
					user.setWallet(billets);
					reservationRepository.save(reservation);
					userRepository.save(user);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return reservationRepository.save(reservation);
	}
}
