package edu.eci.cvds.elysium.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    /**
     * Retrieves a list of Reservas based on various optional filtering criteria.
     * @param idSalon salon id
     * @param fecha date
     * @param hora hour
     * @param diaSemana day of the week
     * @param estado reservation status
     * @param duracionBloque block duration
     * @return ResponseEntity containing a list of Reservas that match the provided filters.
     */
    @GetMapping("")
    @Operation(summary = "Consultar reservas", description = "Endpoint para consultar reservas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas retornadas correctamente"),
            @ApiResponse(responseCode = "404", description = "Reservas no encontradas")
    })
    public ResponseEntity<List<Reserva>> getReservas(
            @RequestParam(required = false) String idSalon,
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) Double hora,
            @RequestParam(required = false) DiaSemana diaSemana,
            @RequestParam(required = false) EstadoReserva estado,
            @RequestParam(required = false) Boolean duracionBloque) {
        List<Reserva> reservas;
        
        // Si se reciben ambos filtros: idSalon y estado
        if (idSalon != null && estado != null) {
            reservas = reservaService.consultarReservasPorSalonAndEstado(idSalon, estado);
        } else if (idSalon != null) {
            reservas = reservaService.consultarReservasPorSalon(idSalon);
        } else if (fecha != null) {
            reservas = reservaService.consultarReservasPorFecha(fecha);
        } else if (hora != null) {
            reservas = reservaService.consultarReservasPorHora(hora);
        } else if (diaSemana != null) {
            reservas = reservaService.consultarReservasPorDiaSemana(diaSemana);
        } else if (estado != null) {
            reservas = reservaService.consultarReservasPorEstado(estado);
        } else if (duracionBloque != null) {
            reservas = reservaService.consultarReservasPorDuracionBloque(duracionBloque);
        } else {
            reservas = reservaService.consultarReservas();
        }
        return ResponseEntity.ok(reservas);
    }

    /**
     * Endpoint para consultar una reserva por su identificador.
     * @param idReserva reservation id
     * @return Reserva with the given id.
     */
    @GetMapping("")
    @Operation(summary = "Consultar reserva", description = "Endpoint para consultar una reserva por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva retornada correctamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public Reserva consultarReserva(@RequestParam String idReserva) {
        return reservaService.consultarReserva(idReserva);
    }

    /**
     * Endpoint para crear una reserva.
     * @param reservaDTO reservation information
     * @return ResponseEntity with a success message.
     */
    @PostMapping("")
    @Operation(summary = "Crear reserva", description = "Endpoint para crear una reserva.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva creada correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO reservaDTO ) {
        reservaService.crearReserva(reservaDTO.getFechaReserva(),reservaDTO.getHora(), reservaDTO.getDiaSemana(), reservaDTO.getProposito(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque(), reservaDTO.getPrioridad(), reservaDTO.getIdUsuario());
        return ResponseEntity.ok("Reserva creada");
    }

    /**
     * Endpoint para actualizar una reserva.
     * @param mnemonico reservation id
     * @param reservaDTO reservation information
     * @return ResponseEntity with a success message.
     */
    @PatchMapping("/{idReserva}")
    @Operation(summary = "Actualizar reserva", description = "Endpoint para actualizar una reserva por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<Void> actualizarReserva(@PathVariable String idReserva, @RequestBody ReservaDTO reservaDTO) {
        reservaService.actualizarReserva(idReserva, reservaDTO);
        return ResponseEntity.ok().build();
    }
    
    /**
     * Endpoint para deshabilitar una reserva.
     * @param idReserva reservation id
     * @return ResponseEntity with a success message.
     */
    @PutMapping("{idReserva}/inactivo")
    @Operation(summary = "Deshabilitar reserva", description = "Endpoint para deshabilitar una reserva por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva deshabilitada correctamente"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    public ResponseEntity<String> deshabilitarReserva(@PathVariable String idReserva) {
        reservaService.deshabilitarReserva(idReserva);
        return ResponseEntity.ok("Reserva deshabilitada");
    }
    
}
