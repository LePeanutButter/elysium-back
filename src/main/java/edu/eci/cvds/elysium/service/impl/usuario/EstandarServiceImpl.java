package edu.eci.cvds.elysium.service.impl.usuario;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.DiaSemanaModel;
import edu.eci.cvds.elysium.model.ReservaModel;
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.usuario.EstandarService;

@Service
public class EstandarServiceImpl extends UsuarioServiceImpl implements EstandarService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private Estandar estandar;

    @Override
    public ReservaModel crearReserva(String idReserva, LocalDate fecha,double hora, DiaSemanaModel diaSemana, String proposito, String idSalon, boolean duracionBloque) {
        Usuario usuario = usuarioRepository.findByIdInstitucional(estandar.getIdInstitucional());
        if (usuario != null && usuario instanceof Estandar) {
            Estandar estandar = (Estandar) usuario;
            return estandar.crearReserva(idReserva,fecha,hora, diaSemana, proposito, idSalon, duracionBloque);
        }
        return null;
    }
}
