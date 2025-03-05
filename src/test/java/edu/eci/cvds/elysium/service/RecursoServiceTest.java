// package edu.eci.cvds.elysium.service;

// import edu.eci.cvds.elysium.model.RecursoModel;
// import org.bson.types.ObjectId;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;






// public class RecursoServiceTest {

//     @Mock
//     private RecursoService recursoService;

//     @InjectMocks
//     private RecursoServiceImpl recursoServiceImpl;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     public void testConsultarRecursos() {
//         List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
//         when(recursoService.consultarRecursos()).thenReturn(recursos);

//         List<RecursoModel> result = recursoServiceImpl.consultarRecursos();
//         assertEquals(2, result.size());
//         verify(recursoService, times(1)).consultarRecursos();
//     }

//     @Test
//     public void testConsultarNombre() {
//         String nombre = "Recurso1";
//         List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
//         when(recursoService.consultarNombre(nombre)).thenReturn(recursos);

//         List<RecursoModel> result = recursoServiceImpl.consultarNombre(nombre);
//         assertEquals(1, result.size());
//         verify(recursoService, times(1)).consultarNombre(nombre);
//     }

//     // @Test
//     // public void testConsultarCantidad() {
//     //     int cantidad = 10;
//     //     List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
//     //     when(recursoService.consultarCantidad(cantidad)).thenReturn(recursos);

//     //     List<RecursoModel> result = recursoServiceImpl.consultarCantidad(cantidad);
//     //     assertEquals(1, result.size());
//     //     verify(recursoService, times(1)).consultarCantidad(cantidad);
//     // }

//     // @Test
//     // public void testConsultarEspecificaciones() {
//     //     List<String> especificaciones = Arrays.asList("Especificacion1");
//     //     List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
//     //     when(recursoService.consultarEspecificaciones(especificaciones)).thenReturn(recursos);

//     //     List<RecursoModel> result = recursoServiceImpl.consultarEspecificaciones(especificaciones);
//     //     assertEquals(1, result.size());
//     //     verify(recursoService, times(1)).consultarEspecificaciones(especificaciones);
//     // }

//     // @Test
//     // public void testConsultarRecurso() {
//     //     ObjectId id = new ObjectId();
//     //     RecursoModel recurso = new RecursoModel();
//     //     when(recursoService.consultarRecurso(id)).thenReturn(recurso);

//     //     RecursoModel result = recursoServiceImpl.consultarRecurso(id);
//     //     assertNotNull(result);
//     //     verify(recursoService, times(1)).consultarRecurso(id);
//     // }

//     // @Test
//     // public void testAgregarRecurso() {
//     //     String nombre = "Recurso1";
//     //     int cantidad = 10;
//     //     List<String> especificaciones = Arrays.asList("Especificacion1");

//     //     doNothing().when(recursoService).agregarRecurso(nombre, cantidad, especificaciones);

//     //     recursoServiceImpl.agregarRecurso(nombre, cantidad, especificaciones);
//     //     verify(recursoService, times(1)).agregarRecurso(nombre, cantidad, especificaciones);
//     // }

//     // @Test
//     // public void testActualizarRecurso() {
//     //     ObjectId id = new ObjectId();
//     //     char tipoCampo = 'A';
//     //     String nombre = "Recurso1";
//     //     int cantidad = 10;
//     //     List<String> especificaciones = Arrays.asList("Especificacion1");

//     //     doNothing().when(recursoService).actualizarRecurso(id, tipoCampo, nombre, cantidad, especificaciones);

//     //     recursoServiceImpl.actualizarRecurso(id, tipoCampo, nombre, cantidad, especificaciones);
//     //     verify(recursoService, times(1)).actualizarRecurso(id, tipoCampo, nombre, cantidad, especificaciones);
//     // }

//     @Test
//     public void testEliminarRecurso() {
//         ObjectId id = new ObjectId();

//         doNothing().when(recursoService).eliminarRecurso(id);

//         recursoServiceImpl.eliminarRecurso(id);
//         verify(recursoService, times(1)).eliminarRecurso(id);
//     }
// }