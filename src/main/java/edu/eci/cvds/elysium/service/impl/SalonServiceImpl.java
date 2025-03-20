package edu.eci.cvds.elysium.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.salon.SalonDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.repository.SalonRepository;
import edu.eci.cvds.elysium.service.SalonService;

@Service
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonRepository salonRepository;

    /**
     * Find a salon by its mnemonic
     * @param mnemonico the mnemonic of the salon
     * @return the salon with the given mnemonic
     */
    @Override
    public Salon findByMnemonico(String mnemonico) {
        return salonRepository.findByMnemonico(mnemonico);
    }

    /**
     * Find all the salons
     * @return all the salons
     */
    @Override
    public List<Salon> findAll() {
        return salonRepository.findAll();
    }


    /**
     * Find all the salons that are active
     * @return all the salons that are active
     */
    @Override
    public List<Salon> findByActivoTrue() {
        return salonRepository.findByActivoTrue();
    }

    /**
     * Find all the salons that are inactive
     * @return all the salons that are inactive
     */
    @Override
    public List<Salon> findByActivoFalse() {
        return salonRepository.findByActivoFalse();
    }

    /**
     * Find all the salons that are available
     * @return all the salons that are available
     */
    @Override
    public List<Salon> findByDisponibleTrue() {
        return salonRepository.findByDisponibleTrue();
    }

    /**
     * Find all the salons that are not available
     * @return all the salons that are not available
     */
    @Override
    public List<Salon> findByDisponibleFalse() {
        return salonRepository.findByDisponibleFalse();
    }

    /**
     * Find all the salons that are active and available
     * @return all the salons that are active and available
     */
    @Override
    public List<Salon> findByActivoTrueAndDisponibleTrue() {
        return salonRepository.findByActivoTrueAndDisponibleTrue();
    }

    /**
     * Find all the salons that are active and not available
     * @return all the salons that are active and not available
     */
    @Override
    public List<Salon> findByActivoTrueAndDisponibleFalse() {
        return salonRepository.findByActivoTrueAndDisponibleFalse();
    }

    /**
     * Find all the salons that are inactive and available
     * @return all the salons that are inactive and available
     */
    @Override
    public List<Salon> findByActivoFalseAndDisponibleTrue() {
        return salonRepository.findByActivoFalseAndDisponibleTrue();
    }

    /**
     * Find all the salons that are inactive and not available
     * @return all the salons that are inactive and not available
     */
    @Override
    public List<Salon> findByActivoFalseAndDisponibleFalse() {
        return salonRepository.findByActivoFalseAndDisponibleFalse();
    }

    /**
     * Find all the salons that contain the given name
     * @param nombre the name to search
     * @return all the salons that contain the given name
     */
    @Override
    public List<Salon> findByNombreContainingIgnoreCase(String nombre) {
        return salonRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Find all the salons that contain the given location
     * @param ubicacion the location to search
     * @return all the salons that contain the given location
     */
    @Override
    public List<Salon> findByUbicacionContainingIgnoreCase(String ubicacion) {
        return salonRepository.findByUbicacionContainingIgnoreCase(ubicacion);
    }

    /**
     * Find all the salons that have a capacity greater than or equal to the given capacity
     * @param capacidad the capacity to compare
     * @return all the salons that have a capacity greater than or equal to the given capacity
     */
    @Override
    public List<Salon> findByCapacidadGreaterThanEqual(int capacidad) {
        return salonRepository.findByCapacidadGreaterThanEqual(capacidad);
    }

    /**
     * Find all the salons that have a capacity less than or equal to the given capacity
     * @param capacidad the capacity to compare
     * @return all the salons that have a capacity less than or equal to the given capacity
     */
    @Override
    public List<Salon> findByCapacidadLessThanEqual(int capacidad) {
        return salonRepository.findByCapacidadLessThanEqual(capacidad);
    }

    /**
     * Find all the salons that have the given name and location
     * @param nombre the name of the salon
     * @param ubicacion the location of the salon
     * @return all the salons that have the given name and location
     */
    @Override
    public List<Salon> findByNombreAndUbicacionContainingIgnoreCase(String nombre, String ubicacion) {
        return salonRepository.findByNombreAndUbicacionContainingIgnoreCase(nombre, ubicacion);
    }

    /**
     * Find all the salons that have the given name and location
     * @param nombre the name of the salon
     * @param ubicacion the location of the salon
     * @return all the salons that have the given name and location
     */
    @Override
    public void agregarSalon(String nombre, String mnemonico,String descripcion, String ubicacion, int capacidad, List<Recurso> recursos) {
        try{
            if(salonRepository.existsByMnemonico(mnemonico)){
                throw new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_SALON);
            }

            if(capacidad<=0){
                throw new ElysiumExceptions(ElysiumExceptions.CAPACIDAD_NO_VALIDA);
            }

            if(recursos.isEmpty()){
                throw new ElysiumExceptions(ElysiumExceptions.NO_EXISTE_RECURSO);
            }

            Salon nuevoSalon = new Salon(nombre, mnemonico, descripcion,ubicacion, capacidad, recursos);
            salonRepository.save(nuevoSalon);   
        }
        catch(Exception e){
            System.err.println("Error al agregar salon: " + e.getMessage());
        }
        
    }
    

    /**
     * Disable a salon
     * @param mnemonico the mnemonic of the salon
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
     * Enable a salon
     * @param mnemonico the mnemonic of the salon
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
     * Find a salon by its mnemonic
     * @param mnemonico the mnemonic of the salon
     * @return the salon with the given mnemonic
     */
    @Override
    public boolean getActivo(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        return salon != null && salon.isActivo();
    }

    /**
     * Update a salon
     * @param mnemonico the mnemonic of the salon
     * @param dto the new information of the salon
     */
    @Override
    public void actualizarSalon(String mnemonico, SalonDTO dto) {
        // Buscamos el salón por su mnemonico.
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        if (salon != null) {
            // Actualizamos únicamente los campos enviados en el DTO (no null).
            if (dto.getName() != null) {
                salon.setNombre(dto.getName());
            }
            if (dto.getDescription() != null) {
                salon.setDescripcion(dto.getDescription());
            }
            if (dto.getLocation() != null) {
                salon.setUbicacion(dto.getLocation());
            }
            if (dto.getCapacity() != null) {
                salon.setCapacidad(dto.getCapacity());
            }

            salonRepository.save(salon);
        }
    }

    /**
     * Find a salon by its mnemonic
     * @param mnemonico the mnemonic of the salon
     * @return the salon with the given mnemonic
     */
    @Override
    public boolean getDisponible(String mnemonico) {
        Salon salon = salonRepository.findByMnemonico(mnemonico);
        return salon != null && salon.isDisponible();
    }

    /**
     * Set the salon as available and return true if the operation is successful.
     * @param mnemonico the mnemonic of the salon
     * @return true if the operation is successful
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
     * Set the salon as not available and return true if the operation is successful.
     * @param mnemonico the mnemonic of the salon
     * @return true if the operation is successful
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
