// package edu.eci.cvds.elysium.controller;

// import edu.eci.cvds.elysium.model.Usuario;
// import edu.eci.cvds.elysium.service.UsuarioService;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// // //indicamos que no queremos que se cargue la configuración de mongo
// // @WebMvcTest(controllers = UsuarioController.class, 
// //     excludeAutoConfiguration = {org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class, 
// //                                 org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class})
// @WebMvcTest(UsuarioController.class)

// public class UsuarioControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UsuarioService usuarioService;

//     @Test
//     @DisplayName("GET /api/usuario/{id} - Retorna el usuario")
//     public void testConsultarUsuario() throws Exception {
//         int userId = 1234;
//         // Se crea una instancia anónima de Usuario ya que es abstracta
//         Usuario usuario = new Usuario(userId, "Test", "User", "test@mail.com", true) {
//             @Override
//             public java.util.List<edu.eci.cvds.elysium.model.Salon> getSalones() {
//                 return java.util.Collections.emptyList();
//             }
//         };
//         when(usuarioService.consultarUsuario(userId)).thenReturn(usuario);

//         mockMvc.perform(get("/api/usuario/{id}", userId))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.idInstitucional").value(userId))
//                 .andExpect(jsonPath("$.nombre").value("Test"))
//                 .andExpect(jsonPath("$.apellido").value("User"))
//                 .andExpect(jsonPath("$.correoInstitucional").value("test@mail.com"))
//                 .andExpect(jsonPath("$.activo").value(true));
//     }
// }
