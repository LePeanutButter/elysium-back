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
     * @throws ElysiumExceptions if the user doesn't exist or validation fails
     */
    @Override
    public void actualizarInformacionUsuario(int id, UsuarioDTO dto) throws ElysiumExceptions {
        // Validate user exists
        Usuario usuario = usuarioRepository.findByIdInstitucional(id);
        if (usuario == null) {
            throw new ElysiumExceptions(ElysiumExceptions.USUARIO_NO_ENCONTRADO);
        }
        
        // Validate email format if provided
        if (dto.getCorreo() != null) {
            String emailRegex = "^[a-zA-Z]+\\.[a-zA-Z]+@escuelaing\\.edu\\.co$";
            if (!dto.getCorreo().matches(emailRegex)) {
                throw new ElysiumExceptions(ElysiumExceptions.CORREO_NO_VALIDO);
            }
            
            // Check if email already exists for another user
            Usuario userWithSameEmail = usuarioRepository.findByCorreoInstitucional(dto.getCorreo());
            if (userWithSameEmail != null && userWithSameEmail.getIdInstitucional() != id) {
                throw new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_CORREO);
            }
        }
        
        // Update fields only if they are not null
        if (dto.getNombre() != null) {
            usuario.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            usuario.setApellido(dto.getApellido());
        }
        if (dto.getCorreo() != null) {
            usuario.setCorreoInstitucional(dto.getCorreo());
        }
        if (dto.getIsAdmin() != null) {
            usuario.setAdmin(dto.getIsAdmin());
        }
        if (dto.getActivo() != null) {
            usuario.setActivo(dto.getActivo());
        }   

        // Save the updated user
        usuarioRepository.save(usuario);
    }  

    /**
     * Add a new user.
     * @param idInstitucional The institutional ID of the user.
     * @param nombre The name of the user.
     * @param apellido The last name of the user.
     * @param correoInstitucional The institutional email of the user.
     * @param isAdmin If the user is an administrator.
     * @throws ElysiumExceptions if validation fails
     */
    @Override
    public Usuario agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional,
            boolean isAdmin) throws ElysiumExceptions {
        
        // Validate all required fields
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ElysiumExceptions(ElysiumExceptions.NOMBRE_NO_VALIDO);
        }
        
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new ElysiumExceptions(ElysiumExceptions.APELLIDO_NO_VALIDO);
        }

        // Validar ID institucional
        if (idInstitucional == 0 || String.valueOf(idInstitucional).length() != 10) {
            throw new ElysiumExceptions(ElysiumExceptions.ID_NO_VALIDO);
        }

        // Validar formato del correo
        String emailRegex = "^[a-zA-Z]+\\.[a-zA-Z]+@escuelaing\\.edu\\.co$";
        if (correoInstitucional == null || !correoInstitucional.matches(emailRegex)) {
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

        // Crear y guardar usuario con valores consistentes
        Usuario nuevoUsuario = new Usuario(idInstitucional, nombre, apellido, correoInstitucional, true, isAdmin);
        usuarioRepository.save(nuevoUsuario);

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
     * @throws ElysiumExceptions if validation fails
     */
    @Override
    public void agregarSalon(int id, String mnemonico, String nombre, String descripcion, String ubicacion, Integer capacidad,
            List<Recurso> recursos) throws ElysiumExceptions {
        
        // Validate user exists and is admin
        Usuario usuario = usuarioRepository.findByIdInstitucional(id);
        if (usuario == null) {
            throw new ElysiumExceptions(ElysiumExceptions.USUARIO_NO_ENCONTRADO);
        }
        
        if (!usuario.getIsAdmin()) {
            throw new ElysiumExceptions(ElysiumExceptions.NO_ES_ADMIN);
        }
        
        // Validate salon data
        if (mnemonico == null || mnemonico.trim().isEmpty()) {
            throw new ElysiumExceptions(ElysiumExceptions.MNEMONICO_NO_VALIDO);
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ElysiumExceptions(ElysiumExceptions.NOMBRE_SALON_NO_VALIDO);
        }
        
        if (ubicacion == null || ubicacion.trim().isEmpty()) {
            throw new ElysiumExceptions(ElysiumExceptions.UBICACION_NO_VALIDA);
        }
        
        if (capacidad == null || capacidad <= 0) {
            throw new ElysiumExceptions(ElysiumExceptions.CAPACIDAD_NO_VALIDA);
        }
        
        // Check if salon already exists
        if (salonRepository.existsById(mnemonico)) {
            throw new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_SALON);
        }
        
        // Create and save salon
        Salon nuevoSalon = new Salon(nombre, mnemonico, descripcion, ubicacion, capacidad, recursos);
        salonRepository.save(nuevoSalon);
    }

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
    @Override
    public void crearReserva(LocalDate fechaReserva, double hora, DiaSemana diaSemana, String proposito, String materia, 
            String idSalon, boolean duracionBloque, int prioridad, int idInstitucional) throws ElysiumExceptions {    
        
        // Validate user exists
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario == null) {
            throw new ElysiumExceptions(ElysiumExceptions.USUARIO_NO_ENCONTRADO);
        }
        
        // Validate salon exists
        if (!salonRepository.existsById(idSalon)) {
            throw new ElysiumExceptions(ElysiumExceptions.SALON_NO_ENCONTRADO);
        }
        
        // Validate date is not in the past
        if (fechaReserva != null && fechaReserva.isBefore(LocalDate.now())) {
            throw new ElysiumExceptions(ElysiumExceptions.FECHA_PASADA);
        }
        
        // Validate hour is valid (between 7.0 and 19.0)
        if (hora < 7.0 || hora > 19.0) {
            throw new ElysiumExceptions(ElysiumExceptions.HORA_NO_VALIDA);
        }
        
        // Validate purpose is provided
        if (proposito == null || proposito.trim().isEmpty()) {
            throw new ElysiumExceptions(ElysiumExceptions.PROPOSITO_NO_VALIDO);
        }
        
        // If validation passes, create reservation
        reservaService.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);            
    }

    /**
     * List the reservations of a user
     * @param idInstitucional institutional id of the user
     * @return List of reservations
     * @throws ElysiumExceptions if validation fails
     */
    @Override
    public List<Reserva> listarReservas(int idInstitucional) throws ElysiumExceptions {
        // Validate user exists
        Usuario usuario = usuarioRepository.findByIdInstitucional(idInstitucional);
        if (usuario == null) {
            throw new ElysiumExceptions(ElysiumExceptions.USUARIO_NO_ENCONTRADO);
        }
        
        return reservaService.consultarReservasPorUsuario(idInstitucional);
    }
}