package com.studi.sapioce.JO24BE.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studi.sapioce.JO24BE.pojo.BilletDisponible;
import com.studi.sapioce.JO24BE.pojo.Enum.BilletStatut;
import com.studi.sapioce.JO24BE.services.BilletsDisponibleService;

@RestController
@RequestMapping("/billetsDisponble/v1")
public class BilletsDisponibleController {
	@Autowired
	private BilletsDisponibleService billetDisponibleService;

	@GetMapping("/{id}")
	public ResponseEntity<BilletDisponible> getBilletDisponible(@PathVariable Long id) {
		BilletDisponible billetDispo = billetDisponibleService.getBilletDisponible(id);
		return new ResponseEntity<BilletDisponible>(billetDispo, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<BilletDisponible>> getBilletsDisponibles(@RequestParam(required = false) String sport,
			@RequestParam(required = false) BilletStatut statut, @RequestParam(required = false) String category,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEvent) {
		List<BilletDisponible> listBilletsDisponible = billetDisponibleService.getBilletsDisponibles(sport, statut,
				category, dateEvent);
		return new ResponseEntity<>(listBilletsDisponible, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BilletDisponible> createBilletDisponible(@RequestBody BilletDisponible billetDisponible) {
		BilletDisponible billetDispoCreated = billetDisponibleService.createBilletDisponible(billetDisponible);
		return new ResponseEntity<>(billetDispoCreated, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BilletDisponible> updateBilletDisponible(@PathVariable Long id,
			@RequestBody BilletDisponible billetDisponible) {
		BilletDisponible billetDispoUpdated = billetDisponibleService.updateBilletDisponible(id, billetDisponible);
		if (billetDispoUpdated != null) {
			return new ResponseEntity<>(billetDispoUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

	}

	@DeleteMapping("/{id}")
	public void deleteBilletDisponible(@PathVariable Long id) {
		billetDisponibleService.deleteBilletDisponible(id);
	}
}
