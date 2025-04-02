package edu.eci.cvds.elysium.service;

import java.time.LocalDate;
import java.util.List;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Usuario;

public interface UsuarioService {

    /**
     * Consult a user by its institutional id
     * @param idInstitucional the user's institutional id
     * @return the user
     */
    Usuario consultarUsuario(int idInstitucional);

    /**
     * Method that allows to consult the users
     * @return List of users
     */
    List<Usuario> consultarUsuarios();

    /**
     * Method that allows to consult a user by its email
     * @param correo the user's email
     * @return the user
     */
    public Usuario consultarUsuarioPorCorreo(String correo);


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
    Usuario agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean isAdmin) throws ElysiumExceptions;

    /**
     * Method that allows to update the information of a user
     * @param id identifier of the user
     * @param dto DTO with the information to update
     * @throws ElysiumExceptions if the user doesn't exist or validation fails
     */
    void actualizarInformacionUsuario(int id, UsuarioDTO dto) throws ElysiumExceptions;    

    /**
     * Method that allows to add a new salon
     * @param id ID of the admin user
     * @param mnemonico mnemonic of the salon
     * @param nombre name of the salon
     * @param descripcion description of the salon
     * @param ubicacion location of the salon
     * @param capacidad capacity of the salon
     * @param recursos resources of the salon
     * @throws ElysiumExceptions if validation fails
     */
    void agregarSalon(int id, String mnemonico, String nombre, String descripcion, String ubicacion, Integer capacidad, List<Recurso> recursos) throws ElysiumExceptions;

    /**
     * Create a reservation
     * @param fechaReserva date of the reservation
     * @param hora hour of the reservation
     * @param diaSemana day of the week of the reservation
     * @param proposito purpose of the reservation
     * @param materia subject of the reservation
     * @param idSalon id of the salon
     * @param duracionBloque if the reservation is for a block of time
     * @param prioridad priority of the reservation
     * @param idInstitucional institutional id of the user
     * @throws ElysiumExceptions if validation fails
     */
    void crearReserva(LocalDate fechaReserva, double hora, DiaSemana diaSemana, String proposito, String materia, String idSalon, boolean duracionBloque, int prioridad, int idInstitucional) throws ElysiumExceptions;
    
    /**
     * List the reservations of a user
     * @param idInstitucional institutional id of the user
     * @return List of reservations
     * @throws ElysiumExceptions if validation fails
     */
    List<Reserva> listarReservas(int idInstitucional) throws ElysiumExceptions;
}
