package edu.eci.cvds.elysium.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.service.SalonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

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
    @SuppressWarnings("null")
    @GetMapping("")
    @Operation (summary = "Consultar salones", description = "Endpoint para consultar salones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salones retornados correctamente"),
            @ApiResponse(responseCode = "404", description = "Salones no encontrados")
    })
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

    
    /**
     * Retrieves a Salon based on its mnemonico.
     * @param mnemonico The mnemonico of the Salon to retrieve.
     * @return ResponseEntity containing the Salon with the provided mnemonico.
     */
    @GetMapping("/{mnemonico}")
    @Operation(summary = "Consultar salón", description = "Endpoint para consultar un salón por su mnemónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salón retornado correctamente"),
            @ApiResponse(responseCode = "404", description = "Salón no encontrado")
    })
    public ResponseEntity<Salon> getSalonByMnemonico(@PathVariable String mnemonico) {
        Salon salon = salonService.findByMnemonico(mnemonico);
        return salon != null ? ResponseEntity.ok(salon) : ResponseEntity.notFound().build();
    }
    
    
    /**
     * Retrieves the availability status of a Salon based on its mnemonico.
     * @param mnemonico The mnemonico of the Salon to check.
     * @return ResponseEntity containing a Boolean indicating whether the Salon is available.
     */
    @GetMapping("/{mnemonico}/disponible")
    @Operation(summary = "Consultar disponibilidad", description = "Endpoint para consultar la disponibilidad de un salón por su mnemónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad retornada correctamente"),
            @ApiResponse(responseCode = "404", description = "Salón no encontrado")
    })
    public ResponseEntity<Boolean> getDisponible(@PathVariable String mnemonico) {
        boolean disponible = salonService.getDisponible(mnemonico);
        return ResponseEntity.ok(disponible);
    }

    /**
     * Adds a new Salon to the system.
     * @param salonRequest The Salon to add.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PostMapping("")
    @Operation(summary = "Agregar salón", description = "Endpoint para agregar un salón.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salón agregado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Void> agregarSalon(@Valid @RequestBody SalonDTO salonRequest) {
        // Se espera que el JSON incluya: nombre, mnemonico, ubicacion, capacidad.
        // Se asume que el salón se crea por defecto como activo y disponible.
        salonService.agregarSalon(
                salonRequest.getName(),
                salonRequest.getMnemonic(),
                salonRequest.getDescription(),
                salonRequest.getLocation(),
                salonRequest.getCapacity(),
                salonRequest.getResources()
                
                );      

        return ResponseEntity.ok().build();
    }

    /**
     * Updates the information of a Salon based on its mnemonico.
     * @param mnemonico The mnemonico of the Salon to update.
     * @param salonDto The updated information of the Salon.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PatchMapping("/{mnemonico}")
    @Operation(summary = "Actualizar salón", description = "Endpoint para actualizar un salón por su mnemónico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salón actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Salón no encontrado")
    })
    public ResponseEntity<Void> actualizarSalon(@PathVariable String mnemonico , @RequestBody SalonDTO salonDto){
        salonService.actualizarSalon(mnemonico, salonDto);
        return ResponseEntity.ok().build();
    }

}
