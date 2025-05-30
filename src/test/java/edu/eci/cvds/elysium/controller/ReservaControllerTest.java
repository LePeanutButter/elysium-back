package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ReservaControllerTest {

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    private List<Reserva> reservas;
    private Reserva reserva;
    private ReservaDTO reservaDTO;
    private LocalDate testDate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Initialize test data
        reservas = new ArrayList<>();
        reserva = new Reserva();
        reserva.setIdReserva("reserva1");
        reserva.setEstado(EstadoReserva.ACTIVA);
        reservas.add(reserva);
        
        testDate = LocalDate.now();
    
    }

    @Test
    void testGetReservasWithNoFilters() {
        // Arrange
        when(reservaService.consultarReservas()).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(null, null, null, null, null, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservas();
    }

    @Test
    void testGetReservasByIdSalon() {
        // Arrange
        String idSalon = "S101";
        when(reservaService.consultarReservasPorSalon(idSalon)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(idSalon, null, null, null, null, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorSalon(idSalon);
    }

    @Test
    void testGetReservasByFecha() {
        // Arrange
        when(reservaService.consultarReservasPorFecha(testDate)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(null, testDate, null, null, null, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorFecha(testDate);
    }

    @Test
    void testGetReservasByHora() {
        // Arrange
        Double hora = 9.0;
        when(reservaService.consultarReservasPorHora(hora)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(null, null, hora, null, null, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorHora(hora);
    }

    @Test
    void testGetReservasByDiaSemana() {
        // Arrange
        DiaSemana diaSemana = DiaSemana.LUNES;
        when(reservaService.consultarReservasPorDiaSemana(diaSemana)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(null, null, null, diaSemana, null, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorDiaSemana(diaSemana);
    }

    @Test
    void testGetReservasByEstado() {
        // Arrange
        EstadoReserva estado = EstadoReserva.ACTIVA;
        when(reservaService.consultarReservasPorEstado(estado)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(null, null, null, null, estado, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorEstado(estado);
    }

    @Test
    void testGetReservasByDuracionBloque() {
        // Arrange
        Boolean duracionBloque = true;
        when(reservaService.consultarReservasPorDuracionBloque(duracionBloque)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(null, null, null, null, null, duracionBloque);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorDuracionBloque(duracionBloque);
    }

    @Test
    void testGetReservasByIdSalonAndEstado() {
        // Arrange
        String idSalon = "S101";
        EstadoReserva estado = EstadoReserva.ACTIVA;
        when(reservaService.consultarReservasPorSalonAndEstado(idSalon, estado)).thenReturn(reservas);
        
        // Act
        ResponseEntity<List<Reserva>> response = reservaController.getReservas(idSalon, null, null, null, estado, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservas, response.getBody());
        verify(reservaService, times(1)).consultarReservasPorSalonAndEstado(idSalon, estado);
    }

    @Test
    void testConsultarReserva() {
        // Arrange
        String idReserva = "reserva1";
        when(reservaService.consultarReserva(idReserva)).thenReturn(reserva);
        
        // Act
        Reserva result = reservaController.consultarReserva(idReserva);
        
        // Assert
        assertNotNull(result);
        assertEquals(reserva, result);
        verify(reservaService, times(1)).consultarReserva(idReserva);
    }


    @Test
    void testActualizarReserva() {
        // Arrange
        String idReserva = "reserva1";
        doNothing().when(reservaService).actualizarReserva(eq(idReserva), any(ReservaDTO.class));
        
        // Act
        ResponseEntity<Void> response = reservaController.actualizarReserva(idReserva, reservaDTO);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(reservaService, times(1)).actualizarReserva(idReserva, reservaDTO);
    }

    @Test
    void testDeshabilitarReserva() {
        // Arrange
        String idReserva = "reserva1";
        doNothing().when(reservaService).deshabilitarReserva(idReserva);
        
        // Act
        ResponseEntity<String> response = reservaController.deshabilitarReserva(idReserva);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reserva deshabilitada", response.getBody());
        verify(reservaService, times(1)).deshabilitarReserva(idReserva);
    }

    @Test
    void testCrearReserva() {
        // Arrange
        ReservaDTO mockReservaDTO = mock(ReservaDTO.class);
        LocalDate fechaReserva = LocalDate.now();
        Double hora = 10.0;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Clase de programación";
        String materia = "CVDS";
        String idSalon = "S101";
        boolean duracionBloque = true;
        int prioridad = 1;
        int idUsuario = 123;
        
        // Configure mock to return values when getters are called
        when(mockReservaDTO.getFechaReserva()).thenReturn(fechaReserva);
        when(mockReservaDTO.getHora()).thenReturn(hora);
        when(mockReservaDTO.getDiaSemana()).thenReturn(diaSemana);
        when(mockReservaDTO.getProposito()).thenReturn(proposito);
        when(mockReservaDTO.getMateria()).thenReturn(materia);
        when(mockReservaDTO.getIdSalon()).thenReturn(idSalon);
        when(mockReservaDTO.isDuracionBloque()).thenReturn(duracionBloque);
        when(mockReservaDTO.getPrioridad()).thenReturn(prioridad);
        when(mockReservaDTO.getIdUsuario()).thenReturn(idUsuario);
        
        doNothing().when(reservaService).crearReserva(
            fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idUsuario
        );
        
        // Act
        ResponseEntity<String> response = reservaController.crearReserva(mockReservaDTO);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reserva creada", response.getBody());
        verify(reservaService, times(1)).crearReserva(
            fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idUsuario
        );
    }
}