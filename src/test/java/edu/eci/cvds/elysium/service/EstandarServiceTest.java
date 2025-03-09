// package edu.eci.cvds.elysium.service;

// import java.time.LocalTime;

// import org.junit.jupiter.api.AfterEach;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test; 
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import edu.eci.cvds.elysium.model.Estandar;
// import edu.eci.cvds.elysium.model.Reserva;

// @SpringBootTest
// public class EstandarServiceTest {

//     @Autowired
//     private EstandarService estandarService;

//     private Estandar estandar;

//     @BeforeEach
//     public void setUp(){
//         // Creamos un usuario estandar de prueba utilizando el constructor parametrizado
//         estandar = new Estandar(7777, "Estandar", "User", "estandar@mail.com", true);
//         estandar = (Estandar) estandarService.createUser(estandar);
//     }

//     @AfterEach
//     public void tearDown(){
//         if(estandar != null){
//             //estandarService.deleteUser(estandar.getIdInstitucional());
//         }
//     }

//     @Test
//     public void testCrearReserva() {
//         LocalTime fechaInicio = LocalTime.now();
//         String proposito = "Reunión";
//         String mnemonico = "ABC123";
//         Reserva reserva = estandarService.crearReserva(estandar.getIdInstitucional(), fechaInicio, proposito, mnemonico);
//         assertNotNull(reserva, "La reserva no debe ser nula");
//         assertEquals(proposito, reserva.getProposito(), "El proposito de la reserva debe coincidir");
//         assertEquals(mnemonico, reserva.getMnemonico(), "El mnemonico de la reserva debe coincidir");
//     }
// }
