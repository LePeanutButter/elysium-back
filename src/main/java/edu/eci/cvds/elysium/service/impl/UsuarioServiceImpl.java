package edu.eci.cvds.elysium.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.SalonRepository;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.ReservaService;
import edu.eci.cvds.elysium.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Lazy
    @Autowired
    private ReservaService reservaService;

    @Autowired
    private SalonRepository salonRepository;

    /**
     * Consult a user by its institutional id.
     * @param idInstitucional Institutional id of the user to consult.
     * @return User with the given id.
     */
    @Override
    public Usuario consultarUsuario(int idInstitucional) {
        // Si has definido findByIdInstitucional en el repository:
        return usuarioRepository.findByIdInstitucional(idInstitucional);
    }

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

    @Override
    public Usuario consultarUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreoInstitucional(correo);
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
    public Usuario agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional,
            boolean isAdmin) throws ElysiumExceptions {
        

            // Validar ID institucional
            if (idInstitucional == 0 || String.valueOf(idInstitucional).length() != 10) {
                throw new ElysiumExceptions(ElysiumExceptions.ID_NO_VALIDO);
            }

            // Validar formato del correo
            String emailRegex = "^[a-zA-Z]+\\.[a-zA-Z]+@escuelaing\\.edu\\.co$";
            if (correoInstitucional==null || !correoInstitucional.matches(emailRegex)) {
                throw new ElysiumExceptions(ElysiumExceptions.CORREO_NO_VALIDO);
            }

            // Verificar si el usuario ya existe por ID
            if (usuarioRepository.existsByIdInstitucional(idInstitucional)) {
                throw new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_USUARIO);
            }

            // Verificar si el correo ya está registrado
            if (usuarioRepository.existsByCorreoInstitucional(correoInstitucional)) {
                throw new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_CORREO);
            }

            // Crear y guardar usuario
            if (isAdmin) {
                Usuario nuevoUsuario = new Usuario(idInstitucional, nombre, apellido, correoInstitucional, true,true);
                usuarioRepository.save(nuevoUsuario);
            } else {
                Usuario nuevoUsuario = new Usuario(idInstitucional, nombre, apellido, correoInstitucional, true,true);
                usuarioRepository.save(nuevoUsuario);
            }

        return usuarioRepository.findByIdInstitucional(idInstitucional);
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
    @SuppressWarnings("unused")
    @Override
    public void agregarSalon(int id, String mnemonico, String nombre, String descripcion, String ubicacion, Integer capacidad,
            List<Recurso> recursos) {
        
        Usuario usuario = usuarioRepository.findByIdInstitucional(id);
        
        // Verificar si el usuario existe y es un Administrador
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe");
        }
        
        if (!(usuario.getIsAdmin())) {
            throw new IllegalArgumentException("El usuario con ID " + id + " no es un administrador");
        }
        
        // AÑADIR ESTA VERIFICACIÓN
        if (capacidad == null) {
            throw new IllegalArgumentException("La capacidad no puede ser nula");
        }
        
        Salon nuevoSalon = new Salon(nombre,mnemonico, descripcion, ubicacion, capacidad, recursos);
        
        // Save the salon in the database through the repository
        salonRepository.save(nuevoSalon);
    }

    /**
     * Create a reservation
     * @param fecha date of the reservation
     * @param hora hour of the reservation
     * @param diaSemana day of the week of the reservation
     * @param proposito purpose of the reservation
     * @param idSalon id of the salon
     * @param duracionBloque if the reservation is for a block of time
     * @param prioridad priority of the reservation
     * @param idInstitucional institutional id of the user
     * @throws ExcepcionServiciosElysium if the user is not an Estandar
     */
    @Override
    public void crearReserva(LocalDate fechaReserva,double hora, DiaSemana diaSemana, String proposito,String materia, String idSalon, boolean duracionBloque, int prioridad, int idInstitucional) {    
        // Se utiliza el método definido en el repository para Mongo
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null) {           
            reservaService.crearReserva(fechaReserva,hora, diaSemana, proposito, materia,idSalon, duracionBloque, prioridad, idInstitucional);            
        }  
    }

    /**
     * List the reservations of a user
     * @param idInstitucional institutional id of the user
     */
    @Override
    public List<Reserva> listarReservas(int idInstitucional) {
        // Se utiliza el método definido en el repository para Mongo
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario != null) {
            return reservaService.consultarReservasPorUsuario(idInstitucional);
        }
        return new java.util.ArrayList<>();
    }
}