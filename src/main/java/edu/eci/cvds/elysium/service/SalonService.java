package edu.eci.cvds.elysium.service;

import java.util.List;

import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Salon;

public interface SalonService {
    
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
    List<Salon> findByActivoFalseAndDisponibleTrue();


    /**
     * Find all the salons that are inactive and not available
     * @return all the salons that are inactive and not available
     */
    List<Salon> findByActivoFalseAndDisponibleFalse();
    
    /**
     * Find all the salons that contain the given name
     * @param nombre the name to search
     * @return all the salons that contain the given name
     */
    List<Salon> findByNombreContainingIgnoreCase(String nombre);
    

    /**
     * Find all the salons that contain the given location
     * @param ubicacion the location to search
     * @return all the salons that contain the given location
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
    List<Salon> findByNombreAndUbicacionContainingIgnoreCase(String nombre, String ubicacion);

    /**
     * Add a new salon
     * @param nombre the name of the salon
     * @param mnemonico the mnemonic of the salon
     * @param ubicacion the location of the salon
     * @param capacidad the capacity of the salon
     * @param recursos the resources of the salon
     */
    void agregarSalon(String nombre, String mnemonico,String descripcion, String ubicacion, int capacidad, List<Recurso> recursos);

    /**
     * Disable a salon
     * @param mnemonico the mnemonic of the salon
     */
    void deshabilitarSalon(String mnemonico);

    /**
     * Enable a salon
     * @param mnemonico the mnemonic of the salon
     */
    void habilitarSalon(String mnemonico);

    /**
     * Update a salon
     * @param mnemonico the mnemonic of the salon
     * @param dto the new information of the salon
     */
    boolean getActivo(String mnemonico);  


    /**
     * Update a salon
     * @param mnemonico the mnemonic of the salon
     * @param dto the new information of the salon
     */
    void actualizarSalon(String mnemonico, SalonDTO dto);

    /**
     * Update a salon
     * @param mnemonico the mnemonic of the salon
     * @param dto the new information of the salon
     */
    boolean getDisponible(String mnemonico);  

    /**
     * Update a salon
     * @param mnemonico the mnemonic of the salon
     * @param dto the new information of the salon
     */
    boolean setDisponible(String mnemonico);

     /**
     * Update a salon
     * @param mnemonico the mnemonic of the salon
     * @param dto the new information of the salon
     */
    boolean setNoDisponible(String mnemonico);  
    

}   
