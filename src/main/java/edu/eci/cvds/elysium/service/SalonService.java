package edu.eci.cvds.elysium.service;

import java.util.ArrayList;
import java.util.List;

import edu.eci.cvds.elysium.dto.salon.SalonDTO;
import edu.eci.cvds.elysium.model.Salon;

public interface SalonService {
    
    Salon findByMnemonico(String mnemonico);
    
    List<Salon> findAll();
    
    List<Salon> findByActivoTrue();
    
    List<Salon> findByActivoFalse();
    
    List<Salon> findByDisponibleTrue();
    
    List<Salon> findByDisponibleFalse();
    
    List<Salon> findByActivoTrueAndDisponibleTrue();
    
    List<Salon> findByActivoTrueAndDisponibleFalse();

    List<Salon> findByActivoFalseAndDisponibleTrue();

    List<Salon> findByActivoFalseAndDisponibleFalse();
    
    List<Salon> findByNombreContainingIgnoreCase(String nombre);
    
    List<Salon> findByUbicacionContainingIgnoreCase(String ubicacion);
    
    List<Salon> findByCapacidadGreaterThanEqual(int capacidad);
    
    List<Salon> findByCapacidadLessThanEqual(int capacidad);

    List<Salon> findByNombreAndUbicacionContainingIgnoreCase(String nombre, String ubicacion);
    boolean getDisponible(String mnemonico);  
    boolean getActivo(String mnemonico);  
    void agregarSalon(String nombre, String mnemonico, String ubicacion, int capacidad, String description);
    void actualizarSalon(String mnemonico, SalonDTO dto);    
    void asignarRecurso(String nombre,int cantidad,ArrayList<String> especificacion);
}   
