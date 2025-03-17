package edu.eci.cvds.elysium.service.impl.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.model.usuario.Administrador;
import edu.eci.cvds.elysium.model.usuario.Estandar;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.usuario.AdministradorService;

@Service
public class AdministradorServiceImpl extends UsuarioServiceImpl implements AdministradorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Consult a list of all users.
     * @return List of all users.
     */
    @Override
    public List<Usuario> consultarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Consult the active users.
     * @return List of active users.
     */
    @Override
    public List<Usuario> consultarUsuariosActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    /**
     * Consult the inactive users.
     * @return List of inactive users.
     */
    @Override
    public List<Usuario> consultarUsuariosInactivos() {
        return usuarioRepository.findByActivoFalse();
    }

    /**
     * Consult the users that are administrators.
     * @return List of administrators.
     */
    @Override
    public List<Usuario> consultarUsuariosAdmins() {
        return usuarioRepository.findByIsAdminTrue();
    }

    /**
     * Consult the users that are not administrators.
     * @return List of users that are not administrators.
     */
    @Override
    public List<Usuario> consultarUsuariosActiveAdmins() {
        return usuarioRepository.findByActivoTrueAndIsAdminTrue();
    }

    /**
     * Consult the users that are not administrators and are active.
     * @return List of users that are not administrators and are active.
     */
    @Override
    public List<Usuario> consultarUsuariosInactiveAdmins() {
        return usuarioRepository.findByActivoFalseAndIsAdminTrue();
    }

    /**
     * Consult the users that are not administrators.
     * @return List of users that are not administrators.
     */
    @Override
    public List<Usuario> consultarUsuariosActiveNoAdmins() {
        return usuarioRepository.findByActivoTrueAndIsAdminFalse();
    }


    /**
     * Consult the users that are not administrators and are inactive.
     * @return List of users that are not administrators and are inactive.
     */
    @Override
    public List<Usuario> consultarUsuariosInactiveNoAdmins() {
        return usuarioRepository.findByActivoFalseAndIsAdminFalse();
    }

    /**
     * Update the information of a user.
     * @param id The ID of the user to update.
     * @param dto The new information of the user.
     * @return The updated user.
     */
    @Override
    public void actualizarInformacionUsuario(int id ,UsuarioDTO dto) {
        // Buscamos el usuario por su ID institucional.
        Usuario usuario = usuarioRepository.findByIdInstitucional(id);
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


    /**
     * Add a new user.
     * @param idInstitucional The institutional ID of the user.
     * @param nombre The name of the user.
     * @param apellido The last name of the user.
     * @param correoInstitucional The institutional email of the user.
     * @param isAdmin If the user is an administrator.
     */
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

    /**
     * Add a salon.
     * @param id The ID of the user that adds the salon.
     * @param mnemonico The mnemonic of the salon.
     * @param nombre The name of the salon.
     * @param descripcion The description of the salon.
     * @param ubicacion The location of the salon.
     * @param capacidad The capacity of the salon.
     * @param recursos The resources of the salon.
     */
    @Override
    public void agregarSalon(int id,String mnemonico, String nombre, String descripcion, String ubicacion, int capacidad,
            List<Recurso> recursos) {
        
        Administrador administrador = (Administrador) usuarioRepository.findByIdInstitucional(id);
        @SuppressWarnings("unused")
        Salon nuevoSalon = new Salon(mnemonico,nombre, descripcion, ubicacion, capacidad, recursos);
        usuarioRepository.save(administrador);

        
    }
}