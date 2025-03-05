package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.repository.RecursoRepository;



public class RecursoServiceImplTest {

    @Mock
    private RecursoRepository recursoRepository;

    @InjectMocks
    private RecursoServiceImpl recursoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsultarRecursos() {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1")));
        when(recursoRepository.findAll()).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarRecursos();

        assertEquals(1, result.size());
        assertEquals("Recurso1", result.get(0).getNombre());
    }

    @Test
    public void testConsultarNombre() {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1")));
        when(recursoRepository.findByNombre("Recurso1")).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarNombre("Recurso1");

        assertEquals(1, result.size());
        assertEquals("Recurso1", result.get(0).getNombre());
    }

    @Test
    public void testConsultarCantidad() {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1")));
        when(recursoRepository.findByCantidad(10)).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarCantidad(10);

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getCantidad());
    }

    @Test
    public void testConsultarEspecificaciones() {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1")));
        when(recursoRepository.findByEspecificaciones(Arrays.asList("Especificacion1"))).thenReturn(recursos);

        List<RecursoModel> result = recursoService.consultarEspecificaciones(Arrays.asList("Especificacion1"));

        assertEquals(1, result.size());
        assertEquals("Especificacion1", result.get(0).getEspecificaciones().get(0));
    }

    @Test
    public void testConsultarRecurso() {
        RecursoModel recurso = new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1"));
        when(recursoRepository.findByObjectID(any(ObjectId.class))).thenReturn(recurso);

        RecursoModel result = recursoService.consultarRecurso(new ObjectId());

        assertNotNull(result);
        assertEquals("Recurso1", result.getNombre());
    }

    @Test
    public void testAgregarRecurso() {
        RecursoModel recurso = new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1"));
        recursoService.agregarRecurso("Recurso1", 10, Arrays.asList("Especificacion1"));

        verify(recursoRepository, times(1)).save(any(RecursoModel.class));
    }

    @Test
    public void testActualizarRecurso() {
        RecursoModel recurso = new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1"));
        when(recursoRepository.findByObjectID(any(ObjectId.class))).thenReturn(recurso);

        recursoService.actualizarRecurso(new ObjectId(), 'n', "NuevoNombre", 20, Arrays.asList("NuevaEspecificacion"));

        verify(recursoRepository, times(1)).save(any(RecursoModel.class));
        assertEquals("NuevoNombre", recurso.getNombre());
    }

    @Test
    public void testEliminarRecurso() {
        RecursoModel recurso = new RecursoModel("Recurso1", 10, Arrays.asList("Especificacion1"));
        when(recursoRepository.findByObjectID(any(ObjectId.class))).thenReturn(recurso);

        recursoService.eliminarRecurso(new ObjectId());

        verify(recursoRepository, times(1)).delete(any(RecursoModel.class));
        assertFalse(recurso.isActivo());
    }
}