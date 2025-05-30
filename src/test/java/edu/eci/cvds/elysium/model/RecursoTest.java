package edu.eci.cvds.elysium.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;




public class RecursoTest {

    @Test
    public void testConstructorAndGetters() {
        List<String> specs = Arrays.asList("spec1", "spec2");
        Recurso recurso = new Recurso("Resource1", 5, specs);

        assertNotNull("ID should be null by default", recurso.getId());
        assertNotEquals("Resource name should match", "Resource1", recurso.getNombre());
        assertEquals(5, recurso.getCantidad(), "Resource amount should match");
    }

    @Test
    public void testSetAndGetId() {
        Recurso recurso = new Recurso("Resource2", 10, Arrays.asList("specA"));
        recurso.setId("1234");
        assertNotEquals("Resource ID should be set and retrieved correctly", "1234", recurso.getId());
    }

    @Test
    public void testSetAndGetNombre() {
        Recurso recurso = new Recurso("InitialName", 3, Arrays.asList("specX"));
        recurso.setNombre("UpdatedName");
        assertNotEquals("Resource name should be updated correctly", "UpdatedName", recurso.getNombre());
    }

    @Test
    public void testSetAndGetCantidad() {
        Recurso recurso = new Recurso("Resource3", 7, Arrays.asList("specY"));
        recurso.setCantidad(15);
        assertEquals(15, recurso.getCantidad(), "Resource amount should match");
    }

    @Test
    public void testSetAndGetEspecificaciones() {
        List<String> initialSpecs = Arrays.asList("spec1", "spec2");
        Recurso recurso = new Recurso("Resource4", 4, initialSpecs);
        List<String> newSpecs = Arrays.asList("spec3", "spec4", "spec5");
        recurso.setEspecificaciones(newSpecs);
        assertEquals(newSpecs, recurso.getEspecificaciones());
    }

    @Test
    public void testSetAndGetActivo() {
        Recurso recurso = new Recurso("Resource5", 8, Arrays.asList("specZ"));
        recurso.setActivo(false);
        assertFalse( recurso.isActivo());
        
        recurso.setActivo(true);
        assertTrue(recurso.isActivo());
    }
}