package edu.eci.cvds.elysium.model.usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.model.Salon;

public class Estandar extends Usuario {

    public Estandar(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean activo) {
        super(idInstitucional, nombre, apellido, correoInstitucional, activo, false);
    }

    // Los usuarios estandar no manejan salones
    // @Override
    // public List<Salon> getSalones() {
    //     return Collections.emptyList();
    // }

    // // Método para crear reserva
    // public ReservaModel crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque) {
    //     return new ReservaModel(idReserva, fecha,hora,diaSemana, proposito, idSalon, duracionBloque);
    // }
}
