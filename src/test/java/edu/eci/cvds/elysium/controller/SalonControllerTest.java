package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.service.SalonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalonControllerTest {

    @Mock
    private SalonService salonService;

    @InjectMocks
    private SalonController salonController;

    private Salon salon1, salon2;
    private List<Salon> salonList;
    private SalonDTO salonDTO;
    private final String MNEMONICO = "S101";

    @BeforeEach
    void setUp() {
        salon1 = new Salon();
        salon1.setNombre("Sala Principal");
        salon1.setMnemonico(MNEMONICO);
        salon1.setUbicacion("Edificio A");
        salon1.setCapacidad(30);
        salon1.setActivo(true);
        salon1.setDisponible(true);

        salon2 = new Salon();
        salon2.setNombre("Sala Secundaria");
        salon2.setMnemonico("S102");
        salon2.setUbicacion("Edificio B");
        salon2.setCapacidad(20);
        salon2.setActivo(true);
        salon2.setDisponible(false);

        salonList = Arrays.asList(salon1, salon2);

        salonDTO = new SalonDTO();
        salonDTO.setName("Nueva Sala");
        salonDTO.setMnemonic("S103");
        salonDTO.setDescription("Descripción de la sala");
        salonDTO.setLocation("Edificio C");
        salonDTO.setCapacity(25);
        salonDTO.setResources(null);
    }

    @Test
    void testGetSalonesWithNoFilters() {
        when(salonService.findAll()).thenReturn(salonList);

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                null, null, null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(salonService).findAll();
    }

    @Test
    void testGetSalonesWithActivoFilter() {
        when(salonService.findByActivoTrue()).thenReturn(salonList);

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                true, null, null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(salonService).findByActivoTrue();
    }

    @Test
    void testGetSalonesWithDisponibleFilter() {
        List<Salon> disponibles = List.of(salon1);
        when(salonService.findByDisponibleTrue()).thenReturn(disponibles);

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                null, true, null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(salonService).findByDisponibleTrue();
    }

    @Test
    void testGetSalonesWithActivoAndDisponibleFilters() {
        List<Salon> activosYDisponibles = List.of(salon1);
        when(salonService.findByActivoTrueAndDisponibleTrue()).thenReturn(activosYDisponibles);

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                true, true, null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(salonService).findByActivoTrueAndDisponibleTrue();
    }

    @Test
    void testGetSalonesWithNombreFilter() {
        List<Salon> filtrados = List.of(salon1);
        when(salonService.findByNombreContainingIgnoreCase("Principal")).thenReturn(filtrados);

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                null, null, "Principal", null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(salonService).findByNombreContainingIgnoreCase("Principal");
    }

    @Test
    void testGetSalonesWithUbicacionFilter() {
        List<Salon> filtrados = List.of(salon1);
        when(salonService.findByUbicacionContainingIgnoreCase("Edificio A")).thenReturn(filtrados);

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                null, null, null, "Edificio A", null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(salonService).findByUbicacionContainingIgnoreCase("Edificio A");
    }

    @Test
    void testGetSalonesWithCapacidadMinFilter() {
        when(salonService.findByCapacidadGreaterThanEqual(25)).thenReturn(List.of(salon1));

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                null, null, null, null, 25, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(salonService).findByCapacidadGreaterThanEqual(25);
    }

    @Test
    void testGetSalonesWithCapacidadMaxFilter() {
        when(salonService.findByCapacidadLessThanEqual(25)).thenReturn(List.of(salon2));

        ResponseEntity<List<Salon>> response = salonController.getSalones(
                null, null, null, null, null, 25);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(salonService).findByCapacidadLessThanEqual(25);
    }

    @Test
    void testGetSalonByMnemonicoFound() {
        when(salonService.findByMnemonico(MNEMONICO)).thenReturn(salon1);

        ResponseEntity<Salon> response = salonController.getSalonByMnemonico(MNEMONICO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MNEMONICO, response.getBody().getMnemonico());
        verify(salonService).findByMnemonico(MNEMONICO);
    }

    @Test
    void testGetSalonByMnemonicoNotFound() {
        when(salonService.findByMnemonico("NOEXISTE")).thenReturn(null);

        ResponseEntity<Salon> response = salonController.getSalonByMnemonico("NOEXISTE");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(salonService).findByMnemonico("NOEXISTE");
    }

    @Test
    void testGetDisponible() {
        when(salonService.getDisponible(MNEMONICO)).thenReturn(true);

        ResponseEntity<Boolean> response = salonController.getDisponible(MNEMONICO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(salonService).getDisponible(MNEMONICO);
    }

    @Test
    void testAgregarSalon() {
        doNothing().when(salonService).agregarSalon(
                salonDTO.getName(),
                salonDTO.getMnemonic(),
                salonDTO.getDescription(),
                salonDTO.getLocation(),
                salonDTO.getCapacity(),
                salonDTO.getResources()
        );

        ResponseEntity<Void> response = salonController.agregarSalon(salonDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(salonService).agregarSalon(
                salonDTO.getName(),
                salonDTO.getMnemonic(),
                salonDTO.getDescription(),
                salonDTO.getLocation(),
                salonDTO.getCapacity(),
                salonDTO.getResources()
        );
    }

    @Test
    void testActualizarSalon() {
        doNothing().when(salonService).actualizarSalon(MNEMONICO, salonDTO);

        ResponseEntity<Void> response = salonController.actualizarSalon(MNEMONICO, salonDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(salonService).actualizarSalon(MNEMONICO, salonDTO);
    }

    @Test
    void testGetSalonesComprehensive() {
        // Test all combinations of activo and disponible
        when(salonService.findByActivoTrueAndDisponibleTrue()).thenReturn(List.of(salon1));
        when(salonService.findByActivoTrueAndDisponibleFalse()).thenReturn(List.of(salon2));
        when(salonService.findByActivoFalseAndDisponibleTrue()).thenReturn(new ArrayList<>());
        when(salonService.findByActivoFalseAndDisponibleFalse()).thenReturn(new ArrayList<>());
        
        // Test activo=true, disponible=true
        ResponseEntity<List<Salon>> response1 = salonController.getSalones(true, true, null, null, null, null);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(1, response1.getBody().size());
        assertEquals(MNEMONICO, response1.getBody().get(0).getMnemonico());
        
        // Test activo=true, disponible=false
        ResponseEntity<List<Salon>> response2 = salonController.getSalones(true, false, null, null, null, null);
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(1, response2.getBody().size());
        assertEquals("S102", response2.getBody().get(0).getMnemonico());
        
        // Test activo=false, disponible=true
        ResponseEntity<List<Salon>> response3 = salonController.getSalones(false, true, null, null, null, null);
        assertEquals(HttpStatus.OK, response3.getStatusCode());
        assertEquals(0, response3.getBody().size());
        
        // Test activo=false, disponible=false
        ResponseEntity<List<Salon>> response4 = salonController.getSalones(false, false, null, null, null, null);
        assertEquals(HttpStatus.OK, response4.getStatusCode());
        assertEquals(0, response4.getBody().size());
        
        // Test nombre and ubicacion combined
        when(salonService.findByNombreAndUbicacionContainingIgnoreCase("Sala Principal", "Edificio A"))
            .thenReturn(List.of(salon1));
        ResponseEntity<List<Salon>> response5 = salonController.getSalones(
            null, null, "Sala Principal", "Edificio A", null, null);
        assertEquals(HttpStatus.OK, response5.getStatusCode());
        assertEquals(1, response5.getBody().size());
        assertEquals(MNEMONICO, response5.getBody().get(0).getMnemonico());
        
        // Test capacidadMin and capacidadMax combined (not directly supported by the method)
        // This will default to capacidadMin only
        when(salonService.findByCapacidadGreaterThanEqual(20)).thenReturn(salonList);
        ResponseEntity<List<Salon>> response6 = salonController.getSalones(
            null, null, null, null, 20, 30);
        assertEquals(HttpStatus.OK, response6.getStatusCode());
        assertEquals(2, response6.getBody().size());
        
        // Verify all service method calls
        verify(salonService).findByActivoTrueAndDisponibleTrue();
        verify(salonService).findByActivoTrueAndDisponibleFalse();
        verify(salonService).findByActivoFalseAndDisponibleTrue();
        verify(salonService).findByActivoFalseAndDisponibleFalse();
        verify(salonService).findByNombreAndUbicacionContainingIgnoreCase("Sala Principal", "Edificio A");
        verify(salonService).findByCapacidadGreaterThanEqual(20);
        verify(salonService, never()).findAll(); // This should never be called with our test parameters
    }
}
