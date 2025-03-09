package edu.eci.cvds.elysium.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.dto.salon.ActualizarSalonDTO;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.repository.SalonRepository;
import edu.eci.cvds.elysium.service.SalonService;

@Service
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonRepository salonRepository;

    @Override
    public Salon findByMnemonico(String mnemonico) {
        return salonRepository.findByMnemonico(mnemonico);
        }

        @Override
        public List<Salon> findAll() {
        return salonRepository.findAll();
        }

        @Override
        public List<Salon> findByActivoTrue() {
        return salonRepository.findByActivoTrue();
        }

        @Override
        public List<Salon> findByActivoFalse() {
        return salonRepository.findByActivoFalse();
        }

        @Override
        public List<Salon> findByDisponibleTrue() {
        return salonRepository.findByDisponibleTrue();
        }

        @Override
        public List<Salon> findByDisponibleFalse() {
        return salonRepository.findByDisponibleFalse();
        }

        @Override
        public List<Salon> findByActivoTrueAndDisponibleTrue() {
        return salonRepository.findByActivoTrueAndDisponibleTrue();
        }

        @Override
        public List<Salon> findByActivoTrueAndDisponibleFalse() {
        return salonRepository.findByActivoTrueAndDisponibleFalse();
        }

        @Override
        public List<Salon> findByActivoFalseAndDisponibleTrue() {
        return salonRepository.findByActivoFalseAndDisponibleTrue();
        }

        @Override
        public List<Salon> findByActivoFalseAndDisponibleFalse() {
        return salonRepository.findByActivoFalseAndDisponibleFalse();
        }

        @Override
        public List<Salon> findByNombreContainingIgnoreCase(String nombre) {
        return salonRepository.findByNombreContainingIgnoreCase(nombre);
        }

        @Override
        public List<Salon> findByUbicacionContainingIgnoreCase(String ubicacion) {
        return salonRepository.findByUbicacionContainingIgnoreCase(ubicacion);
        }

        @Override
        public List<Salon> findByCapacidadGreaterThanEqual(int capacidad) {
        return salonRepository.findByCapacidadGreaterThanEqual(capacidad);
        }

        @Override
        public List<Salon> findByCapacidadLessThanEqual(int capacidad) {
        return salonRepository.findByCapacidadLessThanEqual(capacidad);
        }

        @Override
        public List<Salon> findByNombreAndUbicacionContainingIgnoreCase(String nombre, String ubicacion) {
        return salonRepository.findByNombreAndUbicacionContainingIgnoreCase(nombre, ubicacion);
        }

    /**
     * Agrega y persiste un nuevo salón.
     */
    @Override
    public void agregarSalon(String nombre, String mnemonico, String ubicacion, int capacidad) {
        // Se asume que el constructor de Salon es:
        // Salon(String nombre, String mnemonico, String ubicacion, int capacidad, boolean activo, boolean disponible)
        Salon nuevoSalon = new Salon(nombre, mnemonico, ubicacion, capacidad);
        salonRepository.save(nuevoSalon);
    }

    /**
     * Deshabilita un salón (lo marca como inactivo).
     */
    @Override
    public void deshabilitarSalon(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        if (salon != null) {
            salon.setActivo(false);
            salonRepository.save(salon);
        }
    }

    /**
     * Habilita un salón (lo marca como activo).
     */
    @Override
    public void habilitarSalon(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        if (salon != null) {
            salon.setActivo(true);
            salonRepository.save(salon);
        }
    }

    /**
     * Retorna el estado 'activo' del salón identificado por su mnemonico.
     */
    @Override
    public boolean getActivo(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        return salon != null && salon.isActivo();
    }

    @Override
public void actualizarSalon(String mnemonico, ActualizarSalonDTO dto) {
    // Buscamos el salón por su mnemonico.
    Salon salon = salonRepository.findByMnemonico(mnemonico);
    if (salon != null) {
        // Actualizamos únicamente los campos enviados en el DTO (no null).
        if (dto.getNombre() != null) {
            salon.setNombre(dto.getNombre());
        }
        if (dto.getUbicacion() != null) {
            salon.setUbicacion(dto.getUbicacion());
        }
        if (dto.getCapacidad() != null) {
            salon.setCapacidad(dto.getCapacidad());
        }
        salonRepository.save(salon);
    }
}

    /**
     * Retorna el valor del atributo 'disponible' para el salón.
     */
    @Override
    public boolean getDisponible(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        return salon != null && salon.isDisponible();
    }

    /**
     * Establece el salón como disponible y retorna true si la operación es exitosa.
     */
    @Override
    public boolean setDisponible(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        if (salon != null) {
            salon.setDisponible(true);
            salonRepository.save(salon);
            return true;
        }
        return false;
    }

    /**
     * Establece el salón como no disponible y retorna true si la operación es exitosa.
     */
    @Override
    public boolean setNoDisponible(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        if (salon != null) {
            salon.setDisponible(false);
            salonRepository.save(salon);
            return true;
        }
        return false;
    }
}
