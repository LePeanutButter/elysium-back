package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;

public interface ReservaService {

    List<Reserva> consultarReservas();
    List<Reserva> consultarReservasPorSalon(String idSalon);
    List<Reserva> consultarReservasPorFecha(LocalDate fecha);
    List<Reserva> consultarReservasPorHora(double hora);
    List<Reserva> consultarReservasPorDiaSemana(DiaSemana diaSemana);
    List<Reserva> consultarReservasPorEstado(EstadoReserva estado);
    List<Reserva> consultarReservasPorDuracionBloque(boolean duracionBloque);
    List<Reserva> consultarReservasPorSalonAndEstado(String idSalon, EstadoReserva estado);
    Reserva consultarReserva(String idReserva);
    void crearReserva(LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito, String idSalon,boolean duracionBloque, int prioridad, int idInstitucional);
    void actualizarReserva(String idReserva,char tipoCampo,LocalDate value1,double value2,DiaSemana value3,String value4,boolean value5, int value6);


    // it needs to be refactor as one put in controller
    
    void deleteReserva(String idReserva);
    void cancelReserva(String idReserva);
    void rechazarReserva(String idReserva);
    void generarReservasAleatorias();
}
