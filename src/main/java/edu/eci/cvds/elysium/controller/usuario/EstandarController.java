package edu.eci.cvds.elysium.controller.usuario;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@RestController
@RequestMapping("/api/estandar")
public class EstandarController extends UsuarioController {

    @Autowired
    private EstandarService estandarService;

    @PostMapping("/crearReserva")
    public Reserva crearReserva(@RequestParam int id,
                                @RequestParam String fechaInicio,  // formato "HH:mm"
                                @RequestParam String proposito,
                                @RequestParam String mnemonico) {
        LocalTime time = LocalTime.parse(fechaInicio);
        return estandarService.crearReserva(id, time, proposito, mnemonico);
    }
}
