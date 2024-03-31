package com.studi.sapioce.JO24BE.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.studi.sapioce.JO24BE.pojo.BilletDisponible;
import com.studi.sapioce.JO24BE.pojo.Enum.BilletStatut;
import com.studi.sapioce.JO24BE.repository.BilletsDisponibleRepository;

@SpringBootTest
public class BilletsDisponibleServiceTest {

	@Mock
    private BilletsDisponibleRepository billetDisponibleRepository;

    @InjectMocks
    private BilletsDisponibleService billetsDisponibleService;
    
    /**
     * test qui permet de tester la récupération d'un billet disponible à la vente
     */
    @Test
    public void testGetBilletDisponible() {
        // Setup
        BilletDisponible billet = new BilletDisponible();
        billet.setId(1L);
        when(billetDisponibleRepository.findById(1L)).thenReturn(Optional.of(billet));
        
        // Execution
        BilletDisponible result = billetsDisponibleService.getBilletDisponible(1L);
        
        // Verification
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(billetDisponibleRepository).findById(1L);
    }
    
    /**
     * Test qui permet de récuperer la liste complète des billets disponibles à la vente
     */
    @Test
    public void testGetBilletsDisponibles() {
        // Setup
        List<BilletDisponible> billets = new ArrayList<>();
        billets.add(new BilletDisponible());
        when(billetDisponibleRepository.findBySportAndStatutAndCategoryAndDateEvent(anyString(), any(), anyString(), any())).thenReturn(billets);
        
        // Execution
        List<BilletDisponible> result = billetsDisponibleService.getBilletsDisponibles("Football", BilletStatut.IN_SOLD, "VIP", LocalDate.now());
        
        // Verification
        assertFalse(result.isEmpty());
        verify(billetDisponibleRepository).findBySportAndStatutAndCategoryAndDateEvent(anyString(), any(), anyString(), any());
    }

    /**
     * Test qui permet de tester le service de creation de billet disponibles à la vente
    */
    @Test
    public void testCreateBilletDisponible() {
        // Setup
        BilletDisponible billet = new BilletDisponible();
        when(billetDisponibleRepository.save(any(BilletDisponible.class))).thenReturn(billet);
        
        // Execution
        BilletDisponible result = billetsDisponibleService.createBilletDisponible(billet);
        
        // Verification
        assertNotNull(result);
        verify(billetDisponibleRepository).save(any(BilletDisponible.class));
    }
    
    /**
     * Test qui permet de tester la maj des billet disponibles
     */
    @Test
    public void testUpdateBilletDisponible() {
        // Setup
        BilletDisponible existingBillet = new BilletDisponible();
        existingBillet.setId(1L);
        existingBillet.setSport("Basketball");
        
        BilletDisponible updatedBillet = new BilletDisponible();
        updatedBillet.setSport("Football");
        
        when(billetDisponibleRepository.findById(1L)).thenReturn(Optional.of(existingBillet));
        when(billetDisponibleRepository.save(any(BilletDisponible.class))).thenReturn(updatedBillet);
        
        // Execution
        BilletDisponible result = billetsDisponibleService.updateBilletDisponible(1L, updatedBillet);
        
        // Verification
        assertNotNull(result);
        assertEquals("Football", result.getSport());
        verify(billetDisponibleRepository).save(any(BilletDisponible.class));
    }

    /**
     * Test qui permet de tester le service de suppression (Mise à l'etat de desactiver)
     */
    @Test
    public void testDeleteBilletDisponible() {
        // Setup
        BilletDisponible existingBillet = new BilletDisponible();
        existingBillet.setId(1L);
        existingBillet.setStatut(BilletStatut.IN_SOLD);
        
        when(billetDisponibleRepository.findById(1L)).thenReturn(Optional.of(existingBillet));
        doAnswer(invocation -> {
            BilletDisponible billet = invocation.getArgument(0);
            assertEquals(BilletStatut.DESACTIVED, billet.getStatut());
            return null;
        }).when(billetDisponibleRepository).save(any(BilletDisponible.class));
        
        // Execution
        billetsDisponibleService.deleteBilletDisponible(1L);
        
        // Verification
        verify(billetDisponibleRepository).save(any(BilletDisponible.class));
    }

}
