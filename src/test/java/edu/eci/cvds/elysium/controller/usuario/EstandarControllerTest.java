// package edu.eci.cvds.elysium.controller.usuario;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

// import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
// import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
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
//         List<Usuario> usuarios = Arrays.<Usuario>asList(new Usuario(), new Usuario());

//         when(administradorService.consultarUsuarios()).thenReturn(usuarios);

//         List<Usuario> resultado = administradorController.consultarUsuarios(null, null);
//         assertEquals(2, resultado.size());
//         verify(administradorService).consultarUsuarios();
//     }

//     // Test mínimo para el endpoint POST agregarUsuario
//     @Test
//     void testAgregarUsuario() {
//         UsuarioDTO usuarioDTO = new UsuarioDTO(1, "Juan", "Perez", "juan@example.com", true);
//         administradorController.agregarUsuario(usuarioDTO);
//         verify(administradorService).agregarUsuario(1, "Juan", "Perez", "juan@example.com", true);
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
