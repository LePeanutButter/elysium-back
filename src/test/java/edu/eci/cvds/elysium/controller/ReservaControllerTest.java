package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;
import edu.eci.cvds.elysium.dto.ReservaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ReservaControllerTest {

    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarReservas() {
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservas()).thenReturn(reservas);

        List<ReservaModel> result = reservaController.consultarReservas();
        assertEquals(reservas, result);
    }

    @Test
    public void testConsultarReservasPorSalon() {
        String idSalon = "1";
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorSalon(idSalon)).thenReturn(reservas);

        List<ReservaModel> result = reservaController.consultarReservasPorSalon(idSalon);
        assertEquals(reservas, result);
    }

    @Test
    public void testConsultarReservasPorFecha() {
        LocalDate fecha = LocalDate.now();
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorFecha(fecha)).thenReturn(reservas);

        List<ReservaModel> result = reservaController.consultarReservasPorFecha(fecha);
        assertEquals(reservas, result);
    }

    @Test
    public void testConsultarReservasPorDiaSemana() {
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorDiaSemana(diaSemana)).thenReturn(reservas);

        List<ReservaModel> result = reservaController.consultarReservasPorDiaSemana(diaSemana);
        assertEquals(reservas, result);
    }

    @Test
    public void testConsultarReservasPorEstado() {
        EstadoReservaModel estado = EstadoReservaModel.ACTIVA;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorEstado(estado)).thenReturn(reservas);

        List<ReservaModel> result = reservaController.consultarReservasPorEstado(estado);
        assertEquals(reservas, result);
    }

    @Test
    public void testConsultarReservasPorDuracionBloque() {
        boolean duracionBloque = true;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaService.consultarReservasPorDuracionBloque(duracionBloque)).thenReturn(reservas);

        List<ReservaModel> result = reservaController.consultarReservasPorDuracionBloque(duracionBloque);
        assertEquals(reservas, result);
    }

    @Test
    public void testConsultarReserva() {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        when(reservaService.consultarReserva(idReserva)).thenReturn(reserva);

        ReservaModel result = reservaController.consultarReserva(idReserva);
        assertEquals(reserva, result);
    }

    @Test
    public void testCrearReserva() {
        ReservaDTO reservaDTO = new ReservaDTO();
        doNothing().when(reservaService).crearReserva(anyString(), any(LocalDate.class), any(DiaSemanaModel.class), anyString(), anyString(), anyBoolean());

        ResponseEntity<String> response = reservaController.crearReserva(reservaDTO);
        assertEquals("Reserva creada", response.getBody());
    }

    // @Test
    // public void testActualizarReserva() {
    //     ReservaDTO reservaDTO = new ReservaDTO();
    //     doNothing().when(reservaService).actualizarReserva(anyString(), anyString(), any(LocalDate.class), any(DiaSemanaModel.class), anyString(), anyBoolean());

    //     ResponseEntity<String> response = reservaController.actualizarReserva(reservaDTO);
    //     assertEquals("Reserva actualizada", response.getBody());
    // }

    @Test
    public void testDeleteReserva() {
        String idReserva = "1";
        doNothing().when(reservaService).deleteReserva(idReserva);

        ResponseEntity<String> response = reservaController.deleteReserva(idReserva);
        assertEquals("Reserva eliminada", response.getBody());
    }

    @Test
    public void testCancelReserva() {
        String idReserva = "1";
        doNothing().when(reservaService).cancelReserva(idReserva);

        ResponseEntity<String> response = reservaController.cancelReserva(idReserva);
        assertEquals("Reserva cancelada", response.getBody());
    }

    @Test
    public void testRechazarReserva() {
        String idReserva = "1";
        doNothing().when(reservaService).rechazarReserva(idReserva);

        ResponseEntity<String> response = reservaController.rechazarReserva(idReserva);
        assertEquals("Reserva rechazada", response.getBody());
    }
}