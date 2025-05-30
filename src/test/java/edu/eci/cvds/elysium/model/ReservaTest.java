package edu.eci.cvds.elysium.model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class ReservaTest {

    private Reserva reserva;
    private LocalDate today;

    @BeforeEach
    public void setUp() {
        // initializing with sample valid data
        today = LocalDate.now();
        reserva = new Reserva(today, 10.5, DiaSemana.LUNES, "Estudio", "Matemáticas", "SAL001", true, 3, 101);
    }

    @Test
    public void testParameterizedConstructor() {
        // Check initial values set by constructor
        assertEquals(today, reserva.getFechaReserva());
        assertEquals(10.5, reserva.getHora());
        assertEquals(DiaSemana.LUNES, reserva.getDiaSemana());
        assertEquals("Estudio", reserva.getProposito());
        assertEquals("Matemáticas", reserva.getMateria());
        assertEquals("SAL001", reserva.getIdSalon());
        // The status is set to ACTIVA by default in the constructor.
        assertEquals(EstadoReserva.ACTIVA, reserva.getEstado());
        assertTrue(reserva.isDuracionBloque());
        assertEquals(3, reserva.getPrioridad());
        assertEquals(101, reserva.getIdUsuario());
    }
    
    @Test
    public void testSettersAndGetters() {
        // Set additional properties
        reserva.setIdReserva("RES12345");
        reserva.setFechaReserva(LocalDate.of(2022, 5, 20));
        reserva.setHora(8.0);
        reserva.setDiaSemana(DiaSemana.MARTES);
        reserva.setProposito("Reunión");
        reserva.setMateria("Física");
        reserva.setIdSalon("SAL002");
        reserva.setEstado(EstadoReserva.DESHABILITADA);
        reserva.setDuracionBloque(false);
        reserva.setPrioridad(5);
        reserva.setIdUsuario(202);
        
        // Verify all values
        assertEquals("RES12345", reserva.getIdReserva());
        assertEquals(LocalDate.of(2022, 5, 20), reserva.getFechaReserva());
        assertEquals(8.0, reserva.getHora());
        assertEquals(DiaSemana.MARTES, reserva.getDiaSemana());
        assertEquals("Reunión", reserva.getProposito());
        assertEquals("Física", reserva.getMateria());
        assertEquals("SAL002", reserva.getIdSalon());
        assertEquals(EstadoReserva.DESHABILITADA, reserva.getEstado());
        assertFalse(reserva.isDuracionBloque());
        assertEquals(5, reserva.getPrioridad());
        assertEquals(202, reserva.getIdUsuario());
    }
    
    @Test
    public void testSetPrioridadValid() {
        // Testing valid priority values from 1 to 5.
        for (int p = 1; p <= 5; p++) {
            reserva.setPrioridad(p);
            assertEquals(p, reserva.getPrioridad());
        }
    }
    
    @Test
    public void testSetPrioridadInvalidThrowsException() {
        // Testing for invalid priority values less than 1 and greater than 5.
        Exception exceptionLow = assertThrows(IllegalArgumentException.class, () -> {
            reserva.setPrioridad(0);
        });
        assertEquals("La prioridad debe estar entre 1 y 5.", exceptionLow.getMessage());

        Exception exceptionHigh = assertThrows(IllegalArgumentException.class, () -> {
            reserva.setPrioridad(6);
        });
        assertEquals("La prioridad debe estar entre 1 y 5.", exceptionHigh.getMessage());
    }
}