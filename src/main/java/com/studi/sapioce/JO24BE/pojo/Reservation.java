package com.studi.sapioce.JO24BE.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long idUser;

	private List<Billet> billets;

	private DataBank datatBank;

	private LocalDateTime dateReservation;

	private boolean isAcheter;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the idUser
	 */
	public long getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the billets
	 */
	public List<Billet> getBillets() {
		return billets;
	}

	/**
	 * @param billets the billets to set
	 */
	public void setBillets(List<Billet> billets) {
		this.billets = billets;
	}

	/**
	 * @return the datatBank
	 */
	public DataBank getDatatBank() {
		return datatBank;
	}

	/**
	 * @param datatBank the datatBank to set
	 */
	public void setDatatBank(DataBank datatBank) {
		this.datatBank = datatBank;
	}

	/**
	 * @return the dateReservation
	 */
	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	/**
	 * @param dateReservation the dateReservation to set
	 */
	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	/**
	 * @return the isAcheter
	 */
	public boolean isAcheter() {
		return isAcheter;
	}

	/**
	 * @param isAcheter the isAcheter to set
	 */
	public void setAcheter(boolean isAcheter) {
		this.isAcheter = isAcheter;
	}

	public Reservation() {
		super();
	}

	public Reservation(long id, long idUser, List<Billet> billets, DataBank datatBank, LocalDateTime dateReservation,
			boolean isAcheter) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.billets = billets;
		this.datatBank = datatBank;
		this.dateReservation = dateReservation;
		this.isAcheter = isAcheter;
	}

}
