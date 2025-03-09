package edu.eci.cvds.elysium.service.impl.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
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
    public void actualizarInformacionUsuario(ActualizarUsuarioDTO dto) {
        // Buscamos el usuario por su ID institucional.
        Usuario usuario = usuarioRepository.findByIdInstitucional(dto.getIdInstitucional());
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
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void deshabilitarUsuario(int idInstitucional) {
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null) {
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void habilitarUsuario(int idInstitucional) {
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null) {
            usuario.setActivo(true);
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

    // @Override
    // public void añadirSalon(int adminId, String nombre, String ubicacion, int
    // capacidad) {
    // Usuario usuario = usuarioRepository.findByIdInstitucional(adminId);
    // if (usuario != null && usuario instanceof Administrador) {
    // Administrador admin = (Administrador) usuario;
    // admin.añadirSalon(nombre, ubicacion, capacidad);
    // usuarioRepository.save(admin);
    // }
    // }

    @Override
    public void hacerAdmin(int id) {
        Usuario usuario = usuarioRepository.findByIdInstitucional(id);
        if (usuario != null) {
            usuario.setAdmin(true);
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void quitarAdmin(int id) {
        Usuario usuario = usuarioRepository.findByIdInstitucional(id);
        if (usuario != null) {
            usuario.setAdmin(false);
            usuarioRepository.save(usuario);
        }
    }   
}