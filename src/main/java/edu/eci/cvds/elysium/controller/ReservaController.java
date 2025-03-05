package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.service.ReservaService;
import edu.eci.cvds.elysium.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/consultarReservas")
    public List<ReservaModel> consultarReservas() {
        return reservaService.consultarReservas();
    }
    
    @GetMapping("/consultarReservasPorSalon")
    public List<ReservaModel> consultarReservasPorSalon(@RequestParam String idSalon) {
        return reservaService.consultarReservasPorSalon(idSalon);
    }

    @GetMapping("/consultarReservasPorFecha")
    public List<ReservaModel> consultarReservasPorFecha(@RequestParam LocalDate fecha) {
        return reservaService.consultarReservasPorFecha(fecha);
    }

    @GetMapping("/consultarReservasPorDiaSemana")
    public List<ReservaModel> consultarReservasPorDiaSemana(@RequestParam DiaSemanaModel diaSemana) {
        return reservaService.consultarReservasPorDiaSemana(diaSemana);
    }

    @GetMapping("/consultarReservasPorEstado")
    public List<ReservaModel> consultarReservasPorEstado(@RequestParam EstadoReservaModel estado) {
        return reservaService.consultarReservasPorEstado(estado);
    }

    @GetMapping("/consultarReservasPorDuracionBloque")
    public List<ReservaModel> consultarReservasPorDuracionBloque(@RequestParam boolean duracionBloque) {
        return reservaService.consultarReservasPorDuracionBloque(duracionBloque);
    }

    @GetMapping("/consultarReserva")
    public ReservaModel consultarReserva(@RequestParam String idReserva) {
        return reservaService.consultarReserva(idReserva);
    }

    @PostMapping("/crearReserva")
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDTO reservaDTO ) {
        reservaService.crearReserva(reservaDTO.getIdReserva(), reservaDTO.getFechaReserva(), reservaDTO.getDiaSemana(), reservaDTO.getProposito(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque());
        return ResponseEntity.ok("Reserva creada");
    }

    @PutMapping("/actualizarReserva")
    public ResponseEntity<String> actualizarReserva(@RequestBody ReservaDTO reservaDTO) {
        reservaService.actualizarReserva(reservaDTO.getIdReserva(), reservaDTO.getTipoCampo(), reservaDTO.getFechaReserva(), reservaDTO.getDiaSemana(), reservaDTO.getIdSalon(), reservaDTO.isDuracionBloque());
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

}
