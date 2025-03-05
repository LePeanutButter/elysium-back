package edu.eci.cvds.elysium.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;







public class RecursoDTOTest {

    @Test
    public void testDefaultConstructor() {
        RecursoDTO recurso = new RecursoDTO();
        assertNotNull(recurso);
    }

    @Test
    public void testParameterizedConstructor() {
        String nombre = "Recurso1";
        int cantidad = 10;
        List<String> especificaciones = Arrays.asList("Spec1", "Spec2");

        RecursoDTO recurso = new RecursoDTO(nombre, cantidad, especificaciones);

        assertNotNull(recurso.getId());
        assertEquals(nombre, recurso.getNombre());
        assertEquals(cantidad, recurso.getCantidad());
        assertEquals(especificaciones, recurso.getEspecificaciones());
    }

    @Test
    public void testFullParameterizedConstructor() {
        ObjectId objectId = new ObjectId();
        char tipoCampo = 'A';
        String nombre = "Recurso2";
        int cantidad = 20;
        List<String> especificaciones = Arrays.asList("Spec3", "Spec4");

        RecursoDTO recurso = new RecursoDTO(objectId, tipoCampo, nombre, cantidad, especificaciones);

        assertEquals(objectId, recurso.getId());
        assertEquals(tipoCampo, recurso.getTipoCampo());
        assertEquals(nombre, recurso.getNombre());
        assertEquals(cantidad, recurso.getCantidad());
        assertEquals(especificaciones, recurso.getEspecificaciones());
    }

    @Test
    public void testSettersAndGetters() {
        RecursoDTO recurso = new RecursoDTO();
        ObjectId objectId = new ObjectId();
        char tipoCampo = 'B';
        String nombre = "Recurso3";
        int cantidad = 30;
        List<String> especificaciones = Arrays.asList("Spec5", "Spec6");

        recurso.setId(objectId);
        

        assertEquals(objectId, recurso.getId());
    }
}