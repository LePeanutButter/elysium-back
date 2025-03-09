package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import edu.eci.cvds.elysium.model.*;
import edu.eci.cvds.elysium.repository.ReservaRepository;

public class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarReservas() {
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findAll()).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservas();
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorSalon() {
        String idSalon = "1";
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByIdSalon(idSalon)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorSalon(idSalon);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorFecha() {
        LocalDate fecha = LocalDate.now();
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByFechaReserva(fecha)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorFecha(fecha);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorDiaSemana() {
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByDiaSemana(diaSemana)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorDiaSemana(diaSemana);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorEstado() {
        EstadoReservaModel estado = EstadoReservaModel.ACTIVA;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByEstado(estado)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorEstado(estado);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReservasPorDuracionBloque() {
        boolean duracionBloque = true;
        List<ReservaModel> reservas = Arrays.asList(new ReservaModel(), new ReservaModel());
        when(reservaRepository.findByDuracionBloque(duracionBloque)).thenReturn(reservas);

        List<ReservaModel> result = reservaService.consultarReservasPorDuracionBloque(duracionBloque);
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarReserva() {
        String idReserva = "1";
        ReservaModel reserva = new ReservaModel();
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

        ReservaModel result = reservaService.consultarReserva(idReserva);
        assertNotNull(result);
    }

    @Test
    public void testCrearReserva() {
        String idReserva = "1";
        LocalDate fechaReserva = LocalDate.now();
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        String proposito = "Meeting";
        String idSalon = "101";
        boolean duracionBloque = true;

        reservaService.crearReserva(idReserva, fechaReserva, diaSemana, proposito, idSalon, duracionBloque);
        verify(reservaRepository, times(1)).save(any(ReservaModel.class));
    }

    // @Test
    // public void testActualizarReserva() {
    //     String idReserva = "1";
    //     char tipoCampo = 'f';
    //     LocalDate value1 = LocalDate.now();
    //     DiaSemanaModel value2 = DiaSemanaModel.LUNES;
    //     String value3 = "101";
    //     boolean value4 = true;

    //     ReservaModel reserva = new ReservaModel();
    //     when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

    //     reservaService.actualizarReserva(idReserva, tipoCampo, value1, value2, value3, value4);
    //     verify(reservaRepository, times(1)).save(any(ReservaModel.class));
    // }

    // @Test
    // public void testDeleteReserva() {
    //     String idReserva = "1";
    //     ReservaModel reserva = new ReservaModel();
    //     when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

    //     reservaService.deleteReserva(idReserva);
    //     verify(reservaRepository, times(1)).save(any(ReservaModel.class));
    // }

    // @Test
    // public void testCancelReserva() {
    //     String idReserva = "1";
    //     ReservaModel reserva = new ReservaModel();
    //     when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

    //     reservaService.cancelReserva(idReserva);
    //     verify(reservaRepository, times(1)).save(any(ReservaModel.class));
    // }

    // @Test
    // public void testRechazarReserva() {
    //     String idReserva = "1";
    //     ReservaModel reserva = new ReservaModel();
    //     when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reserva);

    //     reservaService.rechazarReserva(idReserva);
    //     verify(reservaRepository, times(1)).save(any(ReservaModel.class));
    // }
}