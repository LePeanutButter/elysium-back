package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;

public interface ReservaService {

    List<Reserva> consultarReservas();
    List<Reserva> consultarReservasPorSalon(String idSalon);
    List<Reserva> consultarReservasPorFecha(LocalDate fecha);
    List<Reserva> consultarReservasPorHora(double hora);
    List<Reserva> consultarReservasPorDiaSemana(DiaSemanaModel diaSemana);
    List<Reserva> consultarReservasPorEstado(EstadoReserva estado);
    List<Reserva> consultarReservasPorDuracionBloque(boolean duracionBloque);
    List<Reserva> consultarReservasPorSalonAndEstado(String idSalon, EstadoReserva estado);
    Reserva consultarReserva(String idReserva);
    void crearReserva(String idReserva,LocalDate fechaReserva,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon,boolean duracionBloque, int prioridad);
    void actualizarReserva(String idReserva,char tipoCampo,LocalDate value1,double value2,DiaSemanaModel value3,String value4,boolean value5, int value6);
    void deleteReserva(String idReserva);
    void cancelReserva(String idReserva);
    void rechazarReserva(String idReserva);
    void generarReservasAleatorias();
}
