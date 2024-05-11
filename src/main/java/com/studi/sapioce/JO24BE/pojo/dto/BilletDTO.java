package com.studi.sapioce.JO24BE.pojo.dto;

import java.util.Date;

public class BilletDTO {
	
	private String sport;

	private String localisation;

	private Date dateEvent;

	private String category;

	private double prix;

	private Date dateAchat;

	public BilletDTO() {
		super();
	}
	
	public BilletDTO(String sport, String localisation, Date dateEvent, String category, double prix, Date dateAchat) {
		super();
		this.sport = sport;
		this.localisation = localisation;
		this.dateEvent = dateEvent;
		this.category = category;
		this.prix = prix;
		this.dateAchat = dateAchat;
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
	 * @return the dateEvent
	 */
	public Date getDateEvent() {
		return dateEvent;
	}

	/**
	 * @param dateEvent the dateEvent to set
	 */
	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the dateAchat
	 */
	public Date getDateAchat() {
		return dateAchat;
	}

	/**
	 * @param dateAchat the dateAchat to set
	 */
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
}
