package edu.eci.cvds.elysium.model.usuario;

import java.util.ArrayList;
import java.util.List;

import edu.eci.cvds.elysium.model.Salon;

public class Administrador extends Usuario {

    @SuppressWarnings("unused")
    private List<Salon> salones;

    /**
     * Constructor method
     * @param idInstitucional represents the institutional ID
     * @param nombre represents the name
     * @param apellido represents the last name
     * @param correoInstitucional represents the institutional email
     * @param activo represents the active status
     */
    public Administrador(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, true);
        this.salones = new ArrayList<>();
    }
}
