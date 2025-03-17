package edu.eci.cvds.elysium.repository;

import edu.eci.cvds.elysium.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends MongoRepository<Recurso, String> {

    /**
     * Find a resource by its ID
     * @param id the ID of the resource
     * @return the resource with the given ID
     */
    Recurso findByid(String id);

    /**
     * Find all the resources
     * @return all the resources
     */
    @SuppressWarnings("null")
    List<Recurso> findAll();

    /**
     * Find all the resources by name
     * @param nombre the name of the resource
     * @return all the resources with the given name
     */
    List<Recurso> findByNombre(String nombre);

    /**
     * Find all the resources by amount
     * @param cantidad the amount of the resource
     * @return all the resources with the given amount
     */
    List<Recurso> findByCantidad(int cantidad);

    /**
     * Find all the resources by specifications
     * @param especificaciones the specifications of the resource
     * @return all the resources with the given specifications
     */
    List<Recurso> findByEspecificaciones(List<String> especificaciones);
}

