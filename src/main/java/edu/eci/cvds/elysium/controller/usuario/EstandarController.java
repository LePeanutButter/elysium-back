package edu.eci.cvds.elysium.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@RestController
@RequestMapping("/api/estandar")
public class EstandarController {

    @Autowired
    private EstandarService estandarService;


    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable int id) {
        return estandarService.consultarUsuario(id);
    }

    //Falta considerar en donde va el mnemonico
    @PostMapping("{id}/reserva")
    public ResponseEntity<String> crearReserva(@PathVariable int id,@RequestBody
    ReservaDTO reservaDTO){
        estandarService.crearReserva(reservaDTO.getFechaReserva(), reservaDTO.getHora(),reservaDTO.getDiaSemana(), reservaDTO.getProposito(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque(), reservaDTO.getPrioridad(), id);
        return ResponseEntity.ok("Reserva creada");
        //TODO: Check if idUsuario is necessary in reservaDTO
    }    
}
