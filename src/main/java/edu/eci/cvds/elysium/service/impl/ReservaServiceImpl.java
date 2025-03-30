package edu.eci.cvds.elysium.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.ReservaRepository;
import edu.eci.cvds.elysium.service.ReservaService;
import edu.eci.cvds.elysium.service.SalonService;
import edu.eci.cvds.elysium.service.UsuarioService;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SalonService salonService;

    /**
     * Returns all reservations.
     */
    @Override
    public List<Reserva> consultarReservas() {
        return reservaRepository.findAll();
    }


    /**
     * Consult the reservations by the user id
     * @param idUsuario the user id
     * @return the reservations by the user id
     */
    public List<Reserva> consultarReservasPorUsuario(Integer idUsuario){
        return reservaRepository.findByIdUsuario(idUsuario);
    }

    /**
     * Returns all reservations for a specific salon.
     * @param idSalon the salon ID
     * @return the reservations for the specified salon
     */
    @Override
    public List<Reserva> consultarReservasPorSalon(String idSalon) {
        return reservaRepository.findByIdSalon(idSalon);
    }


    /**
     * Returns all reservations for a specific date.
     * @param fecha the date of the reservation
     * @return the reservations for the specified date
     */
    @Override
    public List<Reserva> consultarReservasPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha);
    }

    /**
     * Returns all reservations for a specific time.
     * @param hora the time of the reservation
     * @return the reservations for the specified time
     */
    @Override
    public List<Reserva> consultarReservasPorHora(double hora) {
        return reservaRepository.findByHora(hora);
    }

    /**
     * Returns all reservations for a specific day of the week.
     * @param diaSemana the day of the week of the reservation
     * @return the reservations for the specified day of the week
     */
    @Override
    public List<Reserva> consultarReservasPorDiaSemana(DiaSemana diaSemana) {
        return reservaRepository.findByDiaSemana(diaSemana);
    }

    /**
     * Returns all reservations for a specific state.
     * @param state the state of the reservation
     * @return the reservations for the specified purpose
     */
    @Override
    public List<Reserva> consultarReservasPorEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado);
    }

    /**
     * Returns all reservations for block duration.
     * @param duracionBloque the block duration of the reservation
     * @return the reservations for block duration
     */
    @Override
    public List<Reserva> consultarReservasPorDuracionBloque(boolean duracionBloque) {
        return reservaRepository.findByDuracionBloque(duracionBloque);
    }

    /**
     * Returns all reservations with idSalon and state.
     * @param idSalon the salon ID
     *  @param estado the state of the reservation
     * @return the reservations for the specified salon and state
     */
    @Override
    public List<Reserva> consultarReservasPorSalonAndEstado(String idSalon, EstadoReserva estado) {
        // Primero se obtienen todas las reservas del salón especificado
        List<Reserva> reservasPorSalon = reservaRepository.findByIdSalon(idSalon);
        // Luego se filtran por el estado deseado
        return reservasPorSalon.stream()
                .filter(reserva -> reserva.getEstado().equals(estado))
                .collect(Collectors.toList());
    }

    /**
     * Returns resrvation by id.
     * @param idReserva the reservation ID
     * @return the reservation with the specified ID
     */
    @Override
    public Reserva consultarReserva(String idReserva) {
        return reservaRepository.findByIdReserva(idReserva);
    }

    /**
     * Creates a new reservation.
     * @param fechaReserva the reservation date
     * @param hora the reservation time
     * @param diaSemana the reservation day of the week
     * @param proposito the reservation purpose
     * @param idSalon the reservation salon ID
     * @param duracionBloque the reservation duration
     * @param prioridad the reservation priority
     * @param idInstitucional the reservation user ID
     */
    @SuppressWarnings("unlikely-arg-type")
    // Se crea una reserva con los datos ingresados
    @Override
    public void crearReserva(LocalDate fechaReserva, double hora, DiaSemana diaSemana,
            String proposito, String materia,String idSalon, boolean duracionBloque, int prioridad, int idInstitucional) {
        
                try{
                    if(hora < 0 ){
                        throw new ElysiumExceptions(ElysiumExceptions.HORA_NO_VALIDA);
                    }
                    if(diaSemana.equals("DOMINGO")){
                        throw new ElysiumExceptions(ElysiumExceptions.DIA_NO_VALIDO);
                    }
                    if(prioridad < 1 || prioridad > 5){
                        throw new ElysiumExceptions(ElysiumExceptions.PRIORIDAD_NO_VALIDA);
                    }

                    if(idSalon == null || idSalon.equals("")){
                        throw new ElysiumExceptions(ElysiumExceptions.NO_EXISTE_SALON);
                    }

                    if(idInstitucional == 0){
                        throw new ElysiumExceptions(ElysiumExceptions.NO_EXISTE_USUARIO);
                    }

                    Usuario usuario = usuarioService.consultarUsuario(idInstitucional);
                    Salon salon = salonService.findByMnemonico(idSalon);

                    if (usuario != null && salon != null) {
                        Reserva reserva = new Reserva(fechaReserva, hora, diaSemana, proposito,materia, idSalon, duracionBloque,
                                prioridad, idInstitucional);
                        reserva.setEstado(EstadoReserva.ACTIVA);
                        reservaRepository.save(reserva);
                    }
                    else {
                        throw new IllegalArgumentException("No se encontró el usuario o el salón.");
                    }
                }
                catch (ElysiumExceptions e) {
                    System.err.println("Error al crear reserva: " + e.getMessage());
                }
    }

    
    /**
     * Updates the reservation.
     * @param idReserva the reservation ID
     * @param reservaDTO the reservation data
     */
    // Se actualiza la reserva con los datos ingresados
    @Override
    public void actualizarReserva(String idReserva, ReservaDTO reservaDTO) {
        Reserva reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            if(reservaDTO.getFechaReserva() != null) {
                reserva.setFechaReserva(reservaDTO.getFechaReserva());
            }
            if(reservaDTO.getHora() != 0) {
                reserva.setHora(reservaDTO.getHora());
            }
            if(reservaDTO.getDiaSemana() != null) {
                reserva.setDiaSemana(reservaDTO.getDiaSemana());
            }
            if(reservaDTO.getProposito() != null) {
                reserva.setProposito(reservaDTO.getProposito());
            }
            if(reservaDTO.getMateria() != null) {
                reserva.setMateria(reservaDTO.getMateria());
            }
            if(reservaDTO.getIdSalon() != null) {
                reserva.setIdSalon(reservaDTO.getIdSalon());
            }
            if(reservaDTO.isDuracionBloque() != false) {
                reserva.setDuracionBloque(reservaDTO.isDuracionBloque());
            }
            if(reservaDTO.getPrioridad() != 0) {
                reserva.setPrioridad(reservaDTO.getPrioridad());
            }
            if(reservaDTO.getIdUsuario() != 0) {
                reserva.setIdUsuario(reservaDTO.getIdUsuario());
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
    public void deshabilitarReserva(String idReserva) {
        Reserva reserva = reservaRepository.findByIdReserva(idReserva);
        if (reserva != null) {
            EstadoReserva estado = EstadoReserva.DESHABILITADA;
            reserva.setEstado(estado);
            reservaRepository.save(reserva);
        }
    }

    /**
     * Generates random reservations.
     */
    @Override
    public void generarReservasAleatorias() {
        Random random = new Random();
        int cantidad = random.nextInt(901) + 100;
        for (int i = 0; i < cantidad; i++) {
            LocalDate fechaReserva = LocalDate.now().plusDays(random.nextInt(30));
            DiaSemana diaSemana = DiaSemana.values()[random.nextInt(DiaSemana.values().length)];
            String proposito = "Reserva generada automáticamente";
            String materia = "Materia " + (random.nextInt(5) + 1);
            String idSalon = String.valueOf(random.nextInt(101) + 50);
            boolean duracionBloque = random.nextBoolean();
            int prioridad = random.nextInt(5) + 1;
            int hora = random.nextInt(24);
            int idInstitucional = random.nextInt(6) + 1;
            
            crearReserva(fechaReserva, hora, diaSemana, proposito, materia,idSalon, duracionBloque, prioridad, idInstitucional);
        }
    }

    /**
     * Returns the reservations by user id.
     * @param idUsuario the user ID
     * @return the reservations for the specified user
     */
    @Override
    public List<Reserva> consultarReservasPorUsuario(int idUsuario) {
        return reservaRepository.findByIdUsuario(idUsuario);
    }
}
