package edu.eci.cvds.elysium.service.usuario;

import java.time.LocalTime;

import edu.eci.cvds.elysium.model.Reserva;

public interface EstandarService extends UsuarioService {
    Reserva crearReserva(int idInstitucional, LocalTime fechaInicio, String proposito, String mnemonico);
}
