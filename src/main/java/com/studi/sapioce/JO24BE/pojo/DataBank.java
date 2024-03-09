package com.studi.sapioce.JO24BE.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DataBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_id")
    private Long userId;
	
	private boolean dataSaved;
	
	private String nameCard;
	
	private String numberCard;
	
	private String keyCard;
	
	private String dateClosed;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDataSaved() {
		return dataSaved;
	}

	public void setDataSaved(boolean dataSaved) {
		this.dataSaved = dataSaved;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public String getKeyCard() {
		return keyCard;
	}

	public void setKeyCard(String keyCard) {
		this.keyCard = keyCard;
	}

	public String getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(String dateClosed) {
		this.dateClosed = dateClosed;
	}

	public DataBank(long id, boolean dataSaved, String nameCard, String numberCard, String keyCard, String dateClosed) {
		super();
		this.id = id;
		this.dataSaved = dataSaved;
		this.nameCard = nameCard;
		this.numberCard = numberCard;
		this.keyCard = keyCard;
		this.dateClosed = dateClosed;
	}
	
	

}
