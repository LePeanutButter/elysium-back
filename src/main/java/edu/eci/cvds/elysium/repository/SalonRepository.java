package edu.eci.cvds.elysium.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.Salon;

@Repository
public interface SalonRepository extends MongoRepository<Salon, String> {   
    
    Salon findByMnemonico(String mnemonico);
    
    List<Salon> findAll();
    
    List<Salon> findByActivoTrue();
    
    List<Salon> findByActivoFalse();
    
    List<Salon> findByDisponibleTrue();
    
    List<Salon> findByDisponibleFalse();
    
    List<Salon> findByActivoTrueAndDisponibleTrue();
    
    List<Salon> findByActivoTrueAndDisponibleFalse();
    
    List<Salon> findByNombreContainingIgnoreCase(String nombre);
    
    List<Salon> findByUbicacionContainingIgnoreCase(String ubicacion);
    
    List<Salon> findByCapacidadGreaterThanEqual(int capacidad);
    
    List<Salon> findByCapacidadLessThanEqual(int capacidad);
    
    List<Salon> findByActivoFalseAndDisponibleTrue();
    
    List<Salon> findByActivoFalseAndDisponibleFalse();
    
    List<Salon> findByNombreAndUbicacionContainingIgnoreCase(String nombre, String ubicacion);
}