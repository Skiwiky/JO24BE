package com.studi.sapioce.JO24BE.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Adress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	private String street;
	
	private String complement;
	
	private String ZipCode;
	
	private String city;
	
	private String State;

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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the complement
	 */
	public String getComplement() {
		return complement;
	}

	/**
	 * @param complement the complement to set
	 */
	public void setComplement(String complement) {
		this.complement = complement;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return ZipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return State;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		State = state;
	}
	
	public Adress() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param user
	 * @param street
	 * @param complement
	 * @param zipCode
	 * @param city
	 * @param state
	 */
	public Adress(long id, User user, String street, String complement, String zipCode, String city, String state) {
		super();
		this.id = id;
		this.user = user;
		this.street = street;
		this.complement = complement;
		ZipCode = zipCode;
		this.city = city;
		State = state;
	}

	

	
	
	
	
	
}
