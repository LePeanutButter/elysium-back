package edu.eci.cvds.elysium.dto;

import java.util.List;

import edu.eci.cvds.elysium.model.Recurso;
import jakarta.validation.constraints.Positive;
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
    @Positive(message = "La capacidad debe ser un número positivo") // CAMBIO AQUÍ
    private Integer capacidad;
    
    @NotNull(message = "Los recursos no pueden ser nulos")
    private List<Recurso> recursos;
    private Boolean activo;
    private Boolean disponible;

    /**
     * Default constructor
     */
    public SalonDTO() {}
    

    // Setters explicitos porque los getters no tienen el mismo nombre que los atributos, entonces jackson no los encuentra.
     // GETTERS AND SETTERS

    /**
     * Get the mnemonic of the salon
     * @return the mnemonic of the salon
     */
    public String getMnemonic(){return mnemonico;}

    /**
     * Set the mnemonic of the salon
     * @param mnemonico the mnemonic of the salon
     */
    public void setMnemonic(String mnemonico){this.mnemonico = mnemonico;}

    /**
     * Get the name of the salon
     * @return the name of the salon
     */
    public String getName(){return nombre;}

    /**
     * Set the name of the salon
     * @param nombre the name of the salon
     */
    public void setName(String nombre){this.nombre = nombre;}

    /**
     * Get the location of the salon
     * @return the location of the salon
     */
    public String getLocation(){return ubicacion;}

    /**
     * Set the location of the salon
     * @param ubicacion the location of the salon
     */
    public void setLocation(String ubicacion){this.ubicacion = ubicacion;}

    /**
     * Get the capacity of the salon
     * @return the capacity of the salon
     */
    public Integer getCapacity(){return capacidad;}

    /**
     * Set the capacity of the salon
     * @param capacidad the capacity of the salon
     */
    public void setCapacity(Integer capacidad){this.capacidad = capacidad;}

    /**
     * Get the description of the salon
     * @return the description of the salon
     */
    public String getDescription(){return descripcion;}

    /**
     * Set the description of the salon
     * @param descripcion the description of the salon
     */
    public void setDescription(String descripcion){this.descripcion = descripcion;}

    /**
     * Get the active status of the salon
     * @return the active status of the salon
     */
    public Boolean getActivo() {return activo;}

    /**
     * Set the active status of the salon
     * @param activo the active status of the salon
     */
    public void setActivo(boolean activo){this.activo = activo;}

    /**
     * Get the available status of the salon
     * @return the available status of the salon
     */
    public Boolean getAvailable(){return disponible;}

    /**
     * Set the available status of the salon
     * @param disponible the available status of the salon
     */
    public void setAvailable(boolean disponible){this.disponible = disponible;}

    /**
     * Get the resources of the salon
     * @return the resources of the salon
     */
    public List<Recurso> getResources(){return recursos;}

    /**
     * Set the resources of the salon
     * @param recursos the resources of the salon
     */
    public void setResources(List<Recurso> recursos){this.recursos = recursos;}

}