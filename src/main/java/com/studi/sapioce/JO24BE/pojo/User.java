/**
 * 
 */
package com.studi.sapioce.JO24BE.pojo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private Date birthDate;
	
	private String favouriteSport;

	private String userKey;

	private List<Billet> wallet;

	private String role;
	
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
	 * @return the email
	 */
	public String getUserName() {
		return username;
	}



	/**
	 * @param email the email to set
	 */
	public void setUserName(String email) {
		this.username = email;
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
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}



	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}



	/**
	 * @param userKey the userKey to set
	 */
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}



	/**
	 * @return the wallet
	 */
	public List<Billet> getWallet() {
		return wallet;
	}



	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(List<Billet> wallet) {
		this.wallet = wallet;
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



	public User(long id, String firstName, String lastName, String email, String password, Date birthDate,
			String favouriteSport, String userKey, List<Billet> wallet, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = email;
		this.password = password;
		this.birthDate = birthDate;
		this.favouriteSport = favouriteSport;
		this.userKey = userKey;
		this.wallet = wallet;
		this.role = role;
	}
	
	
	
	
}
