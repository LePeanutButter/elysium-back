package edu.eci.cvds.elysium.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import edu.eci.cvds.elysium.dto.ReservaDTO;
=======
import edu.eci.cvds.elysium.model.ReservaModel;
>>>>>>> c899553fd22ec883a338597135e1b0a523a2d98f
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@RestController
@RequestMapping("/api/estandar")
public class EstandarController extends UsuarioController {

    @Autowired
    private EstandarService estandarService;

<<<<<<< HEAD
    //Falta considerar en donde va el mnemonico
    @PostMapping("/estandar/crearReserva")
    public ResponseEntity<String> crearReserva(@RequestBody
    ReservaDTO reservaDTO){
        estandarService.crearReserva(reservaDTO.getIdReserva(), reservaDTO.getFechaReserva(), reservaDTO.getDiaSemana(), reservaDTO.getProposito(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque());
        return ResponseEntity.ok("Reserva creada");
    }
    
=======
    // @PostMapping("/crearReserva")
    // public ReservaModel crearReserva(@RequestParam int id,
    //                             @RequestParam String fechaInicio,  // formato "HH:mm"
    //                             @RequestParam String proposito,
    //                             @RequestParam String mnemonico) {
    //     LocalTime time = LocalTime.parse(fechaInicio);
    //     return estandarService.crearReserva(id, time, proposito, mnemonico);
    // }
>>>>>>> c899553fd22ec883a338597135e1b0a523a2d98f
}
