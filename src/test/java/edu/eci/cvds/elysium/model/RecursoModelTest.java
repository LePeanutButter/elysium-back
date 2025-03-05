package edu.eci.cvds.elysium.model;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class RecursoModelTest {

    private RecursoModel recurso;

    @BeforeEach
    public void setUp() {
        recurso = new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1", "Especificacion2"));
    }

    @Test
    public void testCrearRecurso() {
        RecursoModel nuevoRecurso = recurso.crearRecurso("Recurso2", 5, Arrays.asList("Especificacion3"));
        assertNotNull(nuevoRecurso.getId());
        assertEquals("Recurso2", nuevoRecurso.getNombre());
        assertEquals(5, nuevoRecurso.getCantidad());
        assertEquals(Arrays.asList("Especificacion3"), nuevoRecurso.getEspecificaciones());
        assertTrue(nuevoRecurso.isActivo());
    }

    @Test
    public void testActualizarNombre() {
        ObjectId id = recurso.getId();
        recurso.actualizar(id, 'n', "RecursoActualizado", 0, null);
        assertEquals("RecursoActualizado", recurso.getNombre());
    }

    @Test
    public void testActualizarCantidad() {
        ObjectId id = recurso.getId();
        recurso.actualizar(id, 'c', null, 20, null);
        assertEquals(20, recurso.getCantidad());
    }

    @Test
    public void testActualizarEspecificaciones() {
        ObjectId id = recurso.getId();
        List<String> nuevasEspecificaciones = Arrays.asList("EspecificacionActualizada1", "EspecificacionActualizada2");
        recurso.actualizar(id, 'e', null, 0, nuevasEspecificaciones);
        assertEquals(nuevasEspecificaciones, recurso.getEspecificaciones());
    }

    @Test
    public void testEliminar() {
        ObjectId id = recurso.getId();
        recurso.eliminar(id);
        assertFalse(recurso.isActivo());
    }

    @Test
    public void testSettersAndGetters() {
        ObjectId newId = new ObjectId();
        recurso.setId(newId);
        assertEquals(newId, recurso.getId());

        recurso.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", recurso.getNombre());

        recurso.setCantidad(15);
        assertEquals(15, recurso.getCantidad());

        List<String> nuevasEspecificaciones = Arrays.asList("NuevaEspecificacion1");
        recurso.setEspecificaciones(nuevasEspecificaciones);
        assertEquals(nuevasEspecificaciones, recurso.getEspecificaciones());

        recurso.setActivo(false);
        assertFalse(recurso.isActivo());
    }
}