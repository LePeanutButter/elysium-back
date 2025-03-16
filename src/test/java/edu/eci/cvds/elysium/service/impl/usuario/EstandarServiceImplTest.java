// package edu.eci.cvds.elysium.service.impl.usuario;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.time.LocalTime;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import edu.eci.cvds.elysium.model.Reserva;
// import edu.eci.cvds.elysium.model.usuario.Administrador;
// import edu.eci.cvds.elysium.model.usuario.Estandar;
// import edu.eci.cvds.elysium.model.usuario.Usuario;
// import edu.eci.cvds.elysium.repository.UsuarioRepository;

// public class EstandarServiceImplTest {

//     @Mock
//     private UsuarioRepository usuarioRepository;

//     @InjectMocks
//     private EstandarServiceImpl estandarServiceImpl;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     // Caso 1: El usuario existe y es de tipo Estandar, se crea la reserva.
//     @Test
//     void testCrearReserva_EstandarExists() {
//         int idInstitucional = 1;
//         LocalTime fechaInicio = LocalTime.of(9, 30);
//         String proposito = "Reunión";
//         String mnemonico = "MN001";

//         // Creamos un spy de Estandar para simular el comportamiento de crearReserva
//         Estandar estandarUser = spy(new Estandar(idInstitucional, "User", "Lastname", "user@example.com", true));
//         Reserva dummyReserva = new Reserva(fechaInicio, proposito, mnemonico, estandarUser);
        
//         // Configuramos el spy para que retorne dummyReserva cuando se invoque crearReserva
//         doReturn(dummyReserva).when(estandarUser).crearReserva(fechaInicio, proposito, mnemonico);
        
//         // Configuramos el repositorio para que retorne nuestro usuario tipo Estandar
//         when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(estandarUser);
        
//         Reserva result = estandarServiceImpl.crearReserva(idInstitucional, fechaInicio, proposito, mnemonico);
//         assertNotNull(result);
//         assertEquals(dummyReserva, result);
        
//         verify(usuarioRepository).findByIdInstitucional(idInstitucional);
//         verify(estandarUser).crearReserva(fechaInicio, proposito, mnemonico);
//     }

//     // // Caso 2: El usuario no se encuentra en el repositorio.
//     // @Test
//     // void testCrearReserva_UserNotFound() {
//     //     int idInstitucional = 2;
//     //     LocalTime fechaInicio = LocalTime.of(10, 0);
//     //     String proposito = "Reunión";
//     //     String mnemonico = "MN002";

//     //     when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(null);
        
//     //     Reserva result = estandarServiceImpl.crearReserva(idInstitucional, fechaInicio, proposito, mnemonico);
//     //     assertNull(result);
//     //     verify(usuarioRepository).findByIdInstitucional(idInstitucional);
//     // }

//     // Caso 3: El usuario encontrado no es de tipo Estandar (por ejemplo, es un Administrador).
//     // @Test
//     // void testCrearReserva_UserNotEstandar() {
//     //     int idInstitucional = 3;
//     //     LocalTime fechaInicio = LocalTime.of(11, 0);
//     //     String proposito = "Reunión";
//     //     String mnemonico = "MN003";

//     //     Usuario notEstandar = new Administrador(idInstitucional, "Admin", "Lastname", "admin@example.com", true);
//     //     when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(notEstandar);

//     //     Reserva result = estandarServiceImpl.crearReserva(idInstitucional, fechaInicio, proposito, mnemonico);
//     //     assertNull(result);
//     //     verify(usuarioRepository).findByIdInstitucional(idInstitucional);
//     // }
// }
