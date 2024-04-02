package com.studi.sapioce.JO24BE.pojo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReservationTicket {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private float price;
	
	private Date dateReservation;

	private long idUser;
	
	private List<Billet> listBillet;
}
