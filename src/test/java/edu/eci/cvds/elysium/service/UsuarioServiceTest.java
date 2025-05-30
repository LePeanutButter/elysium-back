package edu.eci.cvds.elysium.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Usuario;

public class UsuarioServiceTest {

    @Mock
    private UsuarioService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgregarUsuario() throws Exception {
        // Prepare test data
        int idInstitucional = 12345;
        String nombre = "Juan";
        String apellido = "Perez";
        String correo = "jperez@institution.edu";
        boolean isAdmin = false;
        Usuario expectedUser = new Usuario(idInstitucional, nombre, apellido, correo, isAdmin, true);
        
        // Mock behavior
        when(service.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin))
            .thenReturn(expectedUser);
        
        // Execute test
        Usuario u = service.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        
        // Verify
        assertNotNull(u);
        assertEquals(idInstitucional, u.getIdInstitucional());
        assertEquals(nombre, u.getNombre());
        assertEquals(apellido, u.getApellido());
        assertEquals(correo, u.getCorreoInstitucional());
        assertTrue(u.getIsAdmin());
        verify(service).agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
    }

    @Test
    public void testConsultarUsuario() throws Exception {
        // Prepare test data
        int idInstitucional = 11111;
        Usuario expectedUser = new Usuario(idInstitucional, "Laura", "Gomez", "lgomez@institution.edu", true, true);
        
        // Mock behavior
        when(service.agregarUsuario(idInstitucional, "Laura", "Gomez", "lgomez@institution.edu", true))
            .thenReturn(expectedUser);
        when(service.consultarUsuario(idInstitucional)).thenReturn(expectedUser);
        
        // Execute test
        service.agregarUsuario(idInstitucional, "Laura", "Gomez", "lgomez@institution.edu", true);
        Usuario u = service.consultarUsuario(idInstitucional);
        
        // Verify
        assertNotNull(u);
        assertEquals("Laura", u.getNombre());
        assertTrue(u.getIsAdmin());
        verify(service).consultarUsuario(idInstitucional);
    }

    @Test
    public void testConsultarUsuarioPorCorreo() throws Exception {
        // Prepare test data
        int idInstitucional = 22222;
        String correo = "cramirez@institution.edu";
        Usuario expectedUser = new Usuario(idInstitucional, "Carlos", "Ramirez", correo, false, true);
        
        // Mock behavior
        when(service.agregarUsuario(idInstitucional, "Carlos", "Ramirez", correo, false))
            .thenReturn(expectedUser);
        when(service.consultarUsuarioPorCorreo(correo)).thenReturn(expectedUser);
        
        // Execute test
        service.agregarUsuario(idInstitucional, "Carlos", "Ramirez", correo, false);
        Usuario u = service.consultarUsuarioPorCorreo(correo);
        
        // Verify
        assertNotNull(u);
        assertEquals(idInstitucional, u.getIdInstitucional());
        verify(service).consultarUsuarioPorCorreo(correo);
    }

    @Test
    public void testActualizarInformacionUsuario() throws Exception {
        // Prepare test data
        int idInstitucional = 33333;
        String nombre = "Ana Maria";
        String apellido = "Lopez Rodriguez";
        String correo = "anamlopez@institution.edu";
        
        Usuario originalUser = new Usuario(idInstitucional, "Ana", "Lopez", "alopez@institution.edu", false, true);
        Usuario updatedUser = new Usuario(idInstitucional, nombre, apellido, correo, false, true);
        UsuarioDTO dto = new UsuarioDTO();
        
        // Mock behavior
        when(service.agregarUsuario(idInstitucional, "Ana", "Lopez", "alopez@institution.edu", false))
            .thenReturn(originalUser);
        doNothing().when(service).actualizarInformacionUsuario(idInstitucional, dto);
        when(service.consultarUsuario(idInstitucional)).thenReturn(updatedUser);
        
        // Execute test
        service.agregarUsuario(idInstitucional, "Ana", "Lopez", "alopez@institution.edu", false);
        service.actualizarInformacionUsuario(idInstitucional, dto);
        Usuario updated = service.consultarUsuario(idInstitucional);
        
        // Verify
        assertEquals(nombre, updated.getNombre());
        assertEquals(apellido, updated.getApellido());
        assertEquals(correo, updated.getCorreoInstitucional());
        verify(service).actualizarInformacionUsuario(idInstitucional, dto);
    }

    @Test
    public void testCrearYListarReserva() throws Exception {
        // Prepare test data
        int userId = 44444;
        LocalDate fechaReserva = LocalDate.now();
        double hora = 9.0;
        DiaSemana dia = DiaSemana.LUNES;
        String proposito = "Clase de programacion";
        String materia = "Java";
        String idSalon = "SALON101";
        boolean duracionBloque = true;
        int prioridad = 1;
        
        Usuario user = new Usuario(userId, "Diego", "Martinez", "dmartinez@institution.edu", false, true);
        Reserva reserva = new Reserva(fechaReserva, hora, dia, proposito, materia, idSalon, duracionBloque, prioridad, userId);
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);
        
        // Mock behavior
        when(service.agregarUsuario(userId, "Diego", "Martinez", "dmartinez@institution.edu", false))
            .thenReturn(user);
        doNothing().when(service).crearReserva(fechaReserva, hora, dia, proposito, materia, 
                idSalon, duracionBloque, prioridad, userId);
        when(service.listarReservas(userId)).thenReturn(reservas);
        
        // Execute test
        service.agregarUsuario(userId, "Diego", "Martinez", "dmartinez@institution.edu", false);
        service.crearReserva(fechaReserva, hora, dia, proposito, materia, idSalon, duracionBloque, prioridad, userId);
        List<Reserva> resultado = service.listarReservas(userId);
        
        // Verify
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        Reserva r = resultado.get(0);
        assertEquals(fechaReserva, r.getFechaReserva());
        assertEquals(hora, r.getHora());
        assertEquals(dia, r.getDiaSemana());
        verify(service).listarReservas(userId);
    }

    @Test
    public void testListarReservasUsuarioInexistente() throws Exception {
        // Prepare test data
        int userId = 55555;
        
        // Mock behavior
        when(service.listarReservas(userId)).thenThrow(new ElysiumExceptions("Usuario no encontrado"));
        
        // Execute and verify
        Exception ex = assertThrows(ElysiumExceptions.class, () -> {
            service.listarReservas(userId);
        });
        assertTrue(ex.getMessage().contains("Usuario no encontrado"));
        verify(service).listarReservas(userId);
    }
}