package com.studi.sapioce.JO24BE.pojo.dto;

import java.util.List;

public class UserDTO {
	private String id;
	private String username;
	private String firstName;
	private String lastName;
	private String favouriteSport;
	private List<BilletDTO> billets;

	// Constructeurs
	public UserDTO() {
	}

	public UserDTO(String id, String username, String firstName, String lastName, String favouriteSport,
			List<BilletDTO> billets) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.favouriteSport = favouriteSport;
		this.billets = billets;
	}

	// Getters et Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFavouriteSport() {
		return favouriteSport;
	}

	public void setFavouriteSport(String favouriteSport) {
		this.favouriteSport = favouriteSport;
	}

	public List<BilletDTO> getBillets() {
		return billets;
	}

	public void setBillets(List<BilletDTO> billets) {
		this.billets = billets;
	}
}
