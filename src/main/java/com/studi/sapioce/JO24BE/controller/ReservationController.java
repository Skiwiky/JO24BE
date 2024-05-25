package com.studi.sapioce.JO24BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.dto.UserPaiementDTO;
import com.studi.sapioce.JO24BE.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<User> createReservation(@RequestBody UserPaiementDTO userPaiementDTO) {
		User savedUserReservation = reservationService.createReservation(userPaiementDTO);
		if (savedUserReservation != null) {
			return ResponseEntity.ok(savedUserReservation);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	 @GetMapping("/check/{reservationKey}")
	    public ResponseEntity<Boolean> checkReservationKey(@PathVariable String reservationKey) {
	        boolean exists = reservationService.checkReservationKey(reservationKey);
	        return ResponseEntity.ok(exists);
	    }
}
