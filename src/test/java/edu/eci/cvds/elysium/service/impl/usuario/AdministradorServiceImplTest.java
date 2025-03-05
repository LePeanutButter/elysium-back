package edu.eci.cvds.elysium.service.impl.usuario;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Administrador;
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;

public class AdministradorServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AdministradorServiceImpl administradorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Consultar todos los usuarios
    @Test
    void testConsultarUsuarios() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(1, "Admin1", "A1", "admin1@example.com", true)
        );
        when(usuarioRepository.findAll()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuarios();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findAll();
    }

    // 2. Consultar usuarios activos
    @Test
    void testConsultarUsuariosActivos() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(2, "Admin2", "A2", "admin2@example.com", true)
        );
        when(usuarioRepository.findByActivoTrue()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosActivos();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByActivoTrue();
    }

    // 3. Consultar usuarios inactivos
    @Test
    void testConsultarUsuariosInactivos() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(3, "Admin3", "A3", "admin3@example.com", false)
        );
        when(usuarioRepository.findByActivoFalse()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosInactivos();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByActivoFalse();
    }

    // 4. Consultar usuarios administradores
    @Test
    void testConsultarUsuariosAdmins() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(4, "Admin4", "A4", "admin4@example.com", true)
        );
        when(usuarioRepository.findByIsAdminTrue()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosAdmins();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByIsAdminTrue();
    }

    // 5. Consultar usuarios activos y administradores
    @Test
    void testConsultarUsuariosActiveAdmins() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(5, "Admin5", "A5", "admin5@example.com", true)
        );
        when(usuarioRepository.findByActivoTrueAndIsAdminTrue()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosActiveAdmins();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByActivoTrueAndIsAdminTrue();
    }

    // 6. Consultar usuarios inactivos y administradores
    @Test
    void testConsultarUsuariosInactiveAdmins() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(6, "Admin6", "A6", "admin6@example.com", false)
        );
        when(usuarioRepository.findByActivoFalseAndIsAdminTrue()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosInactiveAdmins();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByActivoFalseAndIsAdminTrue();
    }

    // 7. Consultar usuarios activos que no son administradores
    @Test
    void testConsultarUsuariosActiveNoAdmins() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(7, "Admin7", "A7", "admin7@example.com", true)
        );
        when(usuarioRepository.findByActivoTrueAndIsAdminFalse()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosActiveNoAdmins();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByActivoTrueAndIsAdminFalse();
    }

    // 8. Consultar usuarios inactivos que no son administradores
    @Test
    void testConsultarUsuariosInactiveNoAdmins() {
        List<Usuario> dummyList = Arrays.asList(
            new Administrador(8, "Admin8", "A8", "admin8@example.com", false)
        );
        when(usuarioRepository.findByActivoFalseAndIsAdminFalse()).thenReturn(dummyList);

        List<Usuario> result = administradorServiceImpl.consultarUsuariosInactiveNoAdmins();
        assertEquals(dummyList, result);
        verify(usuarioRepository).findByActivoFalseAndIsAdminFalse();
    }

    // 9. Actualizar información de usuario (solo se actualizan los campos no nulos)
    @Test
    void testActualizarInformacionUsuario() {
        // Usuario inicial
        Usuario usuario = new Administrador(9, "OldName", "OldApellido", "old@example.com", true);
        when(usuarioRepository.findByIdInstitucional(9)).thenReturn(usuario);

        ActualizarUsuarioDTO dto = new ActualizarUsuarioDTO();
        dto.setIdInstitucional(9);
        dto.setNombre("NewName");
        // Dejamos apellido en null para que no se actualice
        dto.setApellido(null);
        dto.setCorreo("new@example.com");
        dto.setIsAdmin(false);

        administradorServiceImpl.actualizarInformacionUsuario(dto);

        ArgumentCaptor<Usuario> userCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(userCaptor.capture());
        Usuario updatedUser = userCaptor.getValue();

        assertEquals("NewName", updatedUser.getNombre());
        assertEquals("OldApellido", updatedUser.getApellido());
        assertEquals("new@example.com", updatedUser.getCorreoInstitucional());
        assertFalse(updatedUser.getIsAdmin());
    }

    // 10. Actualizar información: usuario no encontrado (no se guarda)
    @Test
    void testActualizarInformacionUsuario_NotFound() {
        when(usuarioRepository.findByIdInstitucional(10)).thenReturn(null);
        ActualizarUsuarioDTO dto = new ActualizarUsuarioDTO();
        dto.setIdInstitucional(10);
        administradorServiceImpl.actualizarInformacionUsuario(dto);
        verify(usuarioRepository, never()).save(any());
    }

    // 11. Deshabilitar usuario: se establece activo a false
    @Test
    void testDeshabilitarUsuario() {
        Usuario usuario = new Administrador(11, "Name", "Apellido", "test@example.com", true);
        when(usuarioRepository.findByIdInstitucional(11)).thenReturn(usuario);

        administradorServiceImpl.deshabilitarUsuario(11);

        assertFalse(usuario.isActivo());
        verify(usuarioRepository).save(usuario);
    }

    // 12. Habilitar usuario: se establece activo a true
    @Test
    void testHabilitarUsuario() {
        Usuario usuario = new Administrador(12, "Name", "Apellido", "test@example.com", false);
        when(usuarioRepository.findByIdInstitucional(12)).thenReturn(usuario);

        administradorServiceImpl.habilitarUsuario(12);

        assertTrue(usuario.isActivo());
        verify(usuarioRepository).save(usuario);
    }

    // 13. Agregar usuario: si isAdmin es true, se crea un Administrador
    @Test
    void testAgregarUsuario_Admin() {
        administradorServiceImpl.agregarUsuario(13, "Admin", "Apellido", "admin@example.com", true);
        ArgumentCaptor<Usuario> userCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(userCaptor.capture());
        Usuario savedUser = userCaptor.getValue();
        assertTrue(savedUser instanceof Administrador);
        assertEquals(13, savedUser.getIdInstitucional());
    }

    // 14. Agregar usuario: si isAdmin es false, se crea un Estandar
    @Test
    void testAgregarUsuario_Estandar() {
        administradorServiceImpl.agregarUsuario(14, "User", "Apellido", "user@example.com", false);
        ArgumentCaptor<Usuario> userCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(userCaptor.capture());
        Usuario savedUser = userCaptor.getValue();
        assertTrue(savedUser instanceof Estandar);
        assertEquals(14, savedUser.getIdInstitucional());
    }

    // 15. Hacer admin: se actualiza el usuario para que sea administrador
    @Test
    void testHacerAdmin() {
        Usuario usuario = new Estandar(15, "User", "Apellido", "user@example.com", true);
        when(usuarioRepository.findByIdInstitucional(15)).thenReturn(usuario);

        administradorServiceImpl.hacerAdmin(15);

        assertTrue(usuario.getIsAdmin());
        verify(usuarioRepository).save(usuario);
    }

    // 16. Quitar admin: se actualiza el usuario para que deje de ser administrador
    @Test
    void testQuitarAdmin() {
        Usuario usuario = new Administrador(16, "Admin", "Apellido", "admin@example.com", true);
        usuario.setAdmin(true);
        when(usuarioRepository.findByIdInstitucional(16)).thenReturn(usuario);

        administradorServiceImpl.quitarAdmin(16);

        assertFalse(usuario.getIsAdmin());
        verify(usuarioRepository).save(usuario);
    }
}
