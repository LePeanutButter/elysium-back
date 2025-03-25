package edu.eci.cvds.elysium.model.usuario;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public abstract class Usuario {
    @Id
    protected String idInstitucional;
    protected boolean isAdmin;
    protected String nombre;
    protected String apellido;
    protected String correoInstitucional;
    protected boolean activo;
    protected String password;

    /**
     * Constructor de la clase Usuario
     * @param idInstitucional user's institutional ID
     * @param nombre user's name
     * @param apellido user's last name
     * @param correoInstitucional user's institutional email
     * @param activo user's active status
     * @param isAdmin user's admin status
     */
    protected Usuario(String idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo, boolean isAdmin) {
        this.idInstitucional = idInstitucional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoInstitucional = correoInstitucional;
        this.activo = activo;
        this.isAdmin = isAdmin;
    }
    

    // GETTERS AND SETTERS

    /**
     * Get the user's institutional ID
     * @return user's institutional ID
     */
    public String getIdInstitucional() {
        return idInstitucional;
    }

    /**
     * Set the user's institutional ID
     * @param idInstitucional user's institutional ID
     */
    public void setIdInstitucional(String idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    /**
     * Get the user's name
     * @return user's name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the user's name
     * @param nombre user's name
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Get the user's last name
     * @return user's last name
     */
    public String getApellido() {
        return apellido;
    }

    
    /**
     * Set the user's last name
     * @param apellido user's last name
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Get the user's institutional email
     * @return user's institutional email
     */
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    /**
     * Set the user's institutional email
     * @param correoInstitucional user's institutional email
     */
    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }   
    
    /**
     * Get the user's active status
     * @return user's active status
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Set the user's active status
     * @param activo user's active status
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    /**
     * Set the user's admin status
     * @param esAdmin user's admin status
     */
    public void setAdmin(boolean esAdmin) {
        this.isAdmin = esAdmin;
    }   

    /**
     * Get the user's admin status
     * @return user's admin status
     */
    public boolean getIsAdmin(){
        return isAdmin;
    }   
    
    /**
     * Get the user's password
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }    
}
