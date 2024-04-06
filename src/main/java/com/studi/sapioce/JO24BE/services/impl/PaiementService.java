package com.studi.sapioce.JO24BE.services.impl;

import com.studi.sapioce.JO24BE.pojo.DataBank;

public interface PaiementService {
	boolean processPaiement(DataBank dataBank, double amount);
}
