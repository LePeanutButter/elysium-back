package edu.eci.cvds.elysium.controller;

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

import edu.eci.cvds.elysium.dto.salon.ActualizarSalonDTO;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.service.SalonService;

@RestController
@RequestMapping("/api/salones")
public class SalonController {

    @Autowired
    private SalonService salonService;

    /**
     * Retrieves a list of Salones based on various optional filtering criteria.
     * 
     * This endpoint supports multiple filters that can be combined to refine the
     * search results.
     * The possible filter combinations include:
     * - activo and disponible
     * - activo
     * - disponible
     * - nombre and ubicacion
     * - nombre
     * - ubicacion
     * - capacidadMin
     * - capacidadMax
     * 
     * There are at least 32 possible filter combinations.
     * 
     * @param activo       Optional Boolean filter to retrieve salones based on
     *                     their active status.
     * @param disponible   Optional Boolean filter to retrieve salones based on
     *                     their availability status.
     * @param nombre       Optional String filter to retrieve salones based on their
     *                     name.
     * @param ubicacion    Optional String filter to retrieve salones based on their
     *                     location.
     * @param capacidadMin Optional Integer filter to retrieve salones with a
     *                     minimum capacity.
     * @param capacidadMax Optional Integer filter to retrieve salones with a
     *                     maximum capacity.
     * @return ResponseEntity containing a list of Salones that match the provided
     *         filters.
     */
    @GetMapping("")
    public ResponseEntity<List<Salon>> getSalones(
            @RequestParam(required = false) Boolean activo,
            @RequestParam(required = false) Boolean disponible,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) Integer capacidadMin,
            @RequestParam(required = false) Integer capacidadMax) {
        List<Salon> salones;

        if (activo != null && disponible != null) {
            if (activo && disponible) {
                salones = salonService.findByActivoTrueAndDisponibleTrue();
            } else if (activo && !disponible) {
                salones = salonService.findByActivoTrueAndDisponibleFalse();
            } else if (!activo && disponible) {
                salones = salonService.findByActivoFalseAndDisponibleTrue();
            } else {
                salones = salonService.findByActivoFalseAndDisponibleFalse();
            }
        } else if (activo != null) {
            salones = activo ? salonService.findByActivoTrue() : salonService.findByActivoFalse();
        } else if (disponible != null) {
            salones = disponible ? salonService.findByDisponibleTrue() : salonService.findByDisponibleFalse();
        } else if (nombre != null && ubicacion != null) {
            salones = salonService.findByNombreAndUbicacionContainingIgnoreCase(nombre, ubicacion);
        } else if (nombre != null) {
            salones = salonService.findByNombreContainingIgnoreCase(nombre);
        } else if (ubicacion != null) {
            salones = salonService.findByUbicacionContainingIgnoreCase(ubicacion);
        } else if (capacidadMin != null) {
            salones = salonService.findByCapacidadGreaterThanEqual(capacidadMin);
        } else if (capacidadMax != null) {
            salones = salonService.findByCapacidadLessThanEqual(capacidadMax);
        } else {
            salones = salonService.findAll();
        }
        return ResponseEntity.ok(salones);
    }

    // Consulta un salón específico por su mnemonico
    @GetMapping("/{mnemonico}")
    public ResponseEntity<Salon> getSalonByMnemonico(@PathVariable String mnemonico) {
        Salon salon = salonService.findByMnemonico(mnemonico);
        return salon != null ? ResponseEntity.ok(salon) : ResponseEntity.notFound().build();
    }

    // Agregar un nuevo salón
    @PostMapping("")
    public ResponseEntity<Void> agregarSalon(@RequestBody Salon salonRequest) {
        // Se espera que el JSON incluya: nombre, mnemonico, ubicacion, capacidad.
        // Se asume que el salón se crea por defecto como activo y disponible.
        salonService.agregarSalon(
                salonRequest.getNombre(),
                salonRequest.getMnemonico(),
                salonRequest.getUbicacion(),
                salonRequest.getCapacidad());

        return ResponseEntity.ok().build();
    }

    // Deshabilitar un salón (marcarlo como inactivo)
    @PutMapping("/{mnemonico}/deshabilitar")
    public ResponseEntity<Void> deshabilitarSalon(@PathVariable String mnemonico) {
        salonService.deshabilitarSalon(mnemonico);
        return ResponseEntity.noContent().build();
    }

    // Habilitar un salón (marcarlo como activo)
    @PutMapping("/{mnemonico}/habilitar")
    public ResponseEntity<Void> habilitarSalon(@PathVariable String mnemonico) {
        salonService.habilitarSalon(mnemonico);
        return ResponseEntity.noContent().build();
    }

    // Consultar el estado 'disponible' del salón
    @GetMapping("/{mnemonico}/disponible")
    public ResponseEntity<Boolean> getDisponible(@PathVariable String mnemonico) {
        boolean disponible = salonService.getDisponible(mnemonico);
        return ResponseEntity.ok(disponible);
    }

    // Marcar el salón como disponible
    @PutMapping("/{mnemonico}/disponible")
    public ResponseEntity<Void> setDisponible(@PathVariable String mnemonico) {
        boolean actualizado = salonService.setDisponible(mnemonico);
        return actualizado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Marcar el salón como no disponible
    @PutMapping("/{mnemonico}/noDisponible")
    public ResponseEntity<Void> setNoDisponible(@PathVariable String mnemonico) {
        boolean actualizado = salonService.setNoDisponible(mnemonico);
        return actualizado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PatchMapping("/{mnemonico}")
    public ResponseEntity<Void> actualizarSalon(@PathVariable String mnemonico, @RequestBody ActualizarSalonDTO dto) {
        // El servicio actualizará solo los campos no nulos.
        salonService.actualizarSalon(mnemonico, dto);
        return ResponseEntity.noContent().build();
    }

}
