package edu.eci.cvds.elysium.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;
=======

>>>>>>> c899553fd22ec883a338597135e1b0a523a2d98f

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("")
    public ResponseEntity<List<ReservaModel>> getReservas(
            @RequestParam(required = false) String idSalon,
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) double hora,
            @RequestParam(required = false) DiaSemanaModel diaSemana,
            @RequestParam(required = false) EstadoReservaModel estado,
            @RequestParam(required = false) Boolean duracionBloque) {
        List<ReservaModel> reservas;
        
        // Si se reciben ambos filtros: idSalon y estado
        if (idSalon != null && estado != null) {
            reservas = reservaService.consultarReservasPorSalonAndEstado(idSalon, estado);
        } else if (idSalon != null) {
            reservas = reservaService.consultarReservasPorSalon(idSalon);
        } else if (fecha != null) {
            reservas = reservaService.consultarReservasPorFecha(fecha);
        } else if (hora != 0) {
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

    @GetMapping("/consultarReserva")
    public ReservaModel consultarReserva(@RequestParam String idReserva) {
        return reservaService.consultarReserva(idReserva);
    }

    @PostMapping("/crearReserva")
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO reservaDTO ) {
        reservaService.crearReserva(reservaDTO.getIdReserva(), reservaDTO.getFechaReserva(),reservaDTO.getHora(), reservaDTO.getDiaSemana(), reservaDTO.getProposito(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque(),reservaDTO.getPrioridad());
        return ResponseEntity.ok("Reserva creada");
    }

    @PutMapping("/actualizarReserva")
    public ResponseEntity<String> actualizarReserva(@RequestBody ReservaDTO reservaDTO) {
        reservaService.actualizarReserva(reservaDTO.getIdReserva(), reservaDTO.getTipoCampo(), reservaDTO.getFechaReserva(),reservaDTO.getHora(), reservaDTO.getDiaSemana(), reservaDTO.getIdSalon(), reservaDTO.isDuracionBloque(),reservaDTO.getPrioridad());
        return ResponseEntity.ok("Reserva actualizada");
    }

    @DeleteMapping("{idReserva}/deleteReserva")
    public ResponseEntity<String> deleteReserva(@PathVariable String idReserva) {
        reservaService.deleteReserva(idReserva);
        return ResponseEntity.ok("Reserva eliminada");
    }

    @PutMapping("{idReserva}/cancelReserva")
    public ResponseEntity<String> cancelReserva(@PathVariable String idReserva) {
        reservaService.cancelReserva(idReserva);
        return ResponseEntity.ok("Reserva cancelada");
    }
    
    @PutMapping("{idReserva}/rechazarReserva")
    public ResponseEntity<String> rechazarReserva(@PathVariable String idReserva) {
        reservaService.rechazarReserva(idReserva);
        return ResponseEntity.ok("Reserva rechazada");
    }


<<<<<<< HEAD
    
=======
    @PostMapping("/generarReservas")
    public ResponseEntity<String> generarReservas() {
        reservaService.generarReservasAleatorias();
        return ResponseEntity.ok("Reservas aleatorias generadas correctamente.");
    }
>>>>>>> e63ef6c391d7ee4c24dfeafedc418ca574933724
}
