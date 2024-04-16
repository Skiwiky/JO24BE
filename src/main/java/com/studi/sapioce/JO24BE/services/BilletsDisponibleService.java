package com.studi.sapioce.JO24BE.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studi.sapioce.JO24BE.pojo.BilletDisponible;
import com.studi.sapioce.JO24BE.pojo.Enum.BilletStatut;
import com.studi.sapioce.JO24BE.repository.BilletsDisponibleRepository;

@Service
public class BilletsDisponibleService {

	private static final Logger logger = LoggerFactory.getLogger(BilletDisponible.class);

	@Autowired
	private BilletsDisponibleRepository billetDisponibleRepository;

	public BilletDisponible getBilletDisponible(Long id) {
		BilletDisponible billetDispo = new BilletDisponible();
		try {
			billetDispo = billetDisponibleRepository.findById(id)
					.orElseThrow(() -> new NoSuchElementException("Billet disponible non trouvé pour l'ID : " + id));
		} catch (NoSuchElementException e) {
			logger.error("Impossible de récupérer le billet" + e);
		}
		return billetDispo;
	}

	public List<BilletDisponible> getBilletsDisponibles(String sport, BilletStatut statut, String category,
			LocalDate dateEvent) {

		List<BilletDisponible> listBilletDispo = new ArrayList<BilletDisponible>();
		try {
			listBilletDispo = billetDisponibleRepository.findAll();
//			listBilletDispo = billetDisponibleRepository.findBySportAndStatutAndCategoryAndDateEvent(sport, statut,
//					category, dateEvent);
			logger.info("La liste des billets à boen été récupérée.");
		} catch (Exception e) {
			logger.error("Impossible de récupérer la liste de billet " + e);
		}
		return listBilletDispo;
	}

	@Transactional
	public BilletDisponible createBilletDisponible(BilletDisponible billetDisponible) {
		BilletDisponible billetDispo = new BilletDisponible();

		try {
			billetDispo = billetDisponibleRepository.save(billetDisponible);
		} catch (Exception e) {
			logger.error("Le billet n'a pas été enregistré " + e);
		}

		return billetDispo;
	}

	@Transactional
	public BilletDisponible updateBilletDisponible(Long id, BilletDisponible billetDisponible) {
		BilletDisponible existingBilletDisponible = getBilletDisponible(id);
		existingBilletDisponible.setSport(billetDisponible.getSport() != null ? billetDisponible.getSport()
				: existingBilletDisponible.getSport());
		existingBilletDisponible
				.setLocalisation(billetDisponible.getLocalisation() != null ? billetDisponible.getLocalisation()
						: existingBilletDisponible.getLocalisation());
		existingBilletDisponible.setDateEvent(billetDisponible.getDateEvent() != null ? billetDisponible.getDateEvent()
				: existingBilletDisponible.getDateEvent());
		existingBilletDisponible.setCategory(billetDisponible.getCategory() != null ? billetDisponible.getCategory()
				: existingBilletDisponible.getCategory());
		existingBilletDisponible.setStatut(billetDisponible.getStatut() != null ? billetDisponible.getStatut()
				: existingBilletDisponible.getStatut());
		try {
			existingBilletDisponible = billetDisponibleRepository.save(existingBilletDisponible);
			logger.info("Le billet a été mise à jour.");
		} catch (Exception e) {
			logger.error("Impossible de mettre à jour le billet. " + e);
		}
		return existingBilletDisponible;
	}

	/**
	 * On ne supprime pas le billets, on change sont etat.
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteBilletDisponible(Long id) {
		BilletDisponible existingBilletDisponible = getBilletDisponible(id);
		existingBilletDisponible.setStatut(BilletStatut.DESACTIVED);
		try {
			billetDisponibleRepository.save(existingBilletDisponible);
		} catch (Exception e) {
			logger.error("Impossible de mettre le statut du billet à DESACTIVED, " + e);
		}
	}
}
