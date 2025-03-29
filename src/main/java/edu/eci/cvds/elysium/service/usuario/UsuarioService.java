package edu.eci.cvds.elysium.service.usuario;

import edu.eci.cvds.elysium.model.usuario.Usuario;

public interface UsuarioService {

    /**
     * Consult a user by its institutional id
     * @param idInstitucional the user's institutional id
     * @return the user
     */
    Usuario consultarUsuario(String idInstitucional);

    /**
     * Consult a user by its institutional email
     * @param correoInstitucional the user's institutional email
     * @return the user
     */
    Usuario consultarPorCorreoInstitucional(String correoInstitucional);
}
