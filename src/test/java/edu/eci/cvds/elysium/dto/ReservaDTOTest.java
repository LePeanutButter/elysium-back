package edu.eci.cvds.elysium.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.eci.cvds.elysium.model.DiaSemana;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


public class ReservaDTOTest {

    private static Validator validator;

    

    @Test
    public void testDefaultConstructor() {
        ReservaDTO reserva = new ReservaDTO();
        assertNull(reserva.getIdReserva());
        assertNull(reserva.getFechaReserva());
        assertNull(reserva.getHora());
        assertNull(reserva.getDiaSemana());
        assertNull(reserva.getProposito());
        assertNull(reserva.getMateria());
        assertNull(reserva.getIdSalon());
        assertNull(reserva.isDuracionBloque());
        assertNull(reserva.getPrioridad());
        assertNull(reserva.getIdUsuario());
    }

    @Test
    public void testValidationWithValidValues() {
        ReservaDTO reserva = new ReservaDTO();
        try {
            // Set a valid negative value for hora
            Field horaField = ReservaDTO.class.getDeclaredField("hora");
            horaField.setAccessible(true);
            horaField.set(reserva, -5.0);
            
            // Set required NotNull fields
            Field propositoField = ReservaDTO.class.getDeclaredField("proposito");
            propositoField.setAccessible(true);
            propositoField.set(reserva, "Clase de matemáticas");

            Field idSalonField = ReservaDTO.class.getDeclaredField("idSalon");
            idSalonField.setAccessible(true);
            idSalonField.set(reserva, "SalonA");

            // Optionally set some additional fields
            Field fechaReservaField = ReservaDTO.class.getDeclaredField("fechaReserva");
            fechaReservaField.setAccessible(true);
            fechaReservaField.set(reserva, LocalDate.now());
            
            Field diaSemanaField = ReservaDTO.class.getDeclaredField("diaSemana");
            diaSemanaField.setAccessible(true);
            diaSemanaField.set(reserva, DiaSemana.LUNES);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testValidationForInvalidHora() {
        ReservaDTO reserva = new ReservaDTO();
        try {
            // Set an invalid positive value for hora that should violate @Negative
            Field horaField = ReservaDTO.class.getDeclaredField("hora");
            horaField.setAccessible(true);
            horaField.set(reserva, 4.0);

            // Set required NotNull fields to prevent unrelated violations
            Field propositoField = ReservaDTO.class.getDeclaredField("proposito");
            propositoField.setAccessible(true);
            propositoField.set(reserva, "Reserva errónea");

            Field idSalonField = ReservaDTO.class.getDeclaredField("idSalon");
            idSalonField.setAccessible(true);
            idSalonField.set(reserva, "SalonB");
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }
}