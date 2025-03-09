package edu.eci.cvds.elysium.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaModelTest {

    private ReservaModel reserva;

    @BeforeEach
    public void setUp() {
        reserva = new ReservaModel("1", LocalDate.now(), DiaSemanaModel.LUNES, "Meeting", "A101", true);
    }

    @Test
    public void testCrearReserva() {
        ReservaModel nuevaReserva = reserva.crearReserva("2", LocalDate.now().plusDays(1), DiaSemanaModel.MARTES, "Conference", "B202", false);
        assertNotNull(nuevaReserva);
        assertEquals("2", nuevaReserva.getIdReserva());
        assertEquals(LocalDate.now().plusDays(1), nuevaReserva.getFechaReserva());
        assertEquals(DiaSemanaModel.MARTES, nuevaReserva.getDiaSemana());
        assertEquals("Conference", nuevaReserva.getProposito());
        assertEquals("B202", nuevaReserva.getIdSalon());
        assertFalse(nuevaReserva.isDuracionBloque());
        assertEquals(EstadoReservaModel.ACTIVA, nuevaReserva.getEstado());
    }

    @Test
    public void testActualizar() {
        reserva.actualizar("1", 'f', LocalDate.now().plusDays(2), null, null, false);
        assertEquals(LocalDate.now().plusDays(2), reserva.getFechaReserva());

        reserva.actualizar("1", 'd', null, DiaSemanaModel.MIERCOLES, null, false);
        assertEquals(DiaSemanaModel.MIERCOLES, reserva.getDiaSemana());

        reserva.actualizar("1", 's', null, null, "C303", false);
        assertEquals("C303", reserva.getIdSalon());

        reserva.actualizar("1", 'b', null, null, null, false);
        assertFalse(reserva.isDuracionBloque());
    }

    @Test
    public void testDeleteReserva() {
        reserva.deleteReserva("1");
        assertEquals(EstadoReservaModel.ELIMINADA, reserva.getEstado());
    }

    @Test
    public void testCancelReserva() {
        reserva.cancelReserva("1");
        assertEquals(EstadoReservaModel.CANCELADA, reserva.getEstado());
    }

    @Test
    public void testRechazarReserva() {
        reserva.rechazarReserva("1");
        assertEquals(EstadoReservaModel.RECHAZADA, reserva.getEstado());
    }
}