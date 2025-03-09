package edu.eci.cvds.elysium.service;

import java.util.List;

import edu.eci.cvds.elysium.dto.salon.ActualizarSalonDTO;
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

    void agregarSalon(String nombre, String mnemonico, String ubicacion, int capacidad);
    void deshabilitarSalon(String mnemonico);
    void habilitarSalon(String mnemonico);
    boolean getActivo(String mnemonico);  
    void actualizarSalon(String mnemonico, ActualizarSalonDTO dto);
    boolean getDisponible(String mnemonico);  
    boolean setDisponible(String mnemonico);
    boolean setNoDisponible(String mnemonico);    
    // void asignarRecurso(String nombre,int cantidad,ArrayList<String> especificacion);
}
