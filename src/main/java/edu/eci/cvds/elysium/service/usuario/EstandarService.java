package edu.eci.cvds.elysium.service.usuario;

import java.time.LocalDate;

import edu.eci.cvds.elysium.model.DiaSemana;

public interface EstandarService extends UsuarioService {
        
    void crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemana diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad, int idInstitucional);


}