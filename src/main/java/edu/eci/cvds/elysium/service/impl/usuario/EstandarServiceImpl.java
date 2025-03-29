package edu.eci.cvds.elysium.service.impl.usuario;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.ReservaService;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@Service
public class EstandarServiceImpl extends UsuarioServiceImpl implements EstandarService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    // temporal solution for circular dependency  
    @Lazy
    @Autowired
    private ReservaService reservaService;

    /**
     * Create a reservation
     * @param fechaReserva date of the reservation
     * @param hora hour of the reservation
     * @param diaSemana day of the week of the reservation
     * @param proposito purpose of the reservation
     * @param idSalon id of the salon
     * @param duracionBloque if the reservation is for a block of time
     * @param prioridad priority of the reservation
     * @param idInstitucional institutional id of the user
     */
    @Override
    public void crearReserva(LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito,String materia, String idSalon, boolean duracionBloque, int prioridad, String idInstitucional) {    
        // Se utiliza el método definido en el repository para Mongo
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null && usuario instanceof Estandar) {           
            reservaService.crearReserva(fechaReserva,hora, diaSemana, proposito, materia,idSalon, duracionBloque, prioridad, idInstitucional);            
        }  
    }

    /**
     * List the reservations of a user
     * @param idInstitucional institutional id of the user
     */
    @Override
    public void listarReservas(String idInstitucional) {
        // Se utiliza el método definido en el repository para Mongo
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null && usuario instanceof Estandar) {
            reservaService.consultarReservas();
        }
    }

}
