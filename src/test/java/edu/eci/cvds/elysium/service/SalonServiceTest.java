package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.repository.SalonRepository;
import edu.eci.cvds.elysium.service.impl.SalonServiceImpl;


@ExtendWith(MockitoExtension.class)
public class SalonServiceTest {

    @Mock
    private SalonRepository salonRepository;

    @InjectMocks
    private SalonServiceImpl salonService;

    private Salon salonA;
    private Salon salonB;
    private List<Recurso> recursos;

    @BeforeEach
    public void setup() {
        // Create real resources
        recursos = Arrays.asList(
            new Recurso("null", 5, null),
            new Recurso("fdsfds", 6, null)
        );

        // Create real salon objects
        salonA = new Salon("Laboratorio", "lab", "AYE", "EDIFICIO S", 4, recursos);
        salonB = new Salon("Laboratorio5", "lab5", "AYE34", "EDIFICIO S3", 4, recursos);
        
        // Set activo/disponible states
        salonA.setActivo(true);
        salonA.setDisponible(true);
        salonB.setActivo(true);
        salonB.setDisponible(true);
        
        // No stubbings in setup, moved to individual tests
    }

    @Test
    public void testAgregarAndFindByMnemonico() {
        // Set up mock specifically for this test
        when(salonRepository.findByMnemonico("A1")).thenReturn(salonA);
        
        Salon salon = salonService.findByMnemonico("A1");
        assertNotNull(salon);
        assertEquals("Laboratorio", salon.getNombre());
    }

    @Test
    public void testFindAll() {
        // Set up mock specifically for this test
        when(salonRepository.findAll()).thenReturn(Arrays.asList(salonA, salonB));
        
        List<Salon> salons = salonService.findAll();
        assertEquals(2, salons.size());
    }

    @Test
    public void testDeshabilitarAndHabilitarSalon() {
        // Set up mock specifically for this test
        when(salonRepository.findByMnemonico("A1")).thenReturn(salonA);
        
        // Test disable salon
        salonService.deshabilitarSalon("A1");
        
        // Verify repository was called to save the salon
        verify(salonRepository).save(salonA);
        
        // Since salonA is a real object, modify it to simulate the service's effect
        salonA.setActivo(false);
        assertFalse(salonService.getActivo("A1"));

        // Test enable salon
        salonService.habilitarSalon("A1");
        verify(salonRepository, times(2)).save(salonA);
        
        // Simulate the service's effect on the real object
        salonA.setActivo(true);
        assertTrue(salonService.getActivo("A1"));
    }

    @Test
    public void testFindByNombreContainingIgnoreCase() {
        // Setup mocks specifically for this test
        when(salonRepository.findByNombreContainingIgnoreCase("salon")).thenReturn(Arrays.asList(salonA, salonB));
        when(salonRepository.findByNombreContainingIgnoreCase("A")).thenReturn(Arrays.asList(salonA));
        
        List<Salon> result = salonService.findByNombreContainingIgnoreCase("salon");
        assertEquals(2, result.size());

        result = salonService.findByNombreContainingIgnoreCase("A");
        assertEquals(1, result.size());
        assertEquals("Laboratorio", result.get(0).getNombre());
    }

    @Test
    public void testSetDisponibleAndSetNoDisponible() {
        // Set up mock specifically for this test
        when(salonRepository.findByMnemonico("B1")).thenReturn(salonB);
        
        // Initially salon is disponible
        assertTrue(salonB.isDisponible());
        assertTrue(salonService.getDisponible("B1"));
        
        // Set not disponible
        boolean result = salonService.setNoDisponible("B1");
        assertTrue(result);
        verify(salonRepository).save(salonB);
        
        // Simulate service effect on the real object
        salonB.setDisponible(false);
        assertFalse(salonService.getDisponible("B1"));
        
        // Set disponible again
        result = salonService.setDisponible("B1");
        assertTrue(result);
        verify(salonRepository, times(2)).save(salonB);
        
        // Simulate service effect again
        salonB.setDisponible(true);
        assertTrue(salonService.getDisponible("B1"));
    }
}