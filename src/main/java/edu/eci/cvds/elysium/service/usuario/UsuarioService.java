package edu.eci.cvds.elysium.service.usuario;

import edu.eci.cvds.elysium.model.usuario.Usuario;

public interface UsuarioService {

    /**
     * Consult a user by its institutional id
     * @param idInstitucional the user's institutional id
     * @return the user
     */
    Usuario consultarUsuario(String idInstitucional);
    
}
