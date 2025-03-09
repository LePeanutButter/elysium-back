package edu.eci.cvds.elysium.controller.usuario;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Administrador;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.AdministradorService;

class AdministradorControllerTest {

    @Mock
    private AdministradorService administradorService;

    @InjectMocks
    private AdministradorController administradorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test mínimo para el endpoint GET sin filtros
    @Test
    void testConsultarUsuariosSinFiltros() {
        // Como Usuario es abstracto, creamos instancias concretas de Administrador.
        Administrador admin1 = new Administrador(1, "Admin1", "Apellido1", "admin1@example.com", true);
        Administrador admin2 = new Administrador(2, "Admin2", "Apellido2", "admin2@example.com", true);
        List<Usuario> usuarios = Arrays.asList(admin1, admin2);
        
        when(administradorService.consultarUsuarios()).thenReturn(usuarios);

        List<Usuario> resultado = administradorController.consultarUsuarios(null, null);
        assertEquals(2, resultado.size());
        verify(administradorService).consultarUsuarios();
    }

    // Test mínimo para el endpoint POST agregarUsuario
    @Test
    void testAgregarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Juan", "Perez", "juan@example.com", true);
        administradorController.agregarUsuario(usuarioDTO);
        verify(administradorService).agregarUsuario(3, "Juan", "Perez", "juan@example.com", true);
    }

    // Test mínimo para el endpoint PATCH actualizarInformacionUsuario
    @Test
    void testActualizarInformacionUsuario() {
        ActualizarUsuarioDTO actualizarUsuarioDTO = new ActualizarUsuarioDTO();
        administradorController.actualizarInformacionUsuario(1, actualizarUsuarioDTO);
        verify(administradorService).actualizarInformacionUsuario(actualizarUsuarioDTO);
    }

    // Test mínimo para el endpoint PUT deshabilitarUsuario
    @Test
    void testDeshabilitarUsuario() {
        ResponseEntity<String> response = administradorController.deshabilitarUsuario(1);
        assertEquals("Usuario deshabilitado exitosamente", response.getBody());
        verify(administradorService).deshabilitarUsuario(1);
    }

    // Test mínimo para el endpoint PUT habilitarUsuario
    @Test
    void testHabilitarUsuario() {
        ResponseEntity<String> response = administradorController.habilitarUsuario(1);
        assertEquals("Usuario habilitado exitosamente", response.getBody());
        verify(administradorService).habilitarUsuario(1);
    }

    // Test mínimo para el endpoint PUT hacerAdmin
    @Test
    void testHacerAdmin() {
        ResponseEntity<String> response = administradorController.hacerAdmin(1);
        assertEquals("Usuario ahora es administrador", response.getBody());
        verify(administradorService).hacerAdmin(1);
    }

    // Test mínimo para el endpoint PUT quitarAdmin
    @Test
    void testQuitarAdmin() {
        ResponseEntity<String> response = administradorController.quitarAdmin(1);
        assertEquals("Usuario ya no es administrador", response.getBody());
        verify(administradorService).quitarAdmin(1);
    }
}
