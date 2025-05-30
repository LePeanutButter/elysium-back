package edu.eci.cvds.elysium.model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class SalonTest {


    @Test
    public void testDefaultConstructor() {
        Salon salon = new Salon();
        // Default values are not set by default constructor
        assertNull(salon.getNombre());
        assertNull(salon.getMnemonico());
        assertNull(salon.getDescripcion());
        assertNull(salon.getUbicacion());
        assertEquals(0, salon.getCapacidad());
        assertNull(salon.getRecursos());
        // Since the default constructor does not initialize flags, they should be false
        assertFalse(salon.isDisponible());
        assertFalse(salon.isActivo());
    }

    @Test
    public void testParameterizedConstructor() {
        List<Recurso> recursos = new ArrayList<>();
        
        Salon salon = new Salon("Salon1", "M001", "Test Salon", "Building A", 100, recursos);
        assertEquals("Salon1", salon.getNombre());
        assertEquals("M001", salon.getMnemonico());
        assertEquals("Test Salon", salon.getDescripcion());
        assertEquals("Building A", salon.getUbicacion());
        assertEquals(100, salon.getCapacidad());
        assertEquals(recursos, salon.getRecursos());
        // On parameterized construction, available and active are true
        assertTrue(salon.isDisponible());
        assertTrue(salon.isActivo());
    }

    @Test
    public void testSettersAndGetters() {
        Salon salon = new Salon();
        
        salon.setNombre("Main Hall");
        salon.setMnemonico("MH001");
        salon.setDescripcion("Large salon for events");
        salon.setUbicacion("First Floor");
        salon.setCapacidad(200);
        
        salon.setDisponible(true);
        salon.setActivo(true);

        assertEquals("Main Hall", salon.getNombre());
        assertEquals("MH001", salon.getMnemonico());
        assertEquals("Large salon for events", salon.getDescripcion());
        assertEquals("First Floor", salon.getUbicacion());
        assertEquals(200, salon.getCapacidad());
        assertTrue(salon.isDisponible());
        assertTrue(salon.isActivo());
    }
}