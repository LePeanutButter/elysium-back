package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> consultarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<Reserva> consultarReservasPorSalon(String idSalon) {
        return reservaRepository.findByIdSalon(idSalon);
    }

    @Override
    public List<Reserva> consultarReservasPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha);
    }

    @Override
    public List<Reserva> consultarReservasPorHora(double hora) {
        return reservaRepository.findByHora(hora);
    }

    @Override
    public List<Reserva> consultarReservasPorDiaSemana(DiaSemana diaSemana) {
        return reservaRepository.findByDiaSemana(diaSemana);
    }

    @Override
    public List<Reserva> consultarReservasPorEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado);
    }

    @Override
    public List<Reserva> consultarReservasPorDuracionBloque(boolean duracionBloque) {
        return reservaRepository.findByDuracionBloque(duracionBloque);
    }

    @Override
    public List<Reserva> consultarReservasPorSalonAndEstado(String idSalon, EstadoReserva estado) {
        // Primero se obtienen todas las reservas del salón especificado
        List<Reserva> reservasPorSalon = reservaRepository.findByIdSalon(idSalon);
        // Luego se filtran por el estado deseado
        return reservasPorSalon.stream()
                .filter(reserva -> reserva.getEstado().equals(estado))
                .collect(Collectors.toList());
    }

    @Override
    public Reserva consultarReserva(String idReserva) {
        return reservaRepository.findByIdReserva(idReserva);
    }

    /**
     * Creates a new reservation.
     *
     * @param idReserva      the reservation ID
     * @param fechaReserva   the date of the reservation
     * @param diaSemana      the day of the week of the reservation
     * @param proposito      the purpose of the reservation
     * @param idSalon        the salon associated with the reservation
     * @param estado         the state of the reservation
     * @param duracionBloque the duration block of the reservation
     * @return the new reservation
     */
    @Override
    public void crearReserva(LocalDate fechaReserva, double hora, DiaSemana diaSemana,
            String proposito, String idSalon, boolean duracionBloque, int prioridad, int idInstitucional) {
        Reserva reserva = new Reserva( fechaReserva, hora, diaSemana, proposito, idSalon, duracionBloque,
                prioridad, idInstitucional);

        reserva.setEstado(EstadoReserva.ACTIVA);
        reservaRepository.save(reserva);
    }

    /**
     * Updates the reservation with the new data.
     * 
     * @param idInstitucional the reservation ID
     * @param tipoCampo       the field type to update
     * @param value1          the new value
     * @param value2          the new value
     * @param value3          the new value
     * @param value4          the new value
     */

    @Override
    public void actualizarReserva(String idReserva, char tipoCampo, LocalDate value1, double value2,
            DiaSemana value3, String value4, boolean value5, int value6) {
        Reserva reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            switch (tipoCampo) {
                case 'f':
                    reserva.setFechaReserva(value1);
                    break;
                case 'h':
                    reserva.setHora(value2);
                    break;
                case 'd':
                    reserva.setDiaSemana(value3);
                    break;
                case 's':
                    reserva.setIdSalon(value4);
                    break;
                case 'b':
                    reserva.setDuracionBloque(value5);
                    break;
                case 'p':
                    reserva.setPrioridad(value6);
                    break;
                default:
                    break;
            }
            EstadoReserva estado = EstadoReserva.ACTIVA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }

    /**
     * Deletes the reservation. It's a logical delete or soft delete.
     * 
     * @param idReserva the reservation ID
     */
    @Override
    public void deleteReserva(String idReserva) {
        Reserva reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            EstadoReserva estado = EstadoReserva.ELIMINADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }

    /**
     * Cancels the reservation.
     * 
     * @param idReserva the reservation ID
     */
    @Override
    public void cancelReserva(String idReserva) {
        Reserva reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            EstadoReserva estado = EstadoReserva.CANCELADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }

    /**
     * Accepts the reservation.
     * 
     * @param idReserva the reservation ID
     */
    @Override
    public void rechazarReserva(String idReserva) {
        Reserva reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            EstadoReserva estado = EstadoReserva.RECHAZADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }

    @Override
    public void generarReservasAleatorias() {
        Random random = new Random();
        int cantidad = random.nextInt(901) + 100;
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaReserva = LocalDate.now().plusDays(random.nextInt(30));
            DiaSemana diaSemana = DiaSemana.values()[random.nextInt(DiaSemana.values().length)];
            String proposito = "Reserva generada automáticamente";
            String idSalon = String.valueOf(random.nextInt(101) + 50);
            boolean duracionBloque = random.nextBoolean();
            int prioridad = random.nextInt(5) + 1;
            int hora = random.nextInt(24);
            int idInstitucional = random.nextInt(6) + 1;
            
            crearReserva(fechaReserva, hora, diaSemana, proposito, idSalon, duracionBloque, prioridad, idInstitucional);
        }
    }
}
