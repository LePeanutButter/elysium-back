package edu.eci.cvds.elysium.model;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;




public class DiaSemanaTest {

    private static Field horariosField;

    @BeforeAll
    public static void setup() throws NoSuchFieldException {
        // Access the private field "horarios" using reflection.
        horariosField = DiaSemana.class.getDeclaredField("horarios");
        horariosField.setAccessible(true);
    }

    // Helper method to extract the "horarios" TreeMap from a DiaSemana enum constant.
    @SuppressWarnings("unchecked")
    private TreeMap<String, List<Double>> getHorarios(DiaSemana dia) throws IllegalAccessException {
        return (TreeMap<String, List<Double>>) horariosField.get(dia);
    }

    @Test
    public void testDiaSemanaEntreSemana() throws IllegalAccessException {
        // Expected schedule for weekdays: LUNES, MARTES, MIERCOLES, JUEVES, VIERNES.
        List<Double> expectedHoras = List.of(7.0, 8.5, 10.0, 11.5, 13.0, 14.5, 16.0, 17.5, 19.0);
        DiaSemana[] diasEntreSemana = {DiaSemana.LUNES, DiaSemana.MARTES, DiaSemana.MIERCOLES, DiaSemana.JUEVES, DiaSemana.VIERNES};

        for (DiaSemana dia : diasEntreSemana) {
            TreeMap<String, List<Double>> horarios = getHorarios(dia);
            assertNotNull(horarios, "Horarios should not be null for " + dia);
            assertTrue(horarios.containsKey("horas"), "Horarios should contain key 'horas' for " + dia);
            assertEquals(expectedHoras, horarios.get("horas"), "Schedule for " + dia + " does not match expected.");
        }
    }

    @Test
    public void testDiaSemanaSabado() throws IllegalAccessException {
        // Expected schedule for SABADO.
        List<Double> expectedHoras = List.of(7.0, 8.5, 10.0, 11.5, 13.0);
        TreeMap<String, List<Double>> horarios = getHorarios(DiaSemana.SABADO);
        assertNotNull(horarios, "Horarios should not be null for SABADO.");
        assertTrue(horarios.containsKey("horas"), "Horarios should contain key 'horas' for SABADO.");
        assertEquals(expectedHoras, horarios.get("horas"), "Schedule for SABADO does not match expected.");
    }

    @Test
    public void testHorariosNoExtraneousData() throws IllegalAccessException {
        // Verify that the "horarios" TreeMap contains only the expected key "horas".
        for (DiaSemana dia : DiaSemana.values()) {
            TreeMap<String, List<Double>> horarios = getHorarios(dia);
            if (horarios.isEmpty()) {
                // In the current implementation, this should not occur for our enum values.
                fail("Horarios for " + dia + " should not be empty.");
            } else {
                assertEquals(1, horarios.size(), "There should be exactly one key in horarios for " + dia);
                assertTrue(horarios.containsKey("horas"), "Only key should be 'horas' for " + dia);
            }
        }
    }
}