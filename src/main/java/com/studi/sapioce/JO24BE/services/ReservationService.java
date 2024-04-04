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

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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

		// Appel Mock de paiement - passer par la transaction comme le systeme bancaire
		// creation de la clé pour hh
		try {
//TODO mettre en place lappel de paiement 
			for (Billet billet : billets) {
				billet.setFinalKey(billet.getBilletKey() + user.getUserKey());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Save la reservation une fois le paiement
		try {
			reservationRepository.save(reservation);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return reservationRepository.save(reservation);
	}
}
