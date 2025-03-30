// package edu.eci.cvds.elysium.controller.usuario;

// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

// import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
// import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
// import edu.eci.cvds.elysium.model.usuario.Administrador;
// import edu.eci.cvds.elysium.model.usuario.Usuario;
// import edu.eci.cvds.elysium.service.usuario.AdministradorService;

// class AdministradorControllerTest {

//     @Mock
//     private AdministradorService administradorService;

//     @InjectMocks
//     private AdministradorController administradorController;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     // Test mínimo para el endpoint GET sin filtros
//     @Test
//     void testConsultarUsuariosSinFiltros() {
//         // Como Usuario es abstracto, creamos instancias concretas de Administrador.
//         Administrador admin1 = new Administrador(1, "Admin1", "Apellido1", "admin1@example.com", true);
//         Administrador admin2 = new Administrador(2, "Admin2", "Apellido2", "admin2@example.com", true);
//         List<Usuario> usuarios = Arrays.asList(admin1, admin2);

//         when(administradorService.consultarUsuarios()).thenReturn(usuarios);

//         List<Usuario> resultado = administradorController.consultarUsuarios(null, null);
//         assertEquals(2, resultado.size());
//         verify(administradorService).consultarUsuarios();
//     }

//     // 2. Solo se filtra por estado (activo = true)
//     @Test
//     void testConsultarUsuariosSoloActivoTrue() {
//         List<Usuario> usuariosActivos = Arrays.asList(
//                 new Administrador(3, "Admin3", "Apellido3", "admin3@example.com", true));
//         when(administradorService.consultarUsuariosActivos()).thenReturn(usuariosActivos);

//         List<Usuario> resultado = administradorController.consultarUsuarios(true, null);
//         assertEquals(usuariosActivos, resultado);
//         verify(administradorService).consultarUsuariosActivos();
//     }

//     // 3. Solo se filtra por estado (activo = false)
//     @Test
//     void testConsultarUsuariosSoloActivoFalse() {
//         List<Usuario> usuariosInactivos = Arrays.asList(
//                 new Administrador(4, "Admin4", "Apellido4", "admin4@example.com", false));
//         when(administradorService.consultarUsuariosInactivos()).thenReturn(usuariosInactivos);

//         List<Usuario> resultado = administradorController.consultarUsuarios(false, null);
//         assertEquals(usuariosInactivos, resultado);
//         verify(administradorService).consultarUsuariosInactivos();
//     }

//     // 4. Solo se filtra por rol (isAdmin = true)
//     @Test
//     void testConsultarUsuariosSoloIsAdminTrue() {
//         List<Usuario> usuariosAdmins = Arrays.asList(
//                 new Administrador(5, "Admin5", "Apellido5", "admin5@example.com", true));
//         when(administradorService.consultarUsuariosAdmins()).thenReturn(usuariosAdmins);

//         List<Usuario> resultado = administradorController.consultarUsuarios(null, true);
//         assertEquals(usuariosAdmins, resultado);
//         verify(administradorService).consultarUsuariosAdmins();
//     }

//     // 5. Solo se filtra por rol (isAdmin = false)
//     @Test
//     void testConsultarUsuariosSoloIsAdminFalse() {
//         List<Usuario> usuariosActiveNoAdmins = Arrays.asList(
//                 new Administrador(6, "Admin6", "Apellido6", "admin6@example.com", true));
//         when(administradorService.consultarUsuariosActiveNoAdmins()).thenReturn(usuariosActiveNoAdmins);

//         List<Usuario> resultado = administradorController.consultarUsuarios(null, false);
//         assertEquals(usuariosActiveNoAdmins, resultado);
//         verify(administradorService).consultarUsuariosActiveNoAdmins();
//     }

//     // 6. Se filtra por ambos: activo = true y isAdmin = true
//     @Test
//     void testConsultarUsuariosActivoTrueIsAdminTrue() {
//         List<Usuario> usuariosActiveAdmins = Arrays.asList(
//                 new Administrador(7, "Admin7", "Apellido7", "admin7@example.com", true));
//         when(administradorService.consultarUsuariosActiveAdmins()).thenReturn(usuariosActiveAdmins);

//         List<Usuario> resultado = administradorController.consultarUsuarios(true, true);
//         assertEquals(usuariosActiveAdmins, resultado);
//         verify(administradorService).consultarUsuariosActiveAdmins();
//     }

//     // 7. Se filtra por ambos: activo = true y isAdmin = false
//     @Test
//     void testConsultarUsuariosActivoTrueIsAdminFalse() {
//         List<Usuario> usuariosActiveNoAdmins = Arrays.asList(
//                 new Administrador(8, "Admin8", "Apellido8", "admin8@example.com", true));
//         when(administradorService.consultarUsuariosActiveNoAdmins()).thenReturn(usuariosActiveNoAdmins);

//         List<Usuario> resultado = administradorController.consultarUsuarios(true, false);
//         assertEquals(usuariosActiveNoAdmins, resultado);
//         verify(administradorService).consultarUsuariosActiveNoAdmins();
//     }

//     // 8. Se filtra por ambos: activo = false y isAdmin = true
//     @Test
//     void testConsultarUsuariosActivoFalseIsAdminTrue() {
//         List<Usuario> usuariosInactiveAdmins = Arrays.asList(
//                 new Administrador(9, "Admin9", "Apellido9", "admin9@example.com", false));
//         when(administradorService.consultarUsuariosInactiveAdmins()).thenReturn(usuariosInactiveAdmins);

//         List<Usuario> resultado = administradorController.consultarUsuarios(false, true);
//         assertEquals(usuariosInactiveAdmins, resultado);
//         verify(administradorService).consultarUsuariosInactiveAdmins();
//     }

//     // 9. Se filtra por ambos: activo = false y isAdmin = false
//     @Test
//     void testConsultarUsuariosActivoFalseIsAdminFalse() {
//         List<Usuario> usuariosInactiveNoAdmins = Arrays.asList(
//                 new Administrador(10, "Admin10", "Apellido10", "admin10@example.com", false));
//         when(administradorService.consultarUsuariosInactiveNoAdmins()).thenReturn(usuariosInactiveNoAdmins);

//         List<Usuario> resultado = administradorController.consultarUsuarios(false, false);
//         assertEquals(usuariosInactiveNoAdmins, resultado);
//         verify(administradorService).consultarUsuariosInactiveNoAdmins();
//     }

//     // Test mínimo para el endpoint POST agregarUsuario
//     @Test
//     void testAgregarUsuario() {
//         UsuarioDTO usuarioDTO = new UsuarioDTO(3, "Juan", "Perez", "juan@example.com", true);
//         administradorController.agregarUsuario(usuarioDTO);
//         verify(administradorService).agregarUsuario(3, "Juan", "Perez", "juan@example.com", true);
//     }

//     // Test mínimo para el endpoint PATCH actualizarInformacionUsuario
//     @Test
//     void testActualizarInformacionUsuario() {
//         ActualizarUsuarioDTO actualizarUsuarioDTO = new ActualizarUsuarioDTO();
//         administradorController.actualizarInformacionUsuario(1, actualizarUsuarioDTO);
//         verify(administradorService).actualizarInformacionUsuario(actualizarUsuarioDTO);
//     }

//     // Test mínimo para el endpoint PUT deshabilitarUsuario
//     @Test
//     void testDeshabilitarUsuario() {
//         ResponseEntity<String> response = administradorController.deshabilitarUsuario(1);
//         assertEquals("Usuario deshabilitado exitosamente", response.getBody());
//         verify(administradorService).deshabilitarUsuario(1);
//     }

//     // Test mínimo para el endpoint PUT habilitarUsuario
//     @Test
//     void testHabilitarUsuario() {
//         ResponseEntity<String> response = administradorController.habilitarUsuario(1);
//         assertEquals("Usuario habilitado exitosamente", response.getBody());
//         verify(administradorService).habilitarUsuario(1);
//     }

//     // Test mínimo para el endpoint PUT hacerAdmin
//     @Test
//     void testHacerAdmin() {
//         ResponseEntity<String> response = administradorController.hacerAdmin(1);
//         assertEquals("Usuario ahora es administrador", response.getBody());
//         verify(administradorService).hacerAdmin(1);
//     }

//     // Test mínimo para el endpoint PUT quitarAdmin
//     @Test
//     void testQuitarAdmin() {
//         ResponseEntity<String> response = administradorController.quitarAdmin(1);
//         assertEquals("Usuario ya no es administrador", response.getBody());
//         verify(administradorService).quitarAdmin(1);
//     }
// }
