package edu.eci.cvds.elysium.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.EstadoReservaModel;
import edu.eci.cvds.elysium.model.ReservaModel;


@Repository
public interface ReservaRepository extends MongoRepository<ReservaModel, String> {
    ReservaModel findByIdReserva(String idReserva);
    List<ReservaModel> findAll();
    List<ReservaModel> findByIdSalon(String idSalon);
    List<ReservaModel> findByFechaReserva(LocalDate fechaReserva);
    List<ReservaModel> findByDiaSemana(DiaSemanaModel diaSemana);
    List<ReservaModel> findByDuracionBloque(boolean duracionBloque);
    List<ReservaModel> findByEstado(EstadoReservaModel estado);
}
