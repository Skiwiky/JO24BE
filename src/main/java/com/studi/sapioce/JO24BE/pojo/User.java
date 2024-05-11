/**
 * 
 */
package com.studi.sapioce.JO24BE.pojo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * 
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column
	private String favouriteSport;

	@Column(nullable = false)
	private String keyUser;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdate;

	@Column
	private String role;

	private boolean isAccepteCGU = false;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Billet> billets;

	public User() {
		super();
	}

	public User(long id, String username, String password, String firstName, String lastName, String favouriteSport,
			String keyUser, Date dateCreated, Date birthdate, String role, boolean isAccepteCGU, List<Billet> billets) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.favouriteSport = favouriteSport;
		this.keyUser = keyUser;
		this.dateCreated = dateCreated;
		this.birthdate = birthdate;
		this.role = role;
		this.isAccepteCGU = isAccepteCGU;
		this.billets = billets;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the favouriteSport
	 */
	public String getFavouriteSport() {
		return favouriteSport;
	}

	/**
	 * @param favouriteSport the favouriteSport to set
	 */
	public void setFavouriteSport(String favouriteSport) {
		this.favouriteSport = favouriteSport;
	}

	/**
	 * @return the keyUser
	 */
	public String getKeyUser() {
		return keyUser;
	}

	/**
	 * @param keyUser the keyUser to set
	 */
	public void setKeyUser(String keyUser) {
		this.keyUser = keyUser;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the isAccepteCGU
	 */
	public boolean isAccepteCGU() {
		return isAccepteCGU;
	}

	/**
	 * @param isAccepteCGU the isAccepteCGU to set
	 */
	public void setAccepteCGU(boolean isAccepteCGU) {
		this.isAccepteCGU = isAccepteCGU;
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
}
