package edu.eci.cvds.elysium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.dto.RecursoDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.repository.RecursoRepository;

@Service
public class RecursoServiceImpl implements RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;


    /**
     * Consult a list of resources
     * @return List of resources
     */
    @Override
    public List<Recurso> consultarRecursos() {
        return recursoRepository.findAll();
    }


    /**
     * Consult a list of resources by name
     * @param nombre
     * @return List of resources
     */
    @Override
    public List<Recurso> consultarNombre(String nombre) {
        return recursoRepository.findByNombre(nombre);
    }

    /**
     * Consult a list of resources by quantity
     * @param cantidad
     * @return List of resources
     */
    @Override
    public List<Recurso> consultarCantidad(int cantidad) {
        return recursoRepository.findByCantidad(cantidad);
    }

    /**
     * Consult a list of resources by specifications
     * @param especificaciones
     * @return List of resources
     */
    @Override
    public List<Recurso> consultarEspecificaciones(List<String> especificaciones) {
        return recursoRepository.findByEspecificaciones(especificaciones);
    }

    /**
     * Consult a resource by id
     * @param id
     * @return Resource by id
     */
    @Override
    public Recurso consultarRecurso(String id) {
        return recursoRepository.findByid(id);
    }

    /**
     * Adds a new resource.
     * @param nombre the name of the resource
     * @param cantidad the amount of the resource
     * @param especificaciones the specifications of the resource
     */
    @Override
    public void agregarRecurso(String nombre, int cantidad, List<String> especificaciones) {
        Recurso recurso = new Recurso(nombre, cantidad, especificaciones);
        recursoRepository.save(recurso);
    }

    @Override
    public void actualizarRecurso(String id,RecursoDTO recursoDTO) {
        Recurso recurso = recursoRepository.findByid(id);
        if(recurso != null ){
            recurso.setNombre(recursoDTO.getNombre());
            recurso.setCantidad(recursoDTO.getCantidad());
            recurso.setEspecificaciones(recursoDTO.getEspecificaciones());
            boolean activo = true;
            recurso.setActivo(activo);
            recursoRepository.save(recurso);
        }
    }

    @Override
    public void deshabilitarRecurso(String id) {
        Recurso recurso = recursoRepository.findByid(id);
        if(recurso != null){
            recursoRepository.delete(recurso);
            boolean activo = false;
            recurso.setActivo(activo);
            recursoRepository.save(recurso);
        }
    }
    
}
