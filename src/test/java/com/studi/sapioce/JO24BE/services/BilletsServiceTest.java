package com.studi.sapioce.JO24BE.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.studi.sapioce.JO24BE.pojo.Billet;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.pojo.dto.BilletDTO;
import com.studi.sapioce.JO24BE.repository.BilletsRepository;
import com.studi.sapioce.JO24BE.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class BilletsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BilletsRepository billetRepository;

    @InjectMocks
    private BilletsService billetsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Billet billet = new Billet();
        billet.setSport("Football");
        billet.setLocalisation("Paris");
        billet.setDateEvent(new Date());
        billet.setCategory("VIP");
        billet.setPrix(100);
        billet.setDateAchat(new Date());

        when(billetRepository.findAll()).thenReturn(Arrays.asList(billet));

        List<BilletDTO> result = billetsService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Football", result.get(0).getSport());
    }

    @Test
    void testFindById() {
        Billet billet = new Billet();
        billet.setId(1L);

        when(billetRepository.findById(1L)).thenReturn(Optional.of(billet));

        Billet result = billetsService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(billetRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            billetsService.findById(1L);
        });

        String expectedMessage = "Billet not found: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSave() {
        Billet billet = new Billet();
        billet.setId(1L);

        when(billetRepository.save(any(Billet.class))).thenReturn(billet);

        Billet result = billetsService.save(1L, billet);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteById_Success() {
        when(billetRepository.existsById(1L)).thenReturn(true);

        ResponseMessage result = billetsService.deleteById(1L);

        verify(billetRepository, times(1)).deleteById(1L);
        assertEquals("Billet supprimé avec succès", result.getMessage());
    }

    @Test
    void testDeleteById_NotFound() {
        when(billetRepository.existsById(1L)).thenReturn(false);

        ResponseMessage result = billetsService.deleteById(1L);

        verify(billetRepository, times(0)).deleteById(1L);
        assertEquals("Billet non trouvé avec l'ID : 1", result.getMessage());
    }
}
