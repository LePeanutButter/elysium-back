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
    Reserva findByIdReserva(String idReserva);
    @SuppressWarnings("null")
    List<Reserva> findAll();
    List<Reserva> findByIdSalon(String idSalon);
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);
    List<Reserva> findByHora(double hora);
    List<Reserva> findByDiaSemana(DiaSemana diaSemana);
    List<Reserva> findByDuracionBloque(boolean duracionBloque);
    List<Reserva> findByEstado(EstadoReserva estado);
}
