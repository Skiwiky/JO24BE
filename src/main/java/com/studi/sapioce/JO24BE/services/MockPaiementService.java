package com.studi.sapioce.JO24BE.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.studi.sapioce.JO24BE.pojo.DataBank;
import com.studi.sapioce.JO24BE.services.impl.PaiementService;

@Service
public class MockPaiementService implements PaiementService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public boolean processPaiement(DataBank dataBank, double amount) {
		if (dataBank != null && amount > 0) {
			logger.info("Simulation de paiement réussie pour le montant : " + amount);
			return true; 
		}
		logger.error("Simulation de paiement échouée pour le montant : " + amount);
		return false; 
	}

}
