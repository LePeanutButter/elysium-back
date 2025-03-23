package edu.eci.cvds.elysium.dto;

import java.util.List;

import jakarta.validation.constraints.Negative;

/**
 * RecursoDTO is a Data Transfer Object that represents a resource with a name, quantity, and specifications.
 */
public class RecursoDTO {
    
    private String id;
    private String nombre;
    @Negative(message = "La cantidad no puede ser negativa")
    private int cantidad;
    private List<String> especificaciones;

    /**
     * Default constructor method
     */
    public RecursoDTO() {
    }

    /**
     * Default constructor method
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     * 
     */
    public RecursoDTO(String nombre, int cantidad, List<String> especificaciones) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }
    
    /**
     * Default constructor method
     * @param id id of the resource
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     * 
     */
    public RecursoDTO(String id, String nombre, int cantidad, List<String> especificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }

    // GETTERS

    /**
     * Get the id of the resource
     * @return id of the resource
     */
    public String getId() {return id;}

    /**
     * Get the name of the resource
     * @return name of the resource
     */
    public String getNombre(){return nombre;}

    /**
     * Get the amount of the resource
     * @return amount of the resource
     */
    public int getCantidad(){return cantidad;}

    /**
     * Get the specifications of the resource
     * @return specifications of the resource
     */
    public List<String> getEspecificaciones(){return especificaciones;}
    
}
