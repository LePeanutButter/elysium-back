package edu.eci.cvds.elysium.service;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@SpringBootTest
public class EstandarServiceTest {

    @Autowired
    private EstandarService estandarService;

    private int testUserId;

    @BeforeEach
    public void setUp(){
        // Se crea un usuario estándar para la prueba.
        // Solo un usuario administrador puede crear usuarios estándar.
        //Administrador admin = new Administrador(0, "Admin", "User", "admin@mail.com", true);
        Estandar estandar = new Estandar(1, "Test", "User", "test@mail.com",false);
        
        testUserId = estandar.getIdInstitucional();
    }

    // @Test
    // public void testCrearReserva() {
    //     LocalTime fechaInicio = LocalTime.now();
    //     String proposito = "Reunión";
    //     String mnemonico = "ABC123";
        
    //     Reserva reserva = estandarService.crearReserva(testUserId, fechaInicio, proposito, mnemonico);
        
    //     assertNotNull(reserva, "La reserva no debe ser nula");
    //     assertEquals(mnemonico, reserva.getMnemonico(), "El mnemonico de la reserva debe coincidir");
    // }

    @Test
    public void testCrearReservaConPropositoNulo() {
        LocalTime fechaInicio = LocalTime.now();
        String proposito = null;
        String mnemonico = "ABC123";
        
        Reserva reserva = estandarService.crearReserva(testUserId, fechaInicio, proposito, mnemonico);
        
        assertNull(reserva, "Si el propósito es nulo, la reserva debe ser nula");
    }

    @Test
    public void testCrearReservaConMnemonicoNulo() {
        LocalTime fechaInicio = LocalTime.now();
        String proposito = "Reunión";
        String mnemonico = null;
        
        Reserva reserva = estandarService.crearReserva(testUserId, fechaInicio, proposito, mnemonico);
        
        assertNull(reserva, "Si el mnemonico es nulo, la reserva debe ser nula");
    }

    @Test
    public void testCrearReservaConUsuarioInvalido() {
        LocalTime fechaInicio = LocalTime.now();
        String proposito = "Reunión";
        String mnemonico = "ABC123";
        int idInstitucionalInvalido = 9999;
        
        Reserva reserva = estandarService.crearReserva(idInstitucionalInvalido, fechaInicio, proposito, mnemonico);
        
        assertNull(reserva, "Si el usuario no existe, la reserva debe ser nula");
    }
}
