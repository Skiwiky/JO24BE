package com.studi.sapioce.JO24BE.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DataBank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	private boolean dataSaved;
	
	private String nameCard;
	
	private String numberCard;
	
	private String keyCard;
	
	private String dateClosed;

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

	/**
	 * @return the dataSaved
	 */
	public boolean isDataSaved() {
		return dataSaved;
	}

	/**
	 * @param dataSaved the dataSaved to set
	 */
	public void setDataSaved(boolean dataSaved) {
		this.dataSaved = dataSaved;
	}

	/**
	 * @return the nameCard
	 */
	public String getNameCard() {
		return nameCard;
	}

	/**
	 * @param nameCard the nameCard to set
	 */
	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	/**
	 * @return the numberCard
	 */
	public String getNumberCard() {
		return numberCard;
	}

	/**
	 * @param numberCard the numberCard to set
	 */
	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	/**
	 * @return the keyCard
	 */
	public String getKeyCard() {
		return keyCard;
	}

	/**
	 * @param keyCard the keyCard to set
	 */
	public void setKeyCard(String keyCard) {
		this.keyCard = keyCard;
	}

	/**
	 * @return the dateClosed
	 */
	public String getDateClosed() {
		return dateClosed;
	}

	/**
	 * @param dateClosed the dateClosed to set
	 */
	public void setDateClosed(String dateClosed) {
		this.dateClosed = dateClosed;
	}

	public DataBank() {
		super();
	}

	public DataBank(long id, User user, boolean dataSaved, String nameCard, String numberCard, String keyCard,
			String dateClosed) {
		super();
		this.id = id;
		this.user = user;
		this.dataSaved = dataSaved;
		this.nameCard = nameCard;
		this.numberCard = numberCard;
		this.keyCard = keyCard;
		this.dateClosed = dateClosed;
	}

	
	

}
