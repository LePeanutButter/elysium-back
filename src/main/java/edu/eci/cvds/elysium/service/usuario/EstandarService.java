package edu.eci.cvds.elysium.service.usuario;

import java.time.LocalDate;

import edu.eci.cvds.elysium.model.DiaSemana;

public interface EstandarService extends UsuarioService {
    
    /**
     * Create a reservation
     * @param fecha date of the reservation
     * @param hora hour of the reservation
     * @param diaSemana day of the week of the reservation
     * @param proposito purpose of the reservation
     * @param idSalon id of the salon
     * @param duracionBloque if the reservation is for a block of time
     * @param prioridad priority of the reservation
     * @param idInstitucional institutional id of the user
     */
    void crearReserva(LocalDate fecha,double hora, DiaSemana diaSemana, String proposito, String materia, String idSalon, boolean duracionBloque, int prioridad, String idInstitucional);

    /**
     * List the reservations of a user
     * @param idInstitucional institutional id of the user
     */
    void listarReservas(String idInstitucional);

}