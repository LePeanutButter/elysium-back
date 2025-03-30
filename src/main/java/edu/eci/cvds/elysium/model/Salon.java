package edu.eci.cvds.elysium.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a salon model for the "salones" collection.
 */
// We declare the class as a document
@Document(collection = "salones")
public class Salon {
    //We declare mnemonico as the id of the salon
    @Id
    private String mnemonico;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private int capacidad;
    private List<Recurso> recursos;
    private boolean activo;
    private boolean disponible;
    

    /**
     * Default constructor for a SalonModel instance.
     */
    public Salon() {
    }

    /**
     * Constructor to create a new SalonModel instance.
     *
     * @param nombre     the name of the salon
     * @param mnemonico  the mnemonic of the salon
     * @param descripcion the description of the salon
     * @param ubicacion  the location of the salon
     * @param capacidad  the capacity of the salon
     * @param recursos   the resources of the salon
     */
    public Salon(String nombre, String mnemonico,String descripcion, String ubicacion, int capacidad,List<Recurso> recursos) {
        this.nombre = nombre;
        this.mnemonico = mnemonico;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.recursos = recursos;
        // The first time that it is created, it is available
        this.disponible = true;
        // The first time that it is created, it is active
        this.activo = true;
    }

    // GETTERS AND SETTERS

    /**
     * Get the name of the salon
     * @return the name of the salon
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the name of the salon
     * @param nombre the name of the salon
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the mnemonic of the salon
     * @return the mnemonic of the salon
     */
    public String getMnemonico() {
        return mnemonico;
    }

    /**
     * Set the mnemonic of the salon
     * @param mnemonico the mnemonic of the salon
     */
    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }

    /**
     * Get the location of the salon
     * @return the location of the salon
     */
    public String getUbicacion() {
        return ubicacion;
    }


    /**
     * Set the location of the salon
     * @param ubicacion the location of the salon
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Get the capacity of the salon
     * @return the capacity of the salon
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Set the capacity of the salon
     * @param capacidad the capacity of the salon
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Get the resources of the salon
     * @return the resources of the salon
     */
    public List<Recurso> getRecursos() {
        return recursos;
    }

    /**
     * Set the resources of the salon
     * @param recursos the resources of the salon
     */
    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    /**
     * Get the availability of the salon
     * @return the availability of the salon
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Set the availability of the salon
     * @param disponible the availability of the salon
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Get the active status of the salon
     * @return the active status of the salon
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Set the active status of the salon
     * @param activo the active status of the salon
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Get the description of the salon
     * @return the description of the salon
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the description of the salon
     * @param descripcion the description of the salon
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
