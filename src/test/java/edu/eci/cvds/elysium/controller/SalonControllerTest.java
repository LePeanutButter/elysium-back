package edu.eci.cvds.elysium.controller;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import edu.eci.cvds.elysium.dto.salon.ActualizarSalonDTO;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.service.SalonService;





@ExtendWith(MockitoExtension.class)
public class SalonControllerTest {

    @Mock
    private SalonService salonService;

    @InjectMocks
    private SalonController salonController;

    private Salon testSalon;

    @BeforeEach
    public void setUp() {
        testSalon = new Salon("Nuevo", "N1", "UbicacionNueva", 200);
    }

    @Test
    public void testGetSalonesBothTrue() {
        when(salonService.findByActivoTrueAndDisponibleTrue())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(true, true, null, null, null, null);
        verify(salonService).findByActivoTrueAndDisponibleTrue();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetSalonesActivoTrueDisponibleFalse() {
        when(salonService.findByActivoTrueAndDisponibleFalse())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(true, false, null, null, null, null);
        verify(salonService).findByActivoTrueAndDisponibleFalse();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesActivoFalseDisponibleTrue() {
        when(salonService.findByActivoFalseAndDisponibleTrue())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(false, true, null, null, null, null);
        verify(salonService).findByActivoFalseAndDisponibleTrue();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesActivoFalseDisponibleFalse() {
        when(salonService.findByActivoFalseAndDisponibleFalse())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(false, false, null, null, null, null);
        verify(salonService).findByActivoFalseAndDisponibleFalse();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesOnlyActivo() {
        when(salonService.findByActivoTrue())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(true, null, null, null, null, null);
        verify(salonService).findByActivoTrue();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesOnlyDisponible() {
        when(salonService.findByDisponibleFalse())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, false, null, null, null, null);
        verify(salonService).findByDisponibleFalse();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesNombreAndUbicacion() {
        when(salonService.findByNombreAndUbicacionContainingIgnoreCase("Salon", "Ubicacion"))
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, null, "Salon", "Ubicacion", null, null);
        verify(salonService).findByNombreAndUbicacionContainingIgnoreCase("Salon", "Ubicacion");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesOnlyNombre() {
        when(salonService.findByNombreContainingIgnoreCase("Salon"))
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, null, "Salon", null, null, null);
        verify(salonService).findByNombreContainingIgnoreCase("Salon");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesOnlyUbicacion() {
        when(salonService.findByUbicacionContainingIgnoreCase("Ubicacion"))
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, null, null, "Ubicacion", null, null);
        verify(salonService).findByUbicacionContainingIgnoreCase("Ubicacion");
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesCapacidadMin() {
        when(salonService.findByCapacidadGreaterThanEqual(50))
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, null, null, null, 50, null);
        verify(salonService).findByCapacidadGreaterThanEqual(50);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesCapacidadMax() {
        when(salonService.findByCapacidadLessThanEqual(150))
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, null, null, null, null, 150);
        verify(salonService).findByCapacidadLessThanEqual(150);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonesNoFilter() {
        when(salonService.findAll())
                .thenReturn(Collections.singletonList(testSalon));
        ResponseEntity<List<Salon>> response = salonController.getSalones(null, null, null, null, null, null);
        verify(salonService).findAll();
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetSalonByMnemonicoFound() {
        when(salonService.findByMnemonico("S1")).thenReturn(testSalon);
        ResponseEntity<Salon> response = salonController.getSalonByMnemonico("S1");
        verify(salonService).findByMnemonico("S1");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetSalonByMnemonicoNotFound() {
        when(salonService.findByMnemonico("S2")).thenReturn(null);
        ResponseEntity<Salon> response = salonController.getSalonByMnemonico("S2");
        verify(salonService).findByMnemonico("S2");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void testAgregarSalon() {
        

        Salon newSalon = new Salon("Nuevo", "N1", "UbicacionNueva", 200);
        
        ResponseEntity<Void> response = salonController.agregarSalon(newSalon);
        verify(salonService).agregarSalon("Nuevo", "N1", "UbicacionNueva", 200);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testDeshabilitarSalon() {
        ResponseEntity<Void> response = salonController.deshabilitarSalon("S1");
        verify(salonService).deshabilitarSalon("S1");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testHabilitarSalon() {
        ResponseEntity<Void> response = salonController.habilitarSalon("S1");
        verify(salonService).habilitarSalon("S1");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testGetDisponible() {
        when(salonService.getDisponible("S1")).thenReturn(true);
        ResponseEntity<Boolean> response = salonController.getDisponible("S1");
        verify(salonService).getDisponible("S1");
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody());
    }

    @Test
    public void testSetDisponibleSuccess() {
        when(salonService.setDisponible("S1")).thenReturn(true);
        ResponseEntity<Void> response = salonController.setDisponible("S1");
        verify(salonService).setDisponible("S1");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testSetDisponibleNotFound() {
        when(salonService.setDisponible("S2")).thenReturn(false);
        ResponseEntity<Void> response = salonController.setDisponible("S2");
        verify(salonService).setDisponible("S2");
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSetNoDisponibleSuccess() {
        when(salonService.setNoDisponible("S1")).thenReturn(true);
        ResponseEntity<Void> response = salonController.setNoDisponible("S1");
        verify(salonService).setNoDisponible("S1");
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testSetNoDisponibleNotFound() {
        when(salonService.setNoDisponible("S2")).thenReturn(false);
        ResponseEntity<Void> response = salonController.setNoDisponible("S2");
        verify(salonService).setNoDisponible("S2");
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testActualizarSalon() {
        ActualizarSalonDTO dto = new ActualizarSalonDTO();
        // Se asume que el dto se procesa correctamente.
        ResponseEntity<Void> response = salonController.actualizarSalon("S1", dto);
        verify(salonService).actualizarSalon("S1", dto);
        assertEquals(204, response.getStatusCodeValue());
    }
}
