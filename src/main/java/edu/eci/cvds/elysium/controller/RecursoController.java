package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.RecursoDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.service.RecursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recurso")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    /**
     * Retrieves a list of Recursos based on various optional filtering criteria.
     * @param nombre name
     * @param cantidad quantity
     * @param especificaciones specifications
     * @return ResponseEntity containing a list of Recursos that match the provided filters.
     */
    @GetMapping("")
    @Operation(summary = "Consultar recursos", description = "Endpoint para consultar recursos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recursos retornados correctamente"),
            @ApiResponse(responseCode = "404", description = "Recursos no encontrados")
    })
    public ResponseEntity<List<Recurso>> getRecursos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer cantidad,
            @RequestParam(required = false) List<String> especificaciones) {

        List<Recurso> recursos;

        if (nombre != null) {
            recursos = recursoService.consultarNombre(nombre);
        } else if (cantidad != null) {
            recursos = recursoService.consultarCantidad(cantidad);
        } else if (especificaciones != null) {
            recursos = recursoService.consultarEspecificaciones(especificaciones);
        } else {
            recursos = recursoService.consultarRecursos();
        }
        return ResponseEntity.ok(recursos);
    }

    /**
     * Endpoint para consultar un recurso por su identificador.
     * @param id Identificador del recurso a consultar (proveniente de la URL).
     * @return Recurso con el identificador dado.
     */
    @GetMapping("/{idRecurso}")
    @Operation(summary = "Consultar recurso", description = "Endpoint para consultar un recurso por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso retornado correctamente"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public ResponseEntity<Recurso> getRecurso(@PathVariable String idRecurso) {
        Recurso recurso = recursoService.consultarRecurso(idRecurso);
        return recurso != null ? ResponseEntity.ok(recurso) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para agregar un recurso.
     * @param recursoDTO Información del recurso a agregar.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @PostMapping("")
    @Operation(summary = "Agregar recurso", description = "Endpoint para agregar un recurso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso agregado correctamente"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public ResponseEntity<String> agregarRecurso(@RequestBody RecursoDTO recursoDTO){
        recursoService.agregarRecurso(recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para actualizar un recurso.
     * @param idRecurso Identificador del recurso a actualizar.
     * @param recursoDTO Información del recurso a actualizar.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @PatchMapping("/{idRecurso}")
    @Operation(summary = "Actualizar recurso", description = "Endpoint para actualizar un recurso por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public ResponseEntity<String> actualizarRecurso(@PathVariable String idRecurso,@RequestBody RecursoDTO recursoDTO){
        recursoService.actualizarRecurso(idRecurso,recursoDTO);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para eliminar un recurso.
     * @param id Identificador del recurso a eliminar.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @PutMapping("{id}/inactivo")
    @Operation(summary = "Deshabilitar recurso", description = "Endpoint para deshabilitar un recurso por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recurso deshabilitado correctamente"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public ResponseEntity<String> deshabilitarRecurso(@PathVariable String id){
        recursoService.deshabilitarRecurso(id);
        return ResponseEntity.noContent().build();
    }

}
