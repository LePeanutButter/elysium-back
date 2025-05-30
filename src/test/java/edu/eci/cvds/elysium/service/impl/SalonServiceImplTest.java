package edu.eci.cvds.elysium.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.repository.SalonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class SalonServiceImplTest {

    @Mock
    private SalonRepository salonRepository;

    @InjectMocks
    private SalonServiceImpl salonService;

    private Salon salon;
    private List<Recurso> recursos;

    @BeforeEach
    public void setUp() {
        recursos = Arrays.asList(new Recurso("dsdg", 56, null), new Recurso("ds312g", 56, null));
        salon = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
    }
    
    @Test
    public void testFindByMnemonico() {
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(salon);
        Salon found = salonService.findByMnemonico("SALON1");
        assertNotNull(found);
        assertEquals("SALON1", found.getMnemonico());
        verify(salonRepository).findByMnemonico("SALON1");
    }
    
    @Test
    public void testFindAll() {
        List<Salon> salons = Arrays.asList(salon, new Salon("Salon Dos", "SALON2", "Desc2", "Ub2", 30, recursos));
        when(salonRepository.findAll()).thenReturn(salons);
        List<Salon> result = salonService.findAll();
        assertEquals(2, result.size());
        verify(salonRepository).findAll();
    }
    
    @Test
    public void testFindByActivoTrue() {
        List<Salon> activeSalons = Collections.singletonList(salon);
        when(salonRepository.findByActivoTrue()).thenReturn(activeSalons);
        List<Salon> result = salonService.findByActivoTrue();
        assertEquals(1, result.size());
        verify(salonRepository).findByActivoTrue();
    }
    
    @Test
    public void testAgregarSalonSuccess() {
        // When salon doesn't exist
        when(salonRepository.existsByMnemonico("SALON1")).thenReturn(false);
        // Call method
        salonService.agregarSalon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        // Capture and verify that a new salon is saved.
        ArgumentCaptor<Salon> captor = ArgumentCaptor.forClass(Salon.class);
        verify(salonRepository).save(captor.capture());
        Salon saved = captor.getValue();
        assertEquals("SALON1", saved.getMnemonico());
        assertEquals("Salon Uno", saved.getNombre());
    }
    
    @Test
    public void testAgregarSalonAlreadyExists() {
        // When salon already exists
        when(salonRepository.existsByMnemonico("SALON1")).thenReturn(true);
        // Call method. The method catches the exception internally.
        salonService.agregarSalon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        // Verify that save is not called.
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testAgregarSalonInvalidCapacity() {
        // Capacity <= 0
        when(salonRepository.existsByMnemonico("SALON1")).thenReturn(false);
        salonService.agregarSalon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 0, recursos);
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testAgregarSalonEmptyResources() {
        // Empty recursos list.
        when(salonRepository.existsByMnemonico("SALON1")).thenReturn(false);
        salonService.agregarSalon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, Collections.emptyList());
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testDeshabilitarSalon() {
        // Initially active salon.
        Salon active = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        active.setActivo(true);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(active);
        salonService.deshabilitarSalon("SALON1");
        // After disabling, activo should be false.
        assertFalse(active.isActivo());
        verify(salonRepository).save(active);
    }
    
    @Test
    public void testHabilitarSalon() {
        // Initially inactive salon.
        Salon inactive = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        inactive.setActivo(false);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(inactive);
        salonService.habilitarSalon("SALON1");
        assertTrue(inactive.isActivo());
        verify(salonRepository).save(inactive);
    }
    
    @Test
    public void testGetActivo() {
        Salon active = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        active.setActivo(true);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(active);
        assertTrue(salonService.getActivo("SALON1"));
        active.setActivo(false);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(active);
        assertFalse(salonService.getActivo("SALON1"));
    }
    
    @Test
    public void testActualizarSalon() {
        Salon existing = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(existing);
        SalonDTO dto = new SalonDTO();
        dto.setName("Salon Actualizado");
        dto.setDescription("Nueva Descripcion");
        dto.setLocation("Nueva Ubicacion");
        dto.setCapacity(60);
        dto.setActivo(false);
        dto.setResources(Arrays.asList(new Recurso("dsdg", 56, null)));
        
        salonService.actualizarSalon("SALON1", dto);
        assertEquals("Salon Actualizado", existing.getNombre());
        assertEquals("Nueva Descripcion", existing.getDescripcion());
        assertEquals("Nueva Ubicacion", existing.getUbicacion());
        assertEquals(60, existing.getCapacidad());
        assertFalse(existing.isActivo());
        assertEquals(1, existing.getRecursos().size());
        verify(salonRepository).save(existing);
    }
    
    @Test
    public void testGetDisponible() {
        Salon available = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        available.setDisponible(true);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(available);
        assertTrue(salonService.getDisponible("SALON1"));
        available.setDisponible(false);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(available);
        assertFalse(salonService.getDisponible("SALON1"));
    }
    
    @Test
    public void testSetDisponible() {
        Salon salonDisp = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        salonDisp.setDisponible(false);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(salonDisp);
        boolean result = salonService.setDisponible("SALON1");
        assertTrue(result);
        assertTrue(salonDisp.isDisponible());
        verify(salonRepository).save(salonDisp);
    }
    
    @Test
    public void testSetNoDisponible() {
        Salon salonNoDisp = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        salonNoDisp.setDisponible(true);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(salonNoDisp);
        boolean result = salonService.setNoDisponible("SALON1");
        assertTrue(result);
        assertFalse(salonNoDisp.isDisponible());
        verify(salonRepository).save(salonNoDisp);
    }
    
    @Test
    public void testFindByActivoFalse() {
        List<Salon> inactiveSalons = Collections.singletonList(salon);
        when(salonRepository.findByActivoFalse()).thenReturn(inactiveSalons);
        List<Salon> result = salonService.findByActivoFalse();
        assertEquals(1, result.size());
        verify(salonRepository).findByActivoFalse();
    }
    
    @Test
    public void testFindByDisponibleTrue() {
        List<Salon> availableSalons = Collections.singletonList(salon);
        when(salonRepository.findByDisponibleTrue()).thenReturn(availableSalons);
        List<Salon> result = salonService.findByDisponibleTrue();
        assertEquals(1, result.size());
        verify(salonRepository).findByDisponibleTrue();
    }
    
    @Test
    public void testFindByDisponibleFalse() {
        List<Salon> unavailableSalons = Collections.singletonList(salon);
        when(salonRepository.findByDisponibleFalse()).thenReturn(unavailableSalons);
        List<Salon> result = salonService.findByDisponibleFalse();
        assertEquals(1, result.size());
        verify(salonRepository).findByDisponibleFalse();
    }
    
    @Test
    public void testFindByActivoTrueAndDisponibleTrue() {
        List<Salon> activeAvailableSalons = Collections.singletonList(salon);
        when(salonRepository.findByActivoTrueAndDisponibleTrue()).thenReturn(activeAvailableSalons);
        List<Salon> result = salonService.findByActivoTrueAndDisponibleTrue();
        assertEquals(1, result.size());
        verify(salonRepository).findByActivoTrueAndDisponibleTrue();
    }
    
    @Test
    public void testFindByActivoTrueAndDisponibleFalse() {
        List<Salon> activeUnavailableSalons = Collections.singletonList(salon);
        when(salonRepository.findByActivoTrueAndDisponibleFalse()).thenReturn(activeUnavailableSalons);
        List<Salon> result = salonService.findByActivoTrueAndDisponibleFalse();
        assertEquals(1, result.size());
        verify(salonRepository).findByActivoTrueAndDisponibleFalse();
    }
    
    @Test
    public void testFindByActivoFalseAndDisponibleTrue() {
        List<Salon> inactiveAvailableSalons = Collections.singletonList(salon);
        when(salonRepository.findByActivoFalseAndDisponibleTrue()).thenReturn(inactiveAvailableSalons);
        List<Salon> result = salonService.findByActivoFalseAndDisponibleTrue();
        assertEquals(1, result.size());
        verify(salonRepository).findByActivoFalseAndDisponibleTrue();
    }
    
    @Test
    public void testFindByActivoFalseAndDisponibleFalse() {
        List<Salon> inactiveUnavailableSalons = Collections.singletonList(salon);
        when(salonRepository.findByActivoFalseAndDisponibleFalse()).thenReturn(inactiveUnavailableSalons);
        List<Salon> result = salonService.findByActivoFalseAndDisponibleFalse();
        assertEquals(1, result.size());
        verify(salonRepository).findByActivoFalseAndDisponibleFalse();
    }
    
    @Test
    public void testFindByNombreContainingIgnoreCase() {
        List<Salon> salonsWithName = Collections.singletonList(salon);
        when(salonRepository.findByNombreContainingIgnoreCase("Salon")).thenReturn(salonsWithName);
        List<Salon> result = salonService.findByNombreContainingIgnoreCase("Salon");
        assertEquals(1, result.size());
        verify(salonRepository).findByNombreContainingIgnoreCase("Salon");
    }
    
    @Test
    public void testFindByUbicacionContainingIgnoreCase() {
        List<Salon> salonsWithLocation = Collections.singletonList(salon);
        when(salonRepository.findByUbicacionContainingIgnoreCase("Ubicacion")).thenReturn(salonsWithLocation);
        List<Salon> result = salonService.findByUbicacionContainingIgnoreCase("Ubicacion");
        assertEquals(1, result.size());
        verify(salonRepository).findByUbicacionContainingIgnoreCase("Ubicacion");
    }
    
    @Test
    public void testFindByCapacidadGreaterThanEqual() {
        List<Salon> salonsWithCapacity = Collections.singletonList(salon);
        when(salonRepository.findByCapacidadGreaterThanEqual(30)).thenReturn(salonsWithCapacity);
        List<Salon> result = salonService.findByCapacidadGreaterThanEqual(30);
        assertEquals(1, result.size());
        verify(salonRepository).findByCapacidadGreaterThanEqual(30);
    }
    
    @Test
    public void testFindByCapacidadLessThanEqual() {
        List<Salon> salonsWithCapacity = Collections.singletonList(salon);
        when(salonRepository.findByCapacidadLessThanEqual(60)).thenReturn(salonsWithCapacity);
        List<Salon> result = salonService.findByCapacidadLessThanEqual(60);
        assertEquals(1, result.size());
        verify(salonRepository).findByCapacidadLessThanEqual(60);
    }
    
    @Test
    public void testFindByNombreAndUbicacionContainingIgnoreCase() {
        List<Salon> filteredSalons = Collections.singletonList(salon);
        when(salonRepository.findByNombreAndUbicacionContainingIgnoreCase("Salon Uno", "Ubicacion"))
            .thenReturn(filteredSalons);
        List<Salon> result = salonService.findByNombreAndUbicacionContainingIgnoreCase("Salon Uno", "Ubicacion");
        assertEquals(1, result.size());
        verify(salonRepository).findByNombreAndUbicacionContainingIgnoreCase("Salon Uno", "Ubicacion");
    }
    
    @Test
    public void testSetDisponibleNonexistentSalon() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        boolean result = salonService.setDisponible("NONEXISTENT");
        assertFalse(result);
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testSetNoDisponibleNonexistentSalon() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        boolean result = salonService.setNoDisponible("NONEXISTENT");
        assertFalse(result);
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testActualizarSalonWithPartialData() {
        Salon existing = new Salon("Salon Uno", "SALON1", "Descripcion", "Ubicacion", 50, recursos);
        when(salonRepository.findByMnemonico("SALON1")).thenReturn(existing);
        
        // Create DTO with only some fields populated
        SalonDTO dto = new SalonDTO();
        dto.setName("Salon Actualizado");
        // Other fields are null
        
        salonService.actualizarSalon("SALON1", dto);
        assertEquals("Salon Actualizado", existing.getNombre());
        // Verify other fields remain unchanged
        assertEquals("Descripcion", existing.getDescripcion());
        assertEquals("Ubicacion", existing.getUbicacion());
        assertEquals(50, existing.getCapacidad());
        verify(salonRepository).save(existing);
    }
    
    @Test
    public void testActualizarSalonNonexistent() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        SalonDTO dto = new SalonDTO();
        dto.setName("Nuevo Nombre");
        
        salonService.actualizarSalon("NONEXISTENT", dto);
        // Verify that save was never called since the salon doesn't exist
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testDeshabilitarSalonNonexistent() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        salonService.deshabilitarSalon("NONEXISTENT");
        // Verify that save was never called since the salon doesn't exist
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testHabilitarSalonNonexistent() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        salonService.habilitarSalon("NONEXISTENT");
        // Verify that save was never called since the salon doesn't exist
        verify(salonRepository, never()).save(any(Salon.class));
    }
    
    @Test
    public void testGetActivoNonexistent() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        boolean result = salonService.getActivo("NONEXISTENT");
        assertFalse(result);
    }
    
    @Test
    public void testGetDisponibleNonexistent() {
        when(salonRepository.findByMnemonico("NONEXISTENT")).thenReturn(null);
        boolean result = salonService.getDisponible("NONEXISTENT");
        assertFalse(result);
    }
}