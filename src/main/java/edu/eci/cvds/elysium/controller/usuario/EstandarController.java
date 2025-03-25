package edu.eci.cvds.elysium.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.EstandarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/estandar")
public class EstandarController {

    @Autowired
    private EstandarService estandarService;

    /**
     * Endpoint para consultar un usuario por su identificador.
     * 
     * @param id Identificador del usuario a consultar (proveniente de la URL).
     * @return Usuario con el identificador dado.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consultar usuario", description = "Endpoint para consultar un usuario por su identificador.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario retornado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public Usuario consultarUsuario(@PathVariable int id) {
        return estandarService.consultarUsuario(id);
    }

    /**
     * Endpoint para crear una reserva.
     * 
     * @param id Identificador del usuario que realiza la reserva.
     * @param reservaDTO Información de la reserva a crear.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @Operation(summary = "Crear reserva", description = "Endpoint para crear una reserva.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva creada correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping("{id}/reserva")
    public ResponseEntity<String> crearReserva(@PathVariable int id,@RequestBody
    ReservaDTO reservaDTO){
        estandarService.crearReserva(reservaDTO.getFechaReserva(), reservaDTO.getHora(),reservaDTO.getDiaSemana(), reservaDTO.getProposito(),reservaDTO.getMateria(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque(), reservaDTO.getPrioridad(), id);
        return ResponseEntity.ok("Reserva creada");
    }   
    
    /**
     * Endpoint para listar las reservas de un usuario.
     * 
     * @param id Identificador del usuario a consultar.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @Operation(summary = "Listar reservas", description = "Endpoint para listar las reservas de un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas listadas"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("{id}/reserva")
    public List<Reserva> listarReservas(@PathVariable int id){
        return estandarService.listarReservas(id);        
    }
}
