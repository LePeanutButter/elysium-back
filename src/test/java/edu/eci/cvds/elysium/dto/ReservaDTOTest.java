package edu.eci.cvds.elysium.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import edu.eci.cvds.elysium.model.DiaSemanaModel;


public class ReservaDTOTest {

    @Test
    public void testDefaultConstructor() {
        ReservaDTO reserva = new ReservaDTO();
        assertNull(reserva.getIdReserva());
        assertNull(reserva.getFechaReserva());
        assertNull(reserva.getDiaSemana());
        assertNull(reserva.getProposito());
        assertNull(reserva.getIdSalon());
        assertFalse(reserva.isDuracionBloque());
    }

    @Test
    public void testConstructorWithParameters() {
        LocalDate fechaReserva = LocalDate.now();
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        ReservaDTO reserva = new ReservaDTO("1", fechaReserva, diaSemana, "Meeting", "101", true);

        assertEquals("1", reserva.getIdReserva());
        assertEquals(fechaReserva, reserva.getFechaReserva());
        assertEquals(diaSemana, reserva.getDiaSemana());
        assertEquals("Meeting", reserva.getProposito());
        assertEquals("101", reserva.getIdSalon());
        assertTrue(reserva.isDuracionBloque());
    }

    @Test
    public void testConstructorWithTipoCampo() {
        LocalDate fechaReserva = LocalDate.now();
        DiaSemanaModel diaSemana = DiaSemanaModel.LUNES;
        ReservaDTO reserva = new ReservaDTO("1", 'A', fechaReserva, diaSemana, "101", true);

        assertEquals("1", reserva.getIdReserva());
        assertEquals('A', reserva.getTipoCampo());
        assertEquals(fechaReserva, reserva.getFechaReserva());
        assertEquals(diaSemana, reserva.getDiaSemana());
        assertEquals("101", reserva.getIdSalon());
        assertTrue(reserva.isDuracionBloque());
    }
}