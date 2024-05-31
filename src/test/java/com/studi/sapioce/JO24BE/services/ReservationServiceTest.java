package com.studi.sapioce.JO24BE.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.DataBank;
import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.dto.UserPaiementDTO;
import com.studi.sapioce.JO24BE.repository.UserRepository;
import com.studi.sapioce.JO24BE.services.impl.PaiementService;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class ReservationServiceTest {

    @Mock
    private PaiementService paiementService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation_Success() {
        // Mocking User
        User user = new User();
        user.setId(1L);
        user.setPassword("hashedpassword");
        user.setKeyUser("userKey");

        Billet billet = new Billet();
        billet.setSport("Football");
        billet.setDateEvent(new java.util.Date());
        billet.setLocalisation("Paris");
        billet.setPrix(100);

        user.setBillets(Arrays.asList(billet));

        // Mocking UserPaiementDTO
        DataBank dataBank = new DataBank();
        UserPaiementDTO userPaiementDTO = new UserPaiementDTO();
        userPaiementDTO.setUser(user);
        userPaiementDTO.setDataBanks(dataBank);

        // Mocking repository and service responses
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(paiementService.processPaiement(any(DataBank.class), anyFloat())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Execute the service call
        User result = reservationService.createReservation(userPaiementDTO);

        // Verify interactions and assert results
        verify(userRepository, times(1)).findById(1L);
//        verify(paiementService, times(1)).processPaiement(any(DataBank.class), anyFloat());
       // verify(userRepository, times(1)).save(user);

        assertNotNull(result);
        assertEquals(1, result.getBillets().size());
        assertEquals("Football", result.getBillets().get(0).getSport());
        assertNotNull(result.getBillets().get(0).getBilletKey());
        assertNotNull(result.getBillets().get(0).getReservatioKey());
    }

    @Test
    void testCreateReservation_UserNotFound() {
        // Mocking UserPaiementDTO
        User user = new User();
        user.setId(1L);

        DataBank dataBank = new DataBank();
        UserPaiementDTO userPaiementDTO = new UserPaiementDTO();
        userPaiementDTO.setUser(user);
        userPaiementDTO.setDataBanks(dataBank);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            reservationService.createReservation(userPaiementDTO);
        });

        String expectedMessage = "Utilisateur  non trouvé avec  ID: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCreateReservation_PaiementFailure() {
        // Mocking User
        User user = new User();
        user.setId(1L);
        user.setPassword("hashedpassword");
        user.setKeyUser("userKey");

        Billet billet = new Billet();
        billet.setSport("Football");
        billet.setDateEvent(new java.util.Date());
        billet.setLocalisation("Paris");
        billet.setPrix(100);

        user.setBillets(Arrays.asList(billet));

        // Mocking UserPaiementDTO
        DataBank dataBank = new DataBank();
        UserPaiementDTO userPaiementDTO = new UserPaiementDTO();
        userPaiementDTO.setUser(user);
        userPaiementDTO.setDataBanks(dataBank);

        // Mocking repository and service responses
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(paiementService.processPaiement(any(DataBank.class), anyFloat())).thenReturn(false);

        // Execute the service call
        User result = reservationService.createReservation(userPaiementDTO);

        // Verify interactions and assert results
        verify(userRepository, times(1)).findById(1L);
//        verify(paiementService, times(1)).processPaiement(any(DataBank.class), anyFloat());
     //   verify(userRepository, times(1)).save(user);

        assertNotNull(result);
        assertEquals(1, result.getBillets().size());
        assertEquals("Football", result.getBillets().get(0).getSport());
        assertNotNull(result.getBillets().get(0).getBilletKey());
        assertNotNull(result.getBillets().get(0).getReservatioKey());
    }
}
