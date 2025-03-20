package edu.eci.cvds.elysium.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.Salon;

@Repository
public interface SalonRepository extends MongoRepository<Salon, String> {   
    
    /**
     * Find a salon by its mnemonic
     * @param mnemonico the mnemonic of the salon
     * @return the salon with the given mnemonic
     */
    Salon findByMnemonico(String mnemonico);
    
    /**
     * Find all the salons
     * @return all the salons
    */
    @SuppressWarnings("null")
    List<Salon> findAll();
    

    /**
     * Find all the salons that are active
     * @return all the salons that are active
     */
    List<Salon> findByActivoTrue();
    
    /**
     * Find all the salons that are inactive
     * @return all the salons that are inactive
     */
    List<Salon> findByActivoFalse();
    

    /**
     * Find all the salons that are available
     * @return all the salons that are available
     */
    List<Salon> findByDisponibleTrue();
    

    /**
     * Find all the salons that are not available
     * @return all the salons that are not available
     */
    List<Salon> findByDisponibleFalse();
    

    /**
     * Find all the salons that are active and available
     * @return all the salons that are active and available
     */
    List<Salon> findByActivoTrueAndDisponibleTrue();
    

    /**
     * Find all the salons that are active and not available
     * @return all the salons that are active and not available
     */
    List<Salon> findByActivoTrueAndDisponibleFalse();
    
    
    /**
     * Find all the salons that are inactive and available
     * @return all the salons that are inactive and available
     */
    List<Salon> findByNombreContainingIgnoreCase(String nombre);
    

    /**
     * Find all the salons that are inactive and not available
     * @return all the salons that are inactive and not available
     */
    List<Salon> findByUbicacionContainingIgnoreCase(String ubicacion);
    

    /**
     * Find all the salons that have a capacity greater than or equal to the given capacity
     * @param capacidad the capacity to compare
     * @return all the salons that have a capacity greater than or equal to the given capacity
     */
    List<Salon> findByCapacidadGreaterThanEqual(int capacidad);
    

    /**
     * Find all the salons that have a capacity less than or equal to the given capacity
     * @param capacidad the capacity to compare
     * @return all the salons that have a capacity less than or equal to the given capacity
     */
    List<Salon> findByCapacidadLessThanEqual(int capacidad);
    

    /**
     * Find all the salons that have the given name and location
     * @param nombre the name of the salon
     * @param ubicacion the location of the salon
     * @return all the salons that have the given name and location
     */
    List<Salon> findByActivoFalseAndDisponibleTrue();
    

    /**
     * Find all the salons that have the given name
     * @param nombre the name of the salon
     * @return all the salons that have the given name
     */
    List<Salon> findByActivoFalseAndDisponibleFalse();
    

    /**
     * Find all the salons that have the given location
     * @param ubicacion the location of the salon
     * @return all the salons that have the given location
     */
    List<Salon> findByNombreAndUbicacionContainingIgnoreCase(String nombre, String ubicacion);

    // Usuarios activos que son administradores y cuyo nombre contiene el texto dado
    boolean existsByMnemonico(String mnemonico);
}