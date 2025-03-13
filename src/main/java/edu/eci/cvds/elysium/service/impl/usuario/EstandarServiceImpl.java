package edu.eci.cvds.elysium.service.impl.usuario;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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

    private ReservaService reservaService;

    @Override
    public void crearReserva(String idReserva,LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito, String idSalon, boolean duracionBloque, int prioridad, int idInstitucional) {    
        // Se utiliza el método definido en el repository para Mongo
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);


        if (usuario != null && usuario instanceof Estandar) {           
            reservaService.crearReserva(idReserva, fechaReserva,hora, diaSemana, proposito, idSalon, duracionBloque, prioridad, idInstitucional);
            
        }  
    }
}
