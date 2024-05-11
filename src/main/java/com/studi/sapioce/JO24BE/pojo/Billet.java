package com.studi.sapioce.JO24BE.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Billet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String sport;

	@Column(nullable = false)
	private String localisation;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateEvent;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private double prix;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAchat;

	@Column(nullable = false)
	private String billetKey;

	@Column(nullable = false)
	private String reservatioKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    @JsonIgnoreProperties("billets")
	private User user;

	public Billet() {
		super();
	}

	public Billet(long id, String sport, String localisation, Date dateEvent, String category, double prix,
			Date dateAchat, String billetKey, String reservatioKey, User user) {
		super();
		this.id = id;
		this.sport = sport;
		this.localisation = localisation;
		this.dateEvent = dateEvent;
		this.category = category;
		this.prix = prix;
		this.dateAchat = dateAchat;
		this.billetKey = billetKey;
		this.reservatioKey = reservatioKey;
		this.user = user;
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
	 * @return the reservatioKey
	 */
	public String getReservatioKey() {
		return reservatioKey;
	}

	/**
	 * @param reservatioKey the reservatioKey to set
	 */
	public void setReservatioKey(String reservatioKey) {
		this.reservatioKey = reservatioKey;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
