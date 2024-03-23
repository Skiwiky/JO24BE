package com.studi.sapioce.JO24BE.pojo;

import java.time.LocalDate;

import com.studi.sapioce.JO24BE.pojo.Enum.BilletStatut;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BilletDisponible {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String sport;

	private String localisation;

	private LocalDate dateEvent;

	private String category;

	@Enumerated(EnumType.STRING)
	private BilletStatut statut;

	public BilletDisponible() {
		super();
	}

	public BilletDisponible(long id, String sport, String localisation, LocalDate dateEvent, String category,
			BilletStatut statut) {
		super();
		this.id = id;
		this.sport = sport;
		this.localisation = localisation;
		this.dateEvent = dateEvent;
		this.category = category;
		this.statut = statut;
	}

	public long getId() {
		return id;
	}

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

	public LocalDate getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(LocalDate dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BilletStatut getStatut() {
		return statut;
	}

	public void setStatut(BilletStatut statut) {
		this.statut = statut;
	}

}
