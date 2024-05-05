package com.studi.sapioce.JO24BE.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

	@Autowired
	private PaiementService paiementService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;

	public Reservation createReservation(Reservation reservation) {
		// TODO a revoir absolument la construction de la methode
		User user = userRepository.findById(reservation.getUserId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Utilisateur  non trouvé avec  ID: " + reservation.getUserId()));

		float totalPrix = reservation.getTickets().stream().map(Billet::getPrice).reduce(0f, Float::sum);

		// creation de la clé de chaque billet et calcul du prix global
		for (Billet billet : reservation.getTickets()) {
			String billetKey = billet.getSport() + billet.getDate() + billet.getLocalisation() + billet.getPrice();
			billet.setBilletKey(billetKey);
		}

		// Appel Mock de paiement
		// creation de la clé pour hh
		try {
			boolean paiementSuccess = paiementService.processPaiement(reservation.getUser().getDataBanks(), totalPrix);
			if (!paiementSuccess) {
				throw new RuntimeException("Le paiement a échoué");
			}

			// Liaison des billets à la réservation et peut-être à l'utilisateur
			reservation.setTickets(reservation.getTickets().stream().peek(billet -> {
				billet.setReservation(reservation); // Liaison billet-réservation
			}).collect(Collectors.toSet()));

			reservation.setUser(user);
			reservationRepository.save(reservation); // Sauvegarde de la réservation avec les billets liés

		} catch (Exception e) {
			// TODO: handle exception
		}

		return reservation;
	}
}
