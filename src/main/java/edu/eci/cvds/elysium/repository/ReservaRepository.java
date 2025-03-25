package edu.eci.cvds.elysium.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;


@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {

    /**
     * Find a reservation by its ID
     * @param idReserva the ID of the reservation
     * @return the reservation with the given ID
     */
    Reserva findByIdReserva(String idReserva);

    /**
     * Find all the reservations
     * @return all the reservations
     */
    @SuppressWarnings("null")
    List<Reserva> findAll();

    /**
     * Find all the reservations by the ID of the salon
     * @param idSalon the ID of the salon
     * @return all the reservations with the given ID of the salon
     */
    List<Reserva> findByIdSalon(String idSalon);

    /**
     * Find all the reservations by the reservation date
     * @param fechaReserva the reservation date
     * @return all the reservations with the given reservation date
     */
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);
    
    /**
     * Find all the reservations by the reservation hour
     * @param hora the reservation hour
     * @return all the reservations with the given reservation hour
     */
    List<Reserva> findByHora(double hora);

    /**
     * Find all the reservations by the day of the week
     * @param diaSemana the day of the week
     * @return all the reservations with the given day of the week
     */
    List<Reserva> findByDiaSemana(DiaSemana diaSemana);

    /**
     * Find all the reservations by the duration of the block
     * @param duracionBloque the duration of the block
     * @return all the reservations with the given duration of the block
     */
    List<Reserva> findByDuracionBloque(boolean duracionBloque);

    /**
     * Find all the reservations by the reservation state
     * @param estado the reservation state
     * @return all the reservations with the given reservation state
     */
    List<Reserva> findByEstado(EstadoReserva estado);


    /**
     * Find all the reservations by the user ID
     * @param idUsuario the ID of the user
     * @return all the reservations with the given user ID
     */

    List<Reserva> findByIdUsuario(Integer idUsuario);
}
