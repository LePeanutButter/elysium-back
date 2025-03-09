package edu.eci.cvds.elysium.dto;

import java.util.List;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * RecursoDTO is a Data Transfer Object that represents a resource with a name, quantity, and specifications.
 */
public class RecursoDTO {
    
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId objectID;
    private char tipoCampo;
    private String nombre;
    private int cantidad;
    private List<String> especificaciones;

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
        this.objectID = new ObjectId();
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }
    
    public RecursoDTO(ObjectId objectID,char tipoCampo, String nombre, int cantidad, List<String> especificaciones) {
        this.objectID = objectID;
        this.tipoCampo = tipoCampo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.especificaciones = especificaciones;
    }

    // Getters
    @JsonIgnore
    @JsonProperty("objectID")
    public ObjectId getId() {return objectID;}


    @JsonProperty("objectID")
    public void setId(ObjectId id) {
        this.objectID = id;
    }

    public char getTipoCampo() {return tipoCampo;}
    public String getNombre(){return nombre;}
    public int getCantidad(){return cantidad;}
    public List<String> getEspecificaciones(){return especificaciones;}
    
}
