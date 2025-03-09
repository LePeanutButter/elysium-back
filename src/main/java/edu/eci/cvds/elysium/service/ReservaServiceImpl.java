package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.*;
import edu.eci.cvds.elysium.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;


    @Override
    public List<ReservaModel> consultarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public List<ReservaModel> consultarReservasPorSalon(String idSalon) {
        return reservaRepository.findByIdSalon(idSalon);
    }

    @Override
    public List<ReservaModel> consultarReservasPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha);
    }

    @Override
    public List<ReservaModel> consultarReservasPorDiaSemana(DiaSemanaModel diaSemana) {
        return reservaRepository.findByDiaSemana(diaSemana);
    }

    @Override
    public List<ReservaModel> consultarReservasPorEstado(EstadoReservaModel estado) {
        return reservaRepository.findByEstado(estado);
    }

    @Override
    public List<ReservaModel> consultarReservasPorDuracionBloque(boolean duracionBloque) {
        return reservaRepository.findByDuracionBloque(duracionBloque);
    }

    @Override
    public ReservaModel consultarReserva(String idReserva) {
        return reservaRepository.findByIdReserva(idReserva);
    }

    @Override
    public void crearReserva(String idReserva, LocalDate fechaReserva, DiaSemanaModel diaSemana,String proposito, String idSalon,boolean duracionBloque) {
        ReservaModel reserva = new ReservaModel(idReserva, fechaReserva, diaSemana, proposito, idSalon, duracionBloque);
        reserva.crearReserva(idReserva, fechaReserva, diaSemana, proposito, idSalon, duracionBloque);
        EstadoReservaModel estado = EstadoReservaModel.ACTIVA;
        reserva.setEstado(estado);
        reservaRepository.save(reserva);
    }

    @Override
    public void actualizarReserva(String idReserva, char tipoCampo, LocalDate value1, DiaSemanaModel value2, String value3, boolean value4) {
        ReservaModel reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            reserva.actualizar(idReserva, tipoCampo, value1, value2, value3, value4);
            switch (tipoCampo) {
                case 'f':
                    reserva.setFechaReserva(value1);
                    break;
                case 'd':
                    reserva.setDiaSemana(value2);
                    break;
                case 's':
                    reserva.setIdSalon(value3);
                    break;
                case 'b':
                    reserva.setDuracionBloque(value4);
                    break;
                default:
                    break;
            }
            EstadoReservaModel estado = EstadoReservaModel.ACTIVA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }

    @Override
    public void deleteReserva(String idReserva) {
        ReservaModel reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            reserva.deleteReserva(idReserva);
            EstadoReservaModel estado = EstadoReservaModel.ELIMINADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }
    
    @Override
    public void cancelReserva(String idReserva) {
        ReservaModel reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            reserva.cancelReserva(idReserva);
            EstadoReservaModel estado = EstadoReservaModel.CANCELADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);

        }
    }

    @Override
    public void rechazarReserva(String idReserva) {
        ReservaModel reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            reserva.rechazarReserva(idReserva);
            EstadoReservaModel estado = EstadoReservaModel.RECHAZADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }
}
