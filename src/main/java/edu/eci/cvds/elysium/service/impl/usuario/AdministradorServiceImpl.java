package edu.eci.cvds.elysium.service.impl.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Administrador;
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.usuario.AdministradorService;

@Service
public class AdministradorServiceImpl extends UsuarioServiceImpl implements AdministradorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> consultarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> consultarUsuariosActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    @Override
    public List<Usuario> consultarUsuariosInactivos() {
        return usuarioRepository.findByActivoFalse();
    }

    @Override
    public List<Usuario> consultarUsuariosAdmins() {
        return usuarioRepository.findByIsAdminTrue();
    }

    @Override
    public List<Usuario> consultarUsuariosActiveAdmins() {
        return usuarioRepository.findByActivoTrueAndIsAdminTrue();
    }

    @Override
    public List<Usuario> consultarUsuariosInactiveAdmins() {
        return usuarioRepository.findByActivoFalseAndIsAdminTrue();
    }

    @Override
    public List<Usuario> consultarUsuariosActiveNoAdmins() {
        return usuarioRepository.findByActivoTrueAndIsAdminFalse();
    }

    @Override
    public List<Usuario> consultarUsuariosInactiveNoAdmins() {
        return usuarioRepository.findByActivoFalseAndIsAdminFalse();
    }

    @Override
    public void actualizarInformacionUsuario(UsuarioDTO dto) {
        // Buscamos el usuario por su ID institucional.
        Usuario usuario = usuarioRepository.findByIdInstitucional(dto.getId());
        if (usuario != null) {
            // Actualizamos únicamente si el campo no es nulo.
            if (dto.getNombre() != null) {
                usuario.setNombre(dto.getNombre());
            }
            if (dto.getApellido() != null) {
                usuario.setApellido(dto.getApellido());
            }
            if (dto.getCorreo() != null) {
                usuario.setCorreoInstitucional(dto.getCorreo());
            }
            // Suponiendo que para cambiar el rol se requiere lógica adicional.
            if (dto.getIsAdmin() != null) {
                usuario.setAdmin(dto.getIsAdmin());
            }

            if (dto.getActivo() != null) {
                usuario.setActivo(dto.getActivo());
            }   

            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional,
            boolean isAdmin) {
        if (isAdmin) {
            Administrador nuevoUsuario = new Administrador(idInstitucional, nombre, apellido, correoInstitucional,
                    true);
            usuarioRepository.save(nuevoUsuario);
        } else {
            Estandar nuevoUsuario = new Estandar(idInstitucional, nombre, apellido, correoInstitucional, true);
            usuarioRepository.save(nuevoUsuario);
        }
    }
}