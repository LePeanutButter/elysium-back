package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.dto.RecursoDTO;
import edu.eci.cvds.elysium.model.Recurso;

import java.util.List;


public interface RecursoService {

    /**
     * Consult a list of resources
     * @return List of resources
     */
    List<Recurso> consultarRecursos();

    /**
     * Consult a list of resources by name
     * @param nombre Name of the resource
     * @return List of resources
     */
    List<Recurso> consultarNombre(String nombre);

    /**
     * Consult a list of resources by quantity
     * @param cantidad Quantity of the resource
     * @return List of resources
     */
    List<Recurso> consultarCantidad(int cantidad);

    /**
     * Consult a list of resources by specifications
     * @param especificaciones Specifications of the resource
     * @return List of resources
     */
    List<Recurso> consultarEspecificaciones(List<String> especificaciones);

    /**
     * Consult a resource by id
     * @param id Id of the resource
     * @return Resource
     */
    Recurso consultarRecurso(String id);

    /**
     * Add a resource
     * @param nombre Name of the resource
     * @param cantidad Quantity of the resource
     * @param especificaciones Specifications of the resource
     */
    void agregarRecurso(String nombre, int cantidad, List<String> especificaciones);

    /**
     * Update a resource
     * @param id Id of the resource
     * @param tipoCampo Type of field
     * @param nombre Name of the resource
     * @param cantidad Quantity of the resource
     * @param especificaciones Specifications of the resource
     */
    void actualizarRecurso(String id, RecursoDTO recursodDto);

    /**
     * Disable a resource
     * @param id Id of the resource
     */
    void deshabilitarRecurso(String id);
} 


