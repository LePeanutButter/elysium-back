package edu.eci.cvds.elysium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario consultarUsuario(int idInstitucional) {
        // Si has definido findByIdInstitucional en el repository:
        return usuarioRepository.findByIdInstitucional(idInstitucional);
        // Alternativamente, si usas el método heredado findById que retorna Optional:
        // return usuarioRepository.findById(idInstitucional).orElse(null);
    }

}
