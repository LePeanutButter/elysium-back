// package edu.eci.cvds.elysium.service;

// import java.util.List;

// import org.junit.jupiter.api.AfterEach;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import edu.eci.cvds.elysium.model.Administrador;
// import edu.eci.cvds.elysium.model.Usuario;

// @SpringBootTest
// public class AdministradorServiceTest {

//     @Autowired
//     private AdministradorService administradorService;

//     private Administrador admin;

//     @BeforeEach
//     public void setUp(){
//         // Creamos un administrador de prueba utilizando el constructor
//         admin = new Administrador(1111, "Admin", "User", "admin@mail.com", true);
//         admin = (Administrador) administradorService.createUser(admin);
//     }

//     @AfterEach
//     public void tearDown(){
//         // Eliminamos el administrador de prueba
//         if(admin != null){
//             //administradorService.deleteUser(admin.getIdInstitucional());
//         }
//     }

//     @Test
//     public void testAgregarUsuario() {
//         // El administrador agrega un usuario (se crea como Estandar)
//         int idInstitucional = 2222;
//         administradorService.agregarUsuario(idInstitucional, "Estandar", "User", "estandar@mail.com");
//         Usuario u = administradorService.consultarUsuario(idInstitucional);
//         assertNotNull(u, "El usuario agregado no debe ser nulo");
//         assertEquals("Estandar", u.getNombre(), "El nombre del usuario debe ser 'Estandar'");
        
//         // Limpieza
//         //administradorService.deleteUser(idInstitucional);
//     }

//     @Test
//     public void testActualizarInformacionUsuario() {
//         // Primero agregamos un usuario y luego actualizamos su nombre
//         int idInstitucional = 3333;
//         administradorService.agregarUsuario(idInstitucional, "Juan", "Perez", "juan@mail.com");
//         administradorService.actualizarInformacionUsuario(idInstitucional, 'n', "JuanUpdated");
//         Usuario u = administradorService.consultarUsuario(idInstitucional);
//         assertNotNull(u, "El usuario debe existir");
//         assertEquals("JuanUpdated", u.getNombre(), "El nombre del usuario debe actualizarse");
        
//         // Limpieza
//         //administradorService.deleteUser(idInstitucional);
//     }

//     @Test
//     public void testDeshabilitarUsuario() {
//         int idInstitucional = 4444;
//         administradorService.agregarUsuario(idInstitucional, "Pedro", "Lopez", "pedro@mail.com");
//         administradorService.deshabilitarUsuario(idInstitucional);
//         Usuario u = administradorService.consultarUsuario(idInstitucional);
//         assertNotNull(u, "El usuario debe existir");
//         assertFalse(u.isActivo(), "El usuario debe estar deshabilitado");
        
//         // Limpieza
//         //administradorService.deleteUser(idInstitucional);
//     }

//     @Test
//     public void testAñadirSalon() {
//         // El administrador añade un salón a su lista
//         administradorService.añadirSalon(admin.getIdInstitucional(), "Salon 1", "Ubicación 1", 30);
//         List<?> salones = admin.getSalones();
//         assertNotNull(salones, "La lista de salones no debe ser nula");
//         assertFalse(salones.isEmpty(), "La lista de salones debe contener al menos un elemento");
//     }

//     @Test
//     public void testConsultarUsuarios() {
//         // Agregamos dos usuarios y luego consultamos la lista completa
//         administradorService.agregarUsuario(5555, "User1", "A", "user1@mail.com");
//         administradorService.agregarUsuario(6666, "User2", "B", "user2@mail.com");
//         List<Usuario> usuarios = administradorService.consultarUsuarios();
//         boolean found1 = usuarios.stream().anyMatch(u -> u.getIdInstitucional() == 5555);
//         boolean found2 = usuarios.stream().anyMatch(u -> u.getIdInstitucional() == 6666);
//         assertTrue(found1 && found2, "Los usuarios agregados deben estar en la lista");
        
//         // Limpieza
//         //administradorService.deleteUser(5555);
//         //administradorService.deleteUser(6666);
//     }
// }
