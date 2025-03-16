package edu.eci.cvds.elysium.model.usuario;

public class Estandar extends Usuario {

    public Estandar(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, false);
    }
}
