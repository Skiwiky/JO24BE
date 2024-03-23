package com.studi.sapioce.JO24BE.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.repository.BilletsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BilletsService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private BilletsRepository billetRepository;

	/**
	 * Permer de retourner l'ensembles des billets achetés.
	 * 
	 * @return
	 */
	public List<Billet> findAll() {
		List<Billet> listBillets = new ArrayList<Billet>();
		try {
			listBillets = billetRepository.findAll();
		} catch (Exception e) {
			logger.error("Impossible de récupérer la liste des billets, " + e);
		}
		return listBillets;
	}

	/**
	 * Retourne un billet
	 * 
	 * @param id
	 * @return
	 */
	public Billet findById(Long id) {
		Billet billet = new Billet();
		try {
			billet = billetRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Billet not found: " + id));
			logger.info("le billet à bien été trouvé.");
		} catch (Exception e) {
			logger.error("Impossible de récuperer le billet. " + e);
		}
		return billet;
	}

	@Transactional
	public Billet save(Billet billet) {
		Billet billetSaved = new Billet();
		// TODO AJOUTER LES CREATIONS DES CLES
		// voir si je dois passer l'id en paramètre.

		try {
			billetSaved = billetRepository.save(billet);
		} catch (Exception e) {
			logger.error("Impossible d'enregistrer le billet." + e);
		}
		return billetSaved;
	}

	@Transactional
	public ResponseMessage deleteById(Long id) {
		if (!billetRepository.existsById(id)) {
			return new ResponseMessage("Billet non trouvé avec l'ID : " + id);
		}
		try {
			billetRepository.deleteById(id);
			return new ResponseMessage("Billet supprimé avec succès");
		} catch (Exception e) {
			logger.error("Impossible de supprimer le billet: " + e.getMessage());
			return new ResponseMessage("Erreur lors de la suppression du billet");
		}

	}

}
