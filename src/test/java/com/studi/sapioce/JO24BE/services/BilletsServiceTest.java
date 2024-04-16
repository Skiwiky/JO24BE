package com.studi.sapioce.JO24BE.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.repository.BilletsRepository;
import com.studi.sapioce.JO24BE.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class BilletsServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private BilletsRepository billetRepository;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private BilletsService billetsService;

	/**
	 * 
	 */
	@Test
	public void findAll_ReturnsAllBilletsTest() {
		// Setup
		List<Billet> expectedBillets = new ArrayList<>();
		expectedBillets.add(new Billet());
		when(billetRepository.findAll()).thenReturn(expectedBillets);

		// Execution
		List<Billet> actualBillets = billetsService.findAll();

		// Verification
		assertFalse(actualBillets.isEmpty(), "La liste des billets ne devrait pas être vide");
		verify(billetRepository).findAll();
	}

	/**
	 * 
	 */
	@Test
	public void findById_ReturnsBillet_WhenBilletExistsTest() {
		// Setup
		Billet expectedBillet = new Billet();
		when(billetRepository.findById(1L)).thenReturn(Optional.of(expectedBillet));

		// Execution
		Billet actualBillet = billetsService.findById(1L);

		// Verification
		assertNotNull(actualBillet, "Le billet devrait être trouvé");
		verify(billetRepository).findById(1L);
	}

	/**
	 * 
	 */
	@Test
	public void findById_ThrowsEntityNotFoundException_WhenBilletDoesNotExist() {
		Long nonExistingId = 999L;
		when(billetRepository.findById(1L)).thenReturn(Optional.empty());

		// Execution & Verification
		assertThrows(EntityNotFoundException.class, () -> {
			billetsService.findById(nonExistingId);
		});
	}

	/**
	 * teste de la methode savec pour verifier si on nous a le bon retour dans la methode
	 */
	@Test
	public void save_ReturnsSavedBillet() {
		// Setup
		Billet billetToSave = new Billet();
		User user = new User();
		user.setId(1L);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(passwordEncoder.encode(anyString())).thenReturn("encodedKey");
		when(billetRepository.save(any(Billet.class))).thenReturn(billetToSave);

		// Execution
		Billet savedBillet = billetsService.save(1L, billetToSave);

		// Verification
		assertNotNull(savedBillet, "Le billet devrait être enregistré");
		verify(billetRepository).save(billetToSave);
	}

	/**
	 * test de la methode suppression dans le cas ou le billet existe
	 */
	@Test
	public void deleteById_ReturnsSuccessMessage_WhenBilletExists() {
		// Setup
		when(billetRepository.existsById(1L)).thenReturn(true);

		// Execution
		ResponseMessage response = billetsService.deleteById(1L);

		// Verification
		assertEquals("Billet supprimé avec succès", response.getMessage());
		verify(billetRepository).deleteById(1L);
	}

	/**
	 * test de la methode de suppression si le billet n'existe pas 
	 */
	@Test
	public void deleteById_ReturnsNotFoundMessage_WhenBilletDoesNotExist() {
		// Setup
		when(billetRepository.existsById(1L)).thenReturn(false);

		// Execution
		ResponseMessage response = billetsService.deleteById(1L);

		// Verification
		assertEquals("Billet non trouvé avec l'ID : 1", response.getMessage());
		verify(billetRepository, never()).deleteById(anyLong());
	}

}
