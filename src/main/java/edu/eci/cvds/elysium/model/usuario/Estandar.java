package edu.eci.cvds.elysium.model.usuario;

public class Estandar extends Usuario {

    /**
     * Constructor method
     * @param idInstitucional represents the institutional ID
     * @param nombre represents the name
     * @param apellido represents the last name
     * @param correoInstitucional represents the institutional email
     * @param activo represents the active status
     */
    public Estandar(String idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, false);
    }
}
