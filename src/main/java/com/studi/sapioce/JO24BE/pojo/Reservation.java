package com.studi.sapioce.JO24BE.pojo;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
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

	 @Column(name = "user_id")
	    private Long userId;

	@OneToMany(mappedBy = "reservation")
//    @JsonManagedReference
	private Set<Billet> tickets;

	private LocalDateTime dateReservation;

	private boolean isAcheter;

	public Reservation() {
		super();
	}

	public Reservation(long id, Long userId, Set<Billet> tickets, LocalDateTime dateReservation, boolean isAcheter) {
		super();
		this.id = id;
		this.userId = userId;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	
	/**
     * @param billet Le billet à ajouter.
     */
    public void addTicket(Billet billet) {
        tickets.add(billet);
        billet.setReservation(this);
    }

    /**
     * @param billet Le billet à retirer.
     */
    public void removeTicket(Billet billet) {
        tickets.remove(billet);
        billet.setReservation(null);
    }
}
