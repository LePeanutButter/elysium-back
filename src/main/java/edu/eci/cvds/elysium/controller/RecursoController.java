package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.dto.RecursoDTO;
import edu.eci.cvds.elysium.model.RecursoModel;
import edu.eci.cvds.elysium.service.RecursoService;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recurso")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @GetMapping("/consultarRecursos")
    List<RecursoModel> consultarRecursos(){
        return recursoService.consultarRecursos();
    }

    @GetMapping("/consultarNombre")
    List<RecursoModel> consultarNombre(@RequestParam String nombre){
        return recursoService.consultarNombre(nombre);
    }

    @GetMapping("/consultarCantidad")
    List<RecursoModel> consultarCantidad(@RequestParam int cantidad){
        return recursoService.consultarCantidad(cantidad);
    }

    @GetMapping("/consultarEspecificaciones")
    List<RecursoModel> consultarEspecificaciones(@RequestParam List<String> especificaciones){
        return recursoService.consultarEspecificaciones(especificaciones);
    }

    @GetMapping("/consultarRecurso")
    RecursoModel consultarRecurso(@RequestParam ObjectId id){
        return recursoService.consultarRecurso(id);
    }

    @PostMapping("/agregarRecurso")
    public ResponseEntity<String> agregarRecurso(@RequestBody RecursoDTO recursoDTO){
        recursoService.agregarRecurso(recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
        return ResponseEntity.ok("Recurso agregado");
    }

    @PutMapping("/actualizarRecurso")
    public ResponseEntity<String> actualizarRecurso(@RequestBody RecursoDTO recursoDTO){
        recursoService.actualizarRecurso(recursoDTO.getId(),recursoDTO.getTipoCampo(),recursoDTO.getNombre(), recursoDTO.getCantidad(), recursoDTO.getEspecificaciones());
        return ResponseEntity.ok("Recurso actualizado");
    }

    @DeleteMapping("{id}/eliminarRecurso")
    public ResponseEntity<String> eliminarRecurso(@PathVariable ObjectId id){
        recursoService.eliminarRecurso(id);
        return ResponseEntity.ok("Recurso eliminado");
    }

}
