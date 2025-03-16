package edu.eci.cvds.elysium.service.usuario;

import java.util.List;

import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Usuario;

/**
 * Interfaz que define las operaciones de servicio para administradores.
 * <p>
 * Esta interfaz hereda de UsuarioService y proporciona métodos para la consulta y gestión
 * de usuarios, permitiendo distinguir entre administradores y usuarios no administradores,
 * así como entre usuarios activos e inactivos.
 * </p>
 *
 * Métodos incluidos:
 * <ul>
 *   <li>{@link #consultarUsuarios()} - Retorna una lista de todos los usuarios.</li>
 *   <li>{@link #consultarUsuariosActivos()} - Retorna una lista de usuarios activos.</li>
 *   <li>{@link #consultarUsuariosInactivos()} - Retorna una lista de usuarios inactivos.</li>
 *   <li>{@link #consultarUsuariosAdmins()} - Retorna una lista de usuarios administradores.</li>
 *   <li>{@link #consultarUsuariosActiveAdmins()} - Retorna una lista de administradores activos.</li>
 *   <li>{@link #consultarUsuariosInactiveAdmins()} - Retorna una lista de administradores inactivos.</li>
 *   <li>{@link #consultarUsuariosActiveNoAdmins()} - Retorna una lista de usuarios activos que no son administradores.</li>
 *   <li>{@link #consultarUsuariosInactiveNoAdmins()} - Retorna una lista de usuarios inactivos que no son administradores.</li>
 *   <li>{@link #agregarUsuario(int, String, String, String, boolean)} - Agrega un nuevo usuario con la información proporcionada (identificador institucional, nombre, apellido, correo institucional y flag de administrador).</li>
 *   <li>{@link #actualizarInformacionUsuario(UsuarioDTO)} - Actualiza la información de un usuario a partir de un objeto de transferencia de datos (DTO).</li>
 * </ul>
 *
 * @see UsuarioService
 */
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
    void actualizarInformacionUsuario(int id, UsuarioDTO dto);    
}
