package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;

public interface ReservaService {

    /**
     * Consult all the reservations
     * @return all the reservations
     */
    List<Reserva> consultarReservas();

    /**
     * Consult the reservations by the user id
     * @param idUsuario the user id
     * @return the reservations by the user id
     */
    List<Reserva> consultarReservasPorUsuario(Integer idUsuario);
    /**
     * Consult the reservations by the salon
     * @param idSalon the salon
     * @return the reservations by the salon
     */
    List<Reserva> consultarReservasPorSalon(String idSalon);

    /**
     * Consult the reservations by the date
     * @param fecha the date
     * @return the reservations by the date
     */
    List<Reserva> consultarReservasPorFecha(LocalDate fecha);

    /**
     * Consult the reservations by the hour
     * @param hora the hour
     * @return the reservations by the hour
     */
    List<Reserva> consultarReservasPorHora(double hora);

    /**
     * Consult the reservations by the day of the week
     * @param diaSemana the day of the week
     * @return the reservations by the day of the week
     */
    List<Reserva> consultarReservasPorDiaSemana(DiaSemana diaSemana);

    /**
     * Consult the reservations by the state
     * @param estado the state
     * @return the reservations by the state
     */
    List<Reserva> consultarReservasPorEstado(EstadoReserva estado);

    /**
     * Consult the reservations by the duration of the block
     * @param duracionBloque the duration of the block
     * @return the reservations by the duration of the block
     */
    List<Reserva> consultarReservasPorDuracionBloque(boolean duracionBloque);

    /**
     * Consult the reservations by salon and state
     * @param idSalon the salon
     * @param estado the state
     * @return the reservations by the salon and state
     */
    List<Reserva> consultarReservasPorSalonAndEstado(String idSalon, EstadoReserva estado);

    /**
     * Consult the reservations by the id
     * @param idReserva the id
     * @return the reservations by the id
     */
    Reserva consultarReserva(String idReserva);

    /**
     * Create a reservation
     * @param fechaReserva the date of the reservation
     * @param hora the hour of the reservation
     * @param diaSemana the day of the week of the reservation
     * @param proposito the purpose of the reservation
     * @param materia the subject of the reservation
     * @param idSalon the salon of the reservation
     * @param duracionBloque the duration of the block of the reservation
     * @param prioridad the priority of the reservation
     * @param idInstitucional the institutional id of the reservation
     */
    void crearReserva(LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito,String materia, String idSalon,boolean duracionBloque, int prioridad, int idInstitucional);

    /**
     * Update a reservation
     * @param idReserva the id of the reservation
     * @param reservadDto the reservation
     */
    void actualizarReserva(String idReserva, ReservaDTO reservadDto);


    // it needs to be refactor as one put in controller
    
    /**
     * Disables a reservation
     * @param idReserva
     */
    void deshabilitarReserva(String idReserva);


    /**
     * Generates random reservations
     */
    void generarReservasAleatorias();
}
