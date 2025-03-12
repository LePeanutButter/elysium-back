package edu.eci.cvds.elysium.service.impl.usuario;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
<<<<<<< HEAD
import edu.eci.cvds.elysium.model.Reserva;
=======
import edu.eci.cvds.elysium.model.ReservaModel;
>>>>>>> c899553fd22ec883a338597135e1b0a523a2d98f
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.ReservaService;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@Service
public class EstandarServiceImpl extends UsuarioServiceImpl implements EstandarService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private Estandar estandar;

    private ReservaService reservaService;

    @Override
<<<<<<< HEAD
    public void crearReserva(String idReserva,LocalDate fechaReserva, DiaSemanaModel diaSemana, String proposito, String idSalon,boolean duracionBloque) {
        // Se utiliza el método definido en el repository para Mongo
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null && usuario instanceof Estandar) {
            Estandar estandar = (Estandar) usuario;
            reservaService.crearReserva(idReserva, fechaReserva, diaSemana, proposito, idSalon, duracionBloque);
            
=======
    public ReservaModel crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque) {
        Usuario usuario = usuarioRepository.findByIdInstitucional(estandar.getIdInstitucional());
        if (usuario != null && usuario instanceof Estandar) {
            Estandar estandar = (Estandar) usuario;
            return estandar.crearReserva(idReserva,fecha,hora, diaSemana, proposito, idSalon, duracionBloque);
>>>>>>> c899553fd22ec883a338597135e1b0a523a2d98f
        }
    }
}
