package edu.eci.cvds.elysium.service.usuario;

import java.time.LocalDate;
import java.time.LocalTime;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.ReservaModel;

public interface EstandarService extends UsuarioService {
    ReservaModel crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad);
}
