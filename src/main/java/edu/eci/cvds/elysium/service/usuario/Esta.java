package edu.eci.cvds.elysium.service.usuario;

import java.time.LocalDate;
<<<<<<< HEAD

import edu.eci.cvds.elysium.model.DiaSemanaModel;

public interface Esta

    void crearReserva(String idReserva,LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String idSalon,boolean duracionBloque);
=======
import java.time.LocalTime;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.ReservaModel;

public interface EstandarService extends UsuarioService {
<<<<<<< HEAD
    ReservaModel crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque);
>>>>>>> c899553fd22ec883a338597135e1b0a523a2d98f
=======
    ReservaModel crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad);
>>>>>>> e63ef6c391d7ee4c24dfeafedc418ca574933724
}
