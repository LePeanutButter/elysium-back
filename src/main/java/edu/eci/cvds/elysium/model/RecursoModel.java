package edu.eci.cvds.elysium.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;

/**
 * Represents a resource model for the "resources" collection.
 */
@Document(collection = "recursos")
public class RecursoModel {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId objectID; // Se usará ObjectId y Mongo lo generará automáticamente;
    private String nombre;
    private int cantidad;
    private List<String> especificaciones;
    private boolean activo;
    public RecursoModel() {
    }

    /**
     * Constructor to create a new ResourceModelinstance.
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     */
    public RecursoModel(String nombre, int cantidad, List<String> especificaciones) {
        this.objectID = new ObjectId();
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
    @JsonIgnore
    @JsonProperty("id")
    public ObjectId getId() {
        return objectID;
    }

    /**
     * Sets the resource ID.
     * @param id the resource ID
     */
    public void setId(ObjectId id) {
        this.objectID = id;
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
    
    /**
     * Creates a new resource with the given data.
     * @param nombre name of the resource
     * @param cantidad amount of the resource
     * @param especificaciones specifications of the resource
     * @return the new resource
     */
    public RecursoModel crearRecurso(String nombre, int cantidad, List<String> especificaciones) {
        this.objectID = new ObjectId();
        this.activo = true;
        return new RecursoModel(nombre, cantidad, especificaciones);
    }


    /**
     * Updates the resource.
     * @param id the resource ID
     * @param tipoCampo the type of field to update
     * @param nuevoNombre the new name of the resource
     * @param nuevaCantidad the new amount of the resource
     * @param nuevasEspecificaciones the new specifications of the resource
     */
    public void actualizar(ObjectId id,char tipoCampo, String nuevoNombre, int nuevaCantidad, List<String> nuevasEspecificaciones) {
        if(this.objectID.equals(id)){
            switch (tipoCampo) {
                case 'n':
                    this.nombre = nuevoNombre;
                    break;
                case 'c':
                    this.cantidad = nuevaCantidad;
                    break;
                case 'e':
                    this.especificaciones = nuevasEspecificaciones;
                    break;
                default:
                    break;
            }
        }
        this.activo = true;
    }

    /**
     * Deletes the resource.
     * @param id the resource ID
     */
    public void eliminar(ObjectId id) {
        if(this.objectID.equals(id)){
            this.activo = false;
        }
    }

}
