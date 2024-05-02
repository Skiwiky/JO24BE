package com.studi.sapioce.JO24BE.pojo;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Billet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String sport;

	private String localisation;

	private LocalDate date;

	private String billetKey;

	private String finalKey;

	private float price;

	private String categoryBillet;
	
	private BufferedImage qrCode;

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;

	public Billet() {
		super();
	}

	public Billet(long id, String sport, String localisation, LocalDate date, String billetKey, String finalKey,
			float price, String categoryBillet, BufferedImage qrCode, Reservation reservation) {
		super();
		this.id = id;
		this.sport = sport;
		this.localisation = localisation;
		this.date = date;
		this.billetKey = billetKey;
		this.finalKey = finalKey;
		this.price = price;
		this.categoryBillet = categoryBillet;
		this.reservation = reservation;
	}

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
	 * @return the sport
	 */
	public String getSport() {
		return sport;
	}

	/**
	 * @param sport the sport to set
	 */
	public void setSport(String sport) {
		this.sport = sport;
	}

	/**
	 * @return the localisation
	 */
	public String getLocalisation() {
		return localisation;
	}

	/**
	 * @param localisation the localisation to set
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the billetKey
	 */
	public String getBilletKey() {
		return billetKey;
	}

	/**
	 * @param billetKey the billetKey to set
	 */
	public void setBilletKey(String billetKey) {
		this.billetKey = billetKey;
	}

	/**
	 * @return the finalKey
	 */
	public String getFinalKey() {
		return finalKey;
	}

	/**
	 * @param finalKey the finalKey to set
	 */
	public void setFinalKey(String finalKey) {
		this.finalKey = finalKey;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the categoryBillet
	 */
	public String getCategoryBillet() {
		return categoryBillet;
	}

	/**
	 * @param categoryBillet the categoryBillet to set
	 */
	public void setCategoryBillet(String categoryBillet) {
		this.categoryBillet = categoryBillet;
	}

	/**
	 * @return the qrCode
	 */
	public BufferedImage getQrCode() {
		return qrCode;
	}

	/**
	 * @param qrCode the qrCode to set
	 */
	public void setQrCode(BufferedImage qrCode) {
		this.qrCode = qrCode;
	}

	/**
	 * @return the reservation
	 */
	public Reservation getReservation() {
		return reservation;
	}

	/**
	 * @param reservation the reservation to set
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}
