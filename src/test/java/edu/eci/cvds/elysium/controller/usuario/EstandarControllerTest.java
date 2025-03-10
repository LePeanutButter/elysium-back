package edu.eci.cvds.elysium.controller.usuario;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

public class EstandarControllerTest {

    @Mock
    private EstandarService estandarService;

    @InjectMocks
    private EstandarController estandarController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearReserva() {
        int id = 1;
        String fechaInicio = "09:30";
        String proposito = "Reunión de equipo";
        String mnemonico = "MN123";
        LocalTime timeEsperado = LocalTime.parse(fechaInicio);
        
        // Se crea un dummy Reserva. Se asume que la clase Reserva posee un constructor que recibe:
        // (LocalTime, String, String, Usuario). Si se requiere otro constructor, ajústalo.
        Reserva reservaDummy = new Reserva(timeEsperado, proposito, mnemonico, null);
        
        when(estandarService.crearReserva(id, timeEsperado, proposito, mnemonico)).thenReturn(reservaDummy);
        
        // Se invoca el método del controlador
        Reserva resultado = estandarController.crearReserva(id, fechaInicio, proposito, mnemonico);
        
        // Se verifica que el resultado sea el esperado y que se haya llamado al servicio con los parámetros correctos.
        assertEquals(reservaDummy, resultado);
        verify(estandarService).crearReserva(id, timeEsperado, proposito, mnemonico);
    }
}
