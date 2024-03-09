/**
 * 
 */
package com.studi.sapioce.JO24BE.pojo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	private String userKey;
	
	private String role;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private Date birthDate;
	
	private String favouriteSport;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Billet> wallet;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "facturation_adress_id", referencedColumnName = "id")
	private Adress adressFacturation;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sending_adress_id", referencedColumnName = "id")
	private Adress adressSending;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "data_bank_id", referencedColumnName = "id")
	private DataBank dataBank;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFavouriteSport() {
		return favouriteSport;
	}

	public void setFavouriteSport(String favouriteSport) {
		this.favouriteSport = favouriteSport;
	}

	public List<Billet> getWallet() {
		return wallet;
	}

	public void setWallet(List<Billet> wallet) {
		this.wallet = wallet;
	}

	public Adress getAdressFacturation() {
		return adressFacturation;
	}

	public void setAdressFacturation(Adress adressFacturation) {
		this.adressFacturation = adressFacturation;
	}

	public Adress getAdressSending() {
		return adressSending;
	}

	public void setAdressSending(Adress adressSending) {
		this.adressSending = adressSending;
	}

	public DataBank getDataBank() {
		return dataBank;
	}

	public void setDataBank(DataBank dataBank) {
		this.dataBank = dataBank;
	}

	public User() {
		super();
	}

	public User(long id, String userKey, String role, String firstName, String lastName, String username,
			String password, Date birthDate, String favouriteSport, List<Billet> wallet, Adress adressFacturation,
			Adress adressSending, DataBank dataBank) {
		super();
		this.id = id;
		this.userKey = userKey;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.favouriteSport = favouriteSport;
		this.wallet = wallet;
		this.adressFacturation = adressFacturation;
		this.adressSending = adressSending;
		this.dataBank = dataBank;
	}
	
}
