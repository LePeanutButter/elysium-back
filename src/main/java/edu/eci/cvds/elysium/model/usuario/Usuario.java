package edu.eci.cvds.elysium.model.usuario;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.eci.cvds.elysium.model.Salon;


@Document(collection = "usuarios")
public abstract class Usuario {
    @Id
    protected int idInstitucional;
    protected boolean isAdmin;
    protected String nombre;
    protected String apellido;
    protected String correoInstitucional;
    protected boolean activo;

    public Usuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo, boolean isAdmin) {
        this.idInstitucional = idInstitucional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoInstitucional = correoInstitucional;
        this.activo = activo;
        this.isAdmin = isAdmin;
    }
    
    // Getters y setters
    public int getIdInstitucional() {
        return idInstitucional;
    }

    public void setIdInstitucional(int idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public void setAdmin(boolean esAdmin) {
        this.isAdmin = esAdmin;
    }   

    public boolean getIsAdmin(){
        return isAdmin;
    }
    // Método abstracto que se implementará en Administrador
    public abstract List<Salon> getSalones();

}
