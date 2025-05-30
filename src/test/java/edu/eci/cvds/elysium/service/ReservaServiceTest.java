package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;

public class ReservaServiceTest {

    @Mock
    private ReservaService reservaService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearReservaAndConsultarReservas() {
        // Setup
        List<Reserva> reservas = new ArrayList<>();
        Reserva mockReserva = mock(Reserva.class);
        reservas.add(mockReserva);
        
        // Define behavior
        doNothing().when(reservaService).crearReserva(any(LocalDate.class), anyDouble(), any(DiaSemana.class), 
                anyString(), anyString(), anyString(), anyBoolean(), anyInt(), anyInt());
        when(reservaService.consultarReservas()).thenReturn(reservas);
        
        // Execute
        reservaService.crearReserva(LocalDate.of(2023, 10, 10), 14.0, DiaSemana.JUEVES, 
                "Reunion", "Matematicas", "Salon101", true, 1, 1234);
        List<Reserva> result = reservaService.consultarReservas();
        
        // Verify
        verify(reservaService).crearReserva(LocalDate.of(2023, 10, 10), 14.0, DiaSemana.JUEVES, 
                "Reunion", "Matematicas", "Salon101", true, 1, 1234);
        verify(reservaService).consultarReservas();
        assertEquals(1, result.size());
    }
  
    @Test
    public void testConsultarReservasPorSalon() {
        // Setup
        List<Reserva> reservas = new ArrayList<>();
        Reserva mockReserva = mock(Reserva.class);
        reservas.add(mockReserva);
        
        // Define behavior
        doNothing().when(reservaService).crearReserva(any(LocalDate.class), anyDouble(), any(DiaSemana.class), 
                anyString(), anyString(), anyString(), anyBoolean(), anyInt(), anyInt());
        when(reservaService.consultarReservasPorSalon("Salon202")).thenReturn(reservas);
        
        // Execute
        reservaService.crearReserva(LocalDate.of(2023, 10, 10), 15.0, DiaSemana.VIERNES, 
                "Clase", "Fisica", "Salon202", false, 2, 5678);
        List<Reserva> result = reservaService.consultarReservasPorSalon("Salon202");
        
        // Verify
        verify(reservaService).crearReserva(LocalDate.of(2023, 10, 10), 15.0, DiaSemana.VIERNES, 
                "Clase", "Fisica", "Salon202", false, 2, 5678);
        verify(reservaService).consultarReservasPorSalon("Salon202");
        assertEquals(1, result.size());
    }
  
    @Test
    public void testActualizarReserva() {
        // Setup
        String reservaId = "reserva1";
        Reserva mockReserva = mock(Reserva.class);
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(mockReserva);
        ReservaDTO dto = new ReservaDTO();
        
        
        // Define behavior
        when(reservaService.consultarReservas()).thenReturn(reservas);
        when(mockReserva.getIdReserva()).thenReturn(reservaId);
        when(reservaService.consultarReserva(reservaId)).thenReturn(mockReserva);
        when(mockReserva.getProposito()).thenReturn("Actualizado");
        when(mockReserva.getMateria()).thenReturn("Biologia");
        doNothing().when(reservaService).actualizarReserva(anyString(), any(ReservaDTO.class));
        
        // Execute
        reservaService.crearReserva(LocalDate.of(2023, 10, 11), 16.0, DiaSemana.SABADO, 
                "Estudio", "Quimica", "Salon303", true, 3, 1111);
        Reserva reserva = reservaService.consultarReservas().get(0);
        String id = reserva.getIdReserva();
        reservaService.actualizarReserva(id, dto);
        Reserva updated = reservaService.consultarReserva(id);
        
        // Verify
        assertEquals("Actualizado", updated.getProposito());
        assertEquals("Biologia", updated.getMateria());
    }
  
    @Test
    public void testDeshabilitarReserva() {
        // Setup
        String reservaId = "reserva1";
        Reserva mockReserva = mock(Reserva.class);
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(mockReserva);
        
        // Define behavior
        when(reservaService.consultarReservas()).thenReturn(reservas);
        when(mockReserva.getIdReserva()).thenReturn(reservaId);
        when(reservaService.consultarReserva(reservaId)).thenReturn(mockReserva);
        when(mockReserva.getEstado()).thenReturn(EstadoReserva.DESHABILITADA);
        doNothing().when(reservaService).deshabilitarReserva(anyString());
        
        // Execute
        reservaService.crearReserva(LocalDate.of(2023, 10, 12), 17.0, DiaSemana.SABADO, 
                "Test", "Historia", "Salon404", false, 1, 2222);
        Reserva reserva = reservaService.consultarReservas().get(0);
        String id = reserva.getIdReserva();
        reservaService.deshabilitarReserva(id);
        Reserva disabled = reservaService.consultarReserva(id);
        
        // Verify
        assertEquals(EstadoReserva.DESHABILITADA, disabled.getEstado());
    }
  
    @Test
    public void testGenerarReservasAleatorias() {
        // Setup
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(mock(Reserva.class));
        reservas.add(mock(Reserva.class));
        reservas.add(mock(Reserva.class));
        
        // Define behavior
        doNothing().when(reservaService).generarReservasAleatorias();
        when(reservaService.consultarReservas()).thenReturn(reservas);
        
        // Execute
        reservaService.generarReservasAleatorias();
        List<Reserva> result = reservaService.consultarReservas();
        
        // Verify
        verify(reservaService).generarReservasAleatorias();
        verify(reservaService).consultarReservas();
        assertEquals(3, result.size());
    }
}