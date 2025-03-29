package edu.eci.cvds.elysium.dto;

import java.util.List;

import edu.eci.cvds.elysium.model.Recurso;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;

public class SalonDTO {
    // Campo obligatorio para identificar el salón.
    @NotNull(message = "El mnemonico no puede ser nulo")
    private String mnemonico;
    // Los siguientes campos son opcionales; se actualizan si no son null.
    private String nombre;
    private String descripcion;
    @NotNull(message = "La ubicacion no puede ser nula")
    private String ubicacion;
    @NotNull(message = "La capacidad no puede ser nula")
    @Negative(message = "La capacidad no puede ser negativa")
    private Integer capacidad;
    
    @NotNull(message = "Los recursos no pueden ser nulos")
    private List<Recurso> recursos;
    private Boolean activo;
    private Boolean disponible;

    /**
     * Default constructor
     */
    public SalonDTO() {}


    // GETTERS AND SETTERS

    /**
     * Get the mnemonic of the salon
     * @return the mnemonic of the salon
     */
    public String getMnemonic(){return mnemonico;}


    /**
     * Get the name of the salon
     * @return the name of the salon
     */
    public String getName(){return nombre;}


    /**
     * Get the location of the salon
     * @return the location of the salon
     */
    public String getLocation(){return ubicacion;}


    /**
     * Get the capacity of the salon
     * @return the capacity of the salon
     */
    public Integer getCapacity(){return capacidad;}



    /**
     * Get the description of the salon
     * @return the description of the salon
     */
    public String getDescription(){return descripcion;}


    /**
     * Get the active status of the salon
     * @return the active status of the salon
     */
    public Boolean getActivo() {return activo;}

    
    /**
     * Get the available status of the salon
     * @return the available status of the salon
     */
    public Boolean getAvailable(){return disponible;}


    /**
     * Get the resources of the salon
     * @return the resources of the salon
     */
    public List<Recurso> getResources(){return recursos;}

    
}