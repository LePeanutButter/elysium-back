package edu.eci.cvds.elysium.model.usuario;

import java.util.ArrayList;
import java.util.List;

import edu.eci.cvds.elysium.model.Salon;

public class Administrador extends Usuario {

    private List<Salon> salones;

    public Administrador(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, true);
        this.salones = new ArrayList<>();
    }
}
