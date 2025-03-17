package edu.eci.cvds.elysium.service.usuario;

import java.util.List;

import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.usuario.Usuario;

/**
 * Interface AdministradorService that allows the implementation of the methods of the service of the administrator
 */
public interface AdministradorService extends UsuarioService {

    /**
     * Method that allows to consult the users
     * @return List of users
     */
    List<Usuario> consultarUsuarios();

    /**
     * Method that allows to consult the active users
     * @return List of active users
     */
    List<Usuario> consultarUsuariosActivos();

    /**
     * Method that allows to consult the inactive users
     * @return List of inactive users
     */
    List<Usuario> consultarUsuariosInactivos();

    /**
     * Method that allows to consult the users that are administrators
     * @return List of administrators
     */
    List<Usuario> consultarUsuariosAdmins();

    /**
     * Method that allows to consult the active users that are administrators
     * @return List of active administrators
     */
    List<Usuario> consultarUsuariosActiveAdmins();

    /**
     * Method that allows to consult the inactive users that are administrators
     * @return List of inactive administrators
     */
    List<Usuario> consultarUsuariosInactiveAdmins();

    /**
     * Method that allows to consult the users that are not administrators
     * @return List of users that are not administrators
     */
    List<Usuario> consultarUsuariosActiveNoAdmins();

    /**
     * Method that allows to consult the active users that are not administrators
     * @return List of active users that are not administrators
     */
    List<Usuario> consultarUsuariosInactiveNoAdmins();

    
    /**
     * Method that allows to add a user
     * @param idInstitucional identifier of the user
     * @param nombre name of the user
     * @param apellido last name of the user
     * @param correoInstitucional institutional email of the user
     * @param isAdmin boolean that indicates if the user is an administrator
     */
    void agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean isAdmin);

    /**
     * Method that allows to update the information of a user
     * @param id identifier of the user
     * @param dto DTO with the information to update
     */
    void actualizarInformacionUsuario(int id, UsuarioDTO dto);    

    /**
     * Method that allows to add a new salon
     * @param mnemonico mnemonic of the salon
     * @param nombre name of the salon
     * @param descripcion description of the salon
     * @param ubicacion location of the salon
     * @param capacidad capacity of the salon
     * @param recursos resources of the salon
     */
    void agregarSalon(int id,String mnemonico, String nombre, String descripcion,String ubicacion, int capacidad, List<Recurso> recursos);
}
