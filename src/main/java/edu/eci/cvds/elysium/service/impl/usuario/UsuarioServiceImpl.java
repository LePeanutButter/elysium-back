package edu.eci.cvds.elysium.service.impl.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.usuario.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Consult a user by its institutional id.
     * @param idInstitucional Institutional id of the user to consult.
     * @return User with the given id.
     */
    @Override
    public Usuario consultarUsuario(String idInstitucional) {
        // Si has definido findByIdInstitucional en el repository:
        return usuarioRepository.findByIdInstitucional(idInstitucional);
    }

    /**
     * Consult a user by its institutional email.
     * @param correoInstitucional Institutional email of the user to consult.
     * @return User with the given email.
     */
    @Override
    public Usuario consultarPorCorreoInstitucional(String correoInstitucional) {
        return usuarioRepository.findByCorreoInstitucional(correoInstitucional);
    }

}
