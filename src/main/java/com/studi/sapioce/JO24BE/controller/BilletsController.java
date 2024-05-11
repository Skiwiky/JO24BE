package com.studi.sapioce.JO24BE.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.pojo.dto.BilletDTO;
import com.studi.sapioce.JO24BE.services.BilletsService;

@RestController
@RequestMapping("/billets/v1")
public class BilletsController {

	@Autowired
	private BilletsService billetService;

	@GetMapping
	public ResponseEntity<List<BilletDTO>> getAllBillets() {
		List<BilletDTO> listBillet = new ArrayList<BilletDTO>();
		listBillet = billetService.findAll();
		return new ResponseEntity<>(listBillet, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Billet> getBilletById(@PathVariable Long id) {
		Billet billetFound = new Billet();
		billetFound = billetService.findById(id);
		if (billetFound!= null) {
			return new ResponseEntity<Billet>(billetFound, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Billet> createBillet(@PathVariable Long idUser, @RequestBody Billet billet) {
		Billet billetCreated = new Billet();
		billetCreated= billetService.save(idUser, billet);
		return new ResponseEntity<>(billetCreated, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Billet> updateBillet(@PathVariable Long id, @RequestBody Billet billet) {
		billet.setId(id);
		Billet billetUpdated = new Billet();

		billetUpdated = billetService.save(id, billet);
		if(billetUpdated !=null) {
			return new ResponseEntity<Billet>(billetUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Billet>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteBillet(@PathVariable Long id) {
		ResponseMessage reponse = billetService.deleteById(id);
		return ResponseEntity.ok(reponse);
	}
}
