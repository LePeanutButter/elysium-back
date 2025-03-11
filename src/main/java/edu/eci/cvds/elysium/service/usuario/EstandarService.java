package edu.eci.cvds.elysium.service.usuario;

import java.time.LocalDate;

import edu.eci.cvds.elysium.model.DiaSemanaModel;

public interface EstandarService extends UsuarioService {
    void crearReserva(String idReserva,LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String idSalon,boolean duracionBloque);
}
