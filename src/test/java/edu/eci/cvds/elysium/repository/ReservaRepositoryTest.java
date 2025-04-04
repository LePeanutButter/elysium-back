package edu.eci.cvds.elysium.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;







@DataMongoTest
public class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @BeforeEach
    public void setup() {
        // Clear the repository before each test
        reservaRepository.deleteAll();
    }

    private Reserva createReserva(String idReserva, String idSalon, LocalDate fechaReserva, double hora, 
                                  DiaSemana diaSemana, boolean duracionBloque, EstadoReserva estado, 
                                  Integer idUsuario) {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(idReserva);
        reserva.setIdSalon(idSalon);
        reserva.setFechaReserva(fechaReserva);
        reserva.setHora(hora);
        reserva.setDiaSemana(diaSemana);
        reserva.setDuracionBloque(duracionBloque);
        reserva.setEstado(estado);
        reserva.setIdUsuario(idUsuario);
        return reserva;
    }

    @Test
    public void testSaveAndFindByIdReserva() {
        Reserva reserva = createReserva("reserva1", "salon1", LocalDate.now(), 10.0, 
                                        DiaSemana.LUNES, true, EstadoReserva.ACTIVA, 101);
        reservaRepository.save(reserva);

        Reserva found = reservaRepository.findByIdReserva("reserva1");
        assertThat(found).isNotNull();
        assertThat(found.getIdReserva()).isEqualTo("reserva1");
    }

    @Test
    public void testFindAll() {
        Reserva reserva1 = createReserva("reserva1", "salon1", LocalDate.now(), 10.0, 
                                         DiaSemana.LUNES, true, EstadoReserva.ACTIVA, 101);
        Reserva reserva2 = createReserva("reserva2", "salon2", LocalDate.now().plusDays(1), 11.0, 
                                         DiaSemana.MARTES, false, EstadoReserva.DESHABILITADA, 102);
        reservaRepository.save(reserva1);
        reservaRepository.save(reserva2);

        List<Reserva> reservas = reservaRepository.findAll();
        assertThat(reservas).hasSize(2);
    }

    @Test
    public void testFindByIdSalon() {
        Reserva reserva = createReserva("reserva1", "salon123", LocalDate.now(), 9.0, 
                                        DiaSemana.MIERCOLES, true, EstadoReserva.ACTIVA, 103);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByIdSalon("salon123");
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).getIdSalon()).isEqualTo("salon123");
    }

    @Test
    public void testFindByFechaReserva() {
        LocalDate date = LocalDate.of(2023, 10, 10);
        Reserva reserva = createReserva("reserva1", "salon1", date, 10.5, 
                                        DiaSemana.JUEVES, false, EstadoReserva.ACTIVA, 104);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByFechaReserva(date);
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).getFechaReserva()).isEqualTo(date);
    }

    @Test
    public void testFindByHora() {
        double hora = 14.0;
        Reserva reserva = createReserva("reserva1", "salon2", LocalDate.now(), hora, 
                                        DiaSemana.VIERNES, true, EstadoReserva.ACTIVA, 105);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByHora(hora);
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).getHora()).isEqualTo(hora);
    }

    @Test
    public void testFindByDiaSemana() {
        DiaSemana dia = DiaSemana.SABADO;
        Reserva reserva = createReserva("reserva1", "salon3", LocalDate.now(), 16.0, 
                                        dia, true, EstadoReserva.ACTIVA, 106);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByDiaSemana(dia);
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).getDiaSemana()).isEqualTo(dia);
    }

    @Test
    public void testFindByDuracionBloque() {
        boolean duracionBloque = false;
        Reserva reserva = createReserva("reserva1", "salon4", LocalDate.now(), 8.0, 
                                        DiaSemana.SABADO, duracionBloque, EstadoReserva.ACTIVA, 107);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByDuracionBloque(duracionBloque);
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).isDuracionBloque()).isEqualTo(duracionBloque);
    }

    @Test
    public void testFindByEstado() {
        EstadoReserva estado = EstadoReserva.DESHABILITADA;
        Reserva reserva = createReserva("reserva1", "salon5", LocalDate.now(), 13.0, 
                                        DiaSemana.LUNES, true, estado, 108);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByEstado(estado);
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).getEstado()).isEqualTo(estado);
    }

    @Test
    public void testFindByIdUsuario() {
        Integer idUsuario = 200;
        Reserva reserva = createReserva("reserva1", "salon6", LocalDate.now(), 15.0, 
                                        DiaSemana.MARTES, true, EstadoReserva.ACTIVA, idUsuario);
        reservaRepository.save(reserva);

        List<Reserva> reservas = reservaRepository.findByIdUsuario(idUsuario);
        assertThat(reservas).hasSize(1);
        assertThat(reservas.get(0).getIdUsuario()).isEqualTo(idUsuario);
    }
}