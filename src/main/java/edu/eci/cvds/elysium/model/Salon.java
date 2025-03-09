package edu.eci.cvds.elysium.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Salon {
    //We declare mnemonico as the id of the salon
    @Id
    private String mnemonico;
    private String nombre;
    private String ubicacion;
    private int capacidad;
    private List<ObjectId> recursos;
    private boolean disponible;
    private boolean activo;

    public Salon(String nombre, String mnemonico, String ubicacion, int capacidad) {
        this.nombre = nombre;
        this.mnemonico = mnemonico;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.recursos = new ArrayList<ObjectId>();
        // The first time that it is created, it is available
        this.disponible = true;
        // The first time that it is created, it is active
        this.activo = true;

    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMnemonico() {
        return mnemonico;
    }
    public void setMnemonico(String mnemonico) {
        this.mnemonico = mnemonico;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public List<ObjectId> getRecursos() {
        return recursos;
    }
    public void setRecursos(List<ObjectId> recursos) {
        this.recursos = recursos;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
