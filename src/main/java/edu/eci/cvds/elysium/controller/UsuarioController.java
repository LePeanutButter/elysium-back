package edu.eci.cvds.elysium.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Endpoint para consultar un usuario por su identificador.
     * 
     * @param id Identificador del usuario a consultar (proveniente de la URL).
     * @return Usuario con el identificador dado.
     */
    @Operation(summary = "Consultar usuario", description = "Endpoint para consultar un usuario por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario retornado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> consultarUsuario(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.consultarUsuario(id);
            if (usuario == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "Usuario no encontrado con ID: " + id);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al consultar usuario: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para consultar un usuario por su correo.
     *
     * @param correo Identificador del usuario a consultar (proveniente de la URL).
     * @return Usuario con el identificador dado.
     */
    @GetMapping("/correo/{correo}")
    @Operation(summary = "Consultar usuario", description = "Endpoint para consultar un usuario por su correo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario retornado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> consultarUsuarioPorCorreo(@PathVariable String correo) {
        try {
            Usuario usuario = usuarioService.consultarUsuarioPorCorreo(correo);
            if (usuario == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "Usuario no encontrado con correo: " + correo);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al consultar usuario por correo: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint unificado para consultar usuarios.
     * Se pueden usar los parámetros opcionales:
     * - activo: true/false para filtrar por estado activo.
     * - isAdmin: true/false para filtrar por rol de administrador.
     * 
     * Ejemplos:
     * GET /api/administrador -> Retorna todos los usuarios.
     * GET /api/administrador?activo=true -> Retorna usuarios activos.
     * GET /api/administrador?activo=false -> Retorna usuarios inactivos.
     * GET /api/administrador?isAdmin=true -> Retorna usuarios que son
     * administradores.
     * GET /api/administrador?activo=true&isAdmin=false -> Retorna usuarios activos
     * que no son administradores.
     * 
     * @param activo  Valor opcional para filtrar usuarios por estado activo (true)
     *                o inactivo (false).
     * @param isAdmin Valor opcional para filtrar usuarios por rol de administrador
     *                (true) o no administrador (false).
     */

    @SuppressWarnings("null")
    @GetMapping("")
    @Operation(summary = "Consultar usuarios", description = "Endpoint unificado para consultar usuarios, pudiendo filtrar por estado activo e indicador de rol de administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<?> consultarUsuarios(
            @Parameter(description = "Valor opcional para filtrar usuarios por estado activo (true) o inactivo (false)", example = "true") @RequestParam(required = false) Boolean activo,
            @Parameter(description = "Valor opcional para filtrar usuarios por rol administrador (true) o no administrador (false)", example = "false") @RequestParam(required = false) Boolean isAdmin) {
        try {
            List<Usuario> usuarios;
            
            if (activo == null && isAdmin == null) {
                usuarios = usuarioService.consultarUsuarios();
            } else if (activo != null && isAdmin == null) {
                usuarios = activo ? usuarioService.consultarUsuariosActivos() : usuarioService.consultarUsuariosInactivos();
            } else if (activo == null && isAdmin != null) {
                usuarios = isAdmin ? usuarioService.consultarUsuariosAdmins() : usuarioService.consultarUsuariosActiveNoAdmins();
            } else if (activo && isAdmin) {
                usuarios = usuarioService.consultarUsuariosActiveAdmins();
            } else if (activo && !isAdmin) {
                usuarios = usuarioService.consultarUsuariosActiveNoAdmins();
            } else if (!activo && isAdmin) {
                usuarios = usuarioService.consultarUsuariosInactiveAdmins();
            } else {
                usuarios = usuarioService.consultarUsuariosInactiveNoAdmins();
            }
            
            return ResponseEntity.ok(usuarios);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al consultar usuarios: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para agregar un nuevo usuario.
     *
     * Recibe un objeto de tipo UsuarioDTO en el cuerpo de la solicitud, que
     * contiene todos los datos
     * necesarios para la creación de un usuario, incluyendo identificador, nombre,
     * apellido, correo y
     * bandera que indica si es administrador.
     *
     * @param usuarioDTO Objeto de transferencia de datos que encapsula la
     *                   información del usuario.
     * @return ResponseEntity con código 201 si se crea correctamente, o 400 en caso
     *         de datos inválidos.
     */

    @PostMapping
    @Operation(summary = "Agregar usuario", description = "Endpoint para agregar un nuevo usuario. El identificador es generado automáticamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<?> agregarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioCreado = usuarioService.agregarUsuario(usuarioDTO.getId(), usuarioDTO.getNombre(),
                    usuarioDTO.getApellido(), usuarioDTO.getCorreo(), usuarioDTO.getIsAdmin(), usuarioDTO.getPassword());
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "SUCCESS");
            response.put("message", "Usuario creado correctamente");
            response.put("usuario", usuarioCreado);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ElysiumExceptions ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al crear usuario: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Endpoint para actualizar la información de un usuario.
     * 
     * Recibe un objeto de tipo ActualizarUsuarioDTO en el cuerpo de la solicitud,
     * que contienen los datos para hacer la actualización parcial. Los que no se
     * quieren actualizar se dejan en nulos.
     * 
     * @param id Identificador del usuario a actualizar (proveniente de la URL).
     * 
     * @param actualizarUsuarioDTO Objeto que contiene la información a actualizar.
     * 
     * @return {@code ResponseEntity} con código 204 (No Content) si se actualiza
     * correctamente, o 400 en caso de error.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar información de usuario", description = "Endpoint para actualizar parcialmente la información de un usuario. Los campos no provistos no se modifican.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<?> actualizarInformacionUsuario(@PathVariable int id,
            @RequestBody UsuarioDTO actualizarUsuarioDTO) {
        try {
            Usuario usuario = usuarioService.consultarUsuario(id);
            if (usuario == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "Usuario no encontrado con ID: " + id);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            
            usuarioService.actualizarInformacionUsuario(id, actualizarUsuarioDTO);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "SUCCESS");
            response.put("message", "Usuario actualizado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al actualizar usuario: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para agregar un salón.
     * 
     * @param id    administrador id
     * @param salon salon to add
     * @return ResponseEntity with code 204 if the salon is added correctly, or 400
     *         in case of invalid data.
     */
    @PostMapping("/salones/{id}")
    @Operation(summary = "Agregar salón", description = "Endpoint para agregar un nuevo salón a la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salón agregado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "403", description = "Usuario no es administrador")
    })
    public ResponseEntity<?> agregarSalon(@PathVariable int id, @Valid @RequestBody SalonDTO salondto) {
        try {
            Usuario usuario = usuarioService.consultarUsuario(id);
            if (usuario == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "Usuario no encontrado con ID: " + id);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            
            if (!usuario.getIsAdmin()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "El usuario no tiene permisos de administrador");
                return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
            }
            
            usuarioService.agregarSalon(
                    id,
                    salondto.getMnemonic(),
                    salondto.getName(),
                    salondto.getDescription(),
                    salondto.getLocation(),
                    salondto.getCapacity(),
                    salondto.getResources());
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "SUCCESS");
            response.put("message", "Salón agregado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al agregar salón: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para crear una reserva.
     * 
     * @param id         Identificador del usuario que realiza la reserva.
     * @param reservaDTO Información de la reserva a crear.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @Operation(summary = "Crear reserva", description = "Endpoint para crear una reserva.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("{id}/reservas")
    public ResponseEntity<?> crearReserva(@PathVariable int id, @RequestBody ReservaDTO reservaDTO) {
        try {
            Usuario usuario = usuarioService.consultarUsuario(id);
            if (usuario == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "Usuario no encontrado con ID: " + id);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            
            usuarioService.crearReserva(
                reservaDTO.getFechaReserva(), 
                reservaDTO.getHora(), 
                reservaDTO.getDiaSemana(),
                reservaDTO.getProposito(), 
                reservaDTO.getMateria(), 
                reservaDTO.getIdSalon(),
                reservaDTO.isDuracionBloque(), 
                reservaDTO.getPrioridad(), 
                id
            );
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "SUCCESS");
            response.put("message", "Reserva creada correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al crear reserva: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para listar las reservas de un usuario.
     * 
     * @param id Identificador del usuario a consultar.
     * @return ResponseEntity con un mensaje de éxito.
     */
    @Operation(summary = "Listar reservas", description = "Endpoint para listar las reservas de un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas listadas correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("{id}/reservas")
    public ResponseEntity<?> listarReservas(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.consultarUsuario(id);
            if (usuario == null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("status", "ERROR");
                errorResponse.put("message", "Usuario no encontrado con ID: " + id);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            
            List<Reserva> reservas = usuarioService.listarReservas(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "SUCCESS");
            response.put("message", "Reservas consultadas correctamente");
            response.put("reservas", reservas);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", "Error al listar reservas: " + ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}