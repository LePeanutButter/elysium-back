// package edu.eci.cvds.elysium.dto;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;
// import java.time.LocalDate;
// import edu.eci.cvds.elysium.model.DiaSemana;

// public class ReservaDTOTest {

//     @Test
//     public void testDefaultConstructor() {
//         ReservaDTO reserva = new ReservaDTO();
//         assertNull(reserva.getIdReserva());
//         assertNull(reserva.getFechaReserva());
//         assertNull(reserva.getDiaSemana());
//         assertNull(reserva.getProposito());
//         assertNull(reserva.getIdSalon());
//         assertFalse(reserva.isDuracionBloque());
//     }

//     @Test
//     public void testConstructorWithParameters() {
//         LocalDate fechaReserva = LocalDate.now();
//         DiaSemana diaSemana = DiaSemana.LUNES;
//         double hora = 8.0;
//         int prioridad = 3;

//         ReservaDTO reserva = new ReservaDTO("1", fechaReserva, hora,diaSemana, "Meeting", "101", true, prioridad);

//         assertEquals("1", reserva.getIdReserva());
//         assertEquals(fechaReserva, reserva.getFechaReserva());
//         assertEquals(diaSemana, reserva.getDiaSemana());
//         assertEquals("Meeting", reserva.getProposito());
//         assertEquals("101", reserva.getIdSalon());
//         assertTrue(reserva.isDuracionBloque());
//         assertEquals(prioridad, reserva.getPrioridad());
//     }

//     @Test
//     public void testConstructorWithTipoCampo() {
//         LocalDate fechaReserva = LocalDate.now();
//         DiaSemana diaSemana = DiaSemana.LUNES;
//         int hora = 8;
//         int prioridad = 4;  // Agregamos la prioridad requerida

//         ReservaDTO reserva = new ReservaDTO("1", 'A', fechaReserva,hora, diaSemana, "101", true, prioridad);

//         assertEquals("1", reserva.getIdReserva());
//         assertEquals('A', reserva.getTipoCampo());
//         assertEquals(fechaReserva, reserva.getFechaReserva());
//         assertEquals(diaSemana, reserva.getDiaSemana());
//         assertEquals("101", reserva.getIdSalon());
//         assertTrue(reserva.isDuracionBloque());
//         assertEquals(prioridad, reserva.getPrioridad());
//     }
// }
