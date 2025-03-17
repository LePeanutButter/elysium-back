package edu.eci.cvds.elysium.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

/**
 * Represents a resource model for the "resources" collection.
 */
@Document(collection = "recursos")
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private int cantidad;
    private List<String> especificaciones;
    private boolean activo;


    /**
     * Constructor to create a new ResourceModelinstance.
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     */
    public Recurso(String nombre, int cantidad, List<String> especificaciones) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
        this.activo = true;
    }

    /**
     * Gets the resource ID.
     *
     * @return the resource ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the resource ID.
     * @param id the resource ID
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the resource name.
     * @return the resource name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the resource name.
     * @param nombre the resource name
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the resource amount.
     * @return the resource amount
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the resource amount.
     * @param cantidad the resource amount
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the resource specifications.
     * @return the resource specifications
     */
    public List<String> getEspecificaciones() {
        return especificaciones;
    }

    /**
     * Sets the resource specifications.
     * @param especificaciones the resource specifications
     */
    public void setEspecificaciones(List<String> especificaciones) {
        this.especificaciones = especificaciones;
    }

    /**
     * Gets the resource status.
     * @return the resource status
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Sets the resource status.
     * @param activo the resource status
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
