// package edu.eci.cvds.elysium.service.impl.usuario;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.mockito.MockitoAnnotations;

// import edu.eci.cvds.elysium.model.usuario.Administrador;
// import edu.eci.cvds.elysium.model.usuario.Usuario;
// import edu.eci.cvds.elysium.repository.UsuarioRepository;

// public class UsuarioServiceImplTest {

//     @Mock
//     private UsuarioRepository usuarioRepository;

//     @InjectMocks
//     private UsuarioServiceImpl usuarioServiceImpl;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     // Caso en el que el usuario existe
//     @Test
//     void testConsultarUsuarioFound() {
//         int id = 1;
//         // Usamos Administrador como instancia concreta de Usuario
//         Usuario dummyUsuario = new Administrador(id, "Admin", "Apellido", "admin@example.com", true);
//         when(usuarioRepository.findByIdInstitucional(id)).thenReturn(dummyUsuario);

//         Usuario resultado = usuarioServiceImpl.consultarUsuario(id);
//         assertNotNull(resultado);
//         assertEquals(id, resultado.getIdInstitucional());
//         verify(usuarioRepository).findByIdInstitucional(id);
//     }

//     // Caso en el que el usuario no existe
//     @Test
//     void testConsultarUsuarioNotFound() {
//         int id = 2;
//         when(usuarioRepository.findByIdInstitucional(id)).thenReturn(null);

//         Usuario resultado = usuarioServiceImpl.consultarUsuario(id);
//         assertNull(resultado);
//         verify(usuarioRepository).findByIdInstitucional(id);
//     }
// }
