package com.studi.sapioce.JO24BE.pojo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "reservation")
	private Set<Billet> tickets;

	private LocalDateTime dateReservation;

	private boolean isAcheter;

	public Reservation() {
		super();
	}

	public Reservation(long id, User user, Set<Billet> tickets, LocalDateTime dateReservation, boolean isAcheter) {
		super();
		this.id = id;
		this.user = user;
		this.tickets = tickets;
		this.dateReservation = dateReservation;
		this.isAcheter = isAcheter;
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
	 * @return the tickets
	 */
	public Set<Billet> getTickets() {
		return tickets;
	}

	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(Set<Billet> tickets) {
		this.tickets = tickets;
	}

	/**
	 * @return the dateReservation
	 */
	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	/**
	 * @param dateReservation the dateReservation to set
	 */
	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	/**
	 * @return the isAcheter
	 */
	public boolean isAcheter() {
		return isAcheter;
	}

	/**
	 * @param isAcheter the isAcheter to set
	 */
	public void setAcheter(boolean isAcheter) {
		this.isAcheter = isAcheter;
	}

}
