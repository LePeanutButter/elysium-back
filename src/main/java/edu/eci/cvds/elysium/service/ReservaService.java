package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;

public interface ReservaService {

    List<ReservaModel> consultarReservas();
    List<ReservaModel> consultarReservasPorSalon(String idSalon);
    List<ReservaModel> consultarReservasPorFecha(LocalDate fecha);
    List<ReservaModel> consultarReservasPorDiaSemana(DiaSemanaModel diaSemana);
    List<ReservaModel> consultarReservasPorEstado(EstadoReservaModel estado);
    List<ReservaModel> consultarReservasPorDuracionBloque(boolean duracionBloque);
    ReservaModel consultarReserva(String idReserva);

    void crearReserva(String idReserva, LocalDate fechaReserva, DiaSemanaModel diaSemana,
                      String proposito, String idSalon, boolean duracionBloque, int prioridad);

    void actualizarReserva(String idReserva, char tipoCampo, LocalDate fechaReserva,
                           DiaSemanaModel diaSemana, String idSalon, boolean duracionBloque, int prioridad);

    void deleteReserva(String idReserva);
    void cancelReserva(String idReserva);
    void rechazarReserva(String idReserva);

}
