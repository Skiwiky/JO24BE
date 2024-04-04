package com.studi.sapioce.JO24BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studi.sapioce.JO24BE.pojo.Reservation;
import com.studi.sapioce.JO24BE.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	 private final ReservationService reservationService;

	    @Autowired
	    public ReservationController(ReservationService reservationService) {
	        this.reservationService = reservationService;
	    }

	    @PostMapping
	    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
	        Reservation savedReservation = reservationService.createReservation(reservation);
	        if (savedReservation != null) {
	            return ResponseEntity.ok(savedReservation);
	        } else {
	            return ResponseEntity.badRequest().build();
	        }
	    }
}
