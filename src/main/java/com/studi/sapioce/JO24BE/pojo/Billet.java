package com.studi.sapioce.JO24BE.pojo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Billet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String sport;
	
	private String localisation;
	
	private Date date;
	
	private String billetKey;
	
	private String finalKey;
	
	private float price;

	private String categoryBillet;

	public long getId() {
		return id;
	}
	
	@Column(name = "user_id")
    private Long userId;
	
	public void setId(long id) {
		this.id = id;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBilletKey() {
		return billetKey;
	}

	public void setBilletKey(String billetKey) {
		this.billetKey = billetKey;
	}

	public String getFinalKey() {
		return finalKey;
	}

	public void setFinalKey(String finalKey) {
		this.finalKey = finalKey;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCategoryBillet() {
		return categoryBillet;
	}

	public void setCategoryBillet(String categoryBillet) {
		this.categoryBillet = categoryBillet;
	}

	public Billet(long id, String sport, String localisation, Date date, String billetKey, String finalKey, float price,
			String categoryBillet) {
		super();
		this.id = id;
		this.sport = sport;
		this.localisation = localisation;
		this.date = date;
		this.billetKey = billetKey;
		this.finalKey = finalKey;
		this.price = price;
		this.categoryBillet = categoryBillet;
	}
	
	
}
