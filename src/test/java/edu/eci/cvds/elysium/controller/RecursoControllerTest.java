package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.RecursoDTO;
import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.service.RecursoService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class RecursoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecursoService recursoService;

    @InjectMocks
    private RecursoController recursoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recursoController).build();
    }

    @Test
    public void testConsultarRecursos() throws Exception {
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel(), new RecursoModel());
        when(recursoService.consultarRecursos()).thenReturn(recursos);

        mockMvc.perform(get("/api/recurso/consultarRecursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(recursos.size()));

        verify(recursoService, times(1)).consultarRecursos();
    }

    @Test
    public void testConsultarNombre() throws Exception {
        String nombre = "Recurso1";
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
        when(recursoService.consultarNombre(nombre)).thenReturn(recursos);

        mockMvc.perform(get("/api/recurso/consultarNombre").param("nombre", nombre))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(recursos.size()));

        verify(recursoService, times(1)).consultarNombre(nombre);
    }

    @Test
    public void testConsultarCantidad() throws Exception {
        int cantidad = 10;
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
        when(recursoService.consultarCantidad(cantidad)).thenReturn(recursos);

        mockMvc.perform(get("/api/recurso/consultarCantidad").param("cantidad", String.valueOf(cantidad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(recursos.size()));

        verify(recursoService, times(1)).consultarCantidad(cantidad);
    }

    @Test
    public void testConsultarEspecificaciones() throws Exception {
        List<String> especificaciones = Arrays.asList("spec1", "spec2");
        List<RecursoModel> recursos = Arrays.asList(new RecursoModel());
        when(recursoService.consultarEspecificaciones(especificaciones)).thenReturn(recursos);

        mockMvc.perform(get("/api/recurso/consultarEspecificaciones").param("especificaciones", String.join(",", especificaciones)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(recursos.size()));

        verify(recursoService, times(1)).consultarEspecificaciones(especificaciones);
    }

    // @Test
    // public void testConsultarRecurso() throws Exception {
    //     ObjectId id = new ObjectId();
    //     RecursoModel recurso = new RecursoModel();
    //     when(recursoService.consultarRecurso(id)).thenReturn(recurso);

    //     mockMvc.perform(get("/api/recurso/consultarRecurso").param("id", id.toHexString()))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.id").value(id.toHexString()));

    //     verify(recursoService, times(1)).consultarRecurso(id);
    // }

    @Test
    public void testAgregarRecurso() throws Exception {
        RecursoDTO recursoDTO = new RecursoDTO("Recurso1", 10, Arrays.asList("spec1", "spec2"));

        mockMvc.perform(post("/api/recurso/agregarRecurso")
                .contentType("application/json")
                .content("{\"nombre\":\"Recurso1\",\"cantidad\":10,\"especificaciones\":[\"spec1\",\"spec2\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Recurso agregado"));

        verify(recursoService, times(1)).agregarRecurso(recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
    }

    // @Test
    // public void testActualizarRecurso() throws Exception {
    //     RecursoDTO recursoDTO = new RecursoDTO(new ObjectId(), "Recurso1", 10, Arrays.asList("spec1", "spec2"), "tipoCampo");

    //     mockMvc.perform(put("/api/recurso/actualizarRecurso")
    //             .contentType("application/json")
    //             .content("{\"id\":\"" + recursoDTO.getId().toHexString() + "\",\"nombre\":\"Recurso1\",\"cantidad\":10,\"especificaciones\":[\"spec1\",\"spec2\"],\"tipoCampo\":\"tipoCampo\"}"))
    //             .andExpect(status().isOk())
    //             .andExpect(content().string("Recurso actualizado"));

    //     verify(recursoService, times(1)).actualizarRecurso(recursoDTO.getId(), recursoDTO.getTipoCampo(), recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
    // }

    @Test
    public void testEliminarRecurso() throws Exception {
        ObjectId id = new ObjectId();

        mockMvc.perform(delete("/api/recurso/" + id.toHexString() + "/eliminarRecurso"))
                .andExpect(status().isOk())
                .andExpect(content().string("Recurso eliminado"));

        verify(recursoService, times(1)).eliminarRecurso(id);
    }
}