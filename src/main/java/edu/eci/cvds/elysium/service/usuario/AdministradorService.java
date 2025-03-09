package edu.eci.cvds.elysium.service.usuario;

import java.util.List;

import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Usuario;

public interface AdministradorService extends UsuarioService {
    List<Usuario> consultarUsuarios();
    List<Usuario> consultarUsuariosActivos();
    List<Usuario> consultarUsuariosInactivos();
    List<Usuario> consultarUsuariosAdmins();
    List<Usuario> consultarUsuariosActiveAdmins();
    List<Usuario> consultarUsuariosInactiveAdmins();
    List<Usuario> consultarUsuariosActiveNoAdmins();
    List<Usuario> consultarUsuariosInactiveNoAdmins();
    void agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean isAdmin);
    void actualizarInformacionUsuario(ActualizarUsuarioDTO dto);
    void habilitarUsuario(int idInstitucional);
    void deshabilitarUsuario(int idInstitucional);
    void hacerAdmin(int id);
    void quitarAdmin(int id);
    // void añadirSalon(int adminId, String nombre, String ubicacion, int capacidad);
}
