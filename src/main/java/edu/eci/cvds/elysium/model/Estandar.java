package edu.eci.cvds.elysium.model;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Estandar extends Usuario {

    public Estandar(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, false);
    }

    // Los usuarios estandar no manejan salones
    @Override
    public List<Salon> getSalones() {
        return Collections.emptyList();
    }

    // Método para crear reserva
    public Reserva crearReserva(LocalTime fechaInicio, String proposito, String mnemonico) {
        return new Reserva(fechaInicio, proposito, mnemonico, this);
    }
}
