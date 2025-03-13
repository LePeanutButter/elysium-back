package edu.eci.cvds.elysium.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController extends UsuarioController {

    @Autowired
    private AdministradorService administradorService;

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

    @GetMapping("")
    @Operation(summary = "Consultar usuarios", description = "Endpoint unificado para consultar usuarios, pudiendo filtrar por estado activo e indicador de rol de administrador.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public List<Usuario> consultarUsuarios(
            @Parameter(description = "Valor opcional para filtrar usuarios por estado activo (true) o inactivo (false)", example = "true") @RequestParam(required = false) Boolean activo,

            @Parameter(description = "Valor opcional para filtrar usuarios por rol administrador (true) o no administrador (false)", example = "false") @RequestParam(required = false) Boolean isAdmin) {
        // Si no se pasan filtros, retorna todos
        if (activo == null && isAdmin == null) {
            return administradorService.consultarUsuarios();
        }
        // Si se filtra solo por estado activo/inactivo
        if (activo != null && isAdmin == null) {
            return activo
                    ? administradorService.consultarUsuariosActivos()
                    : administradorService.consultarUsuariosInactivos();
        }
        // Si se filtra solo por rol
        if (activo == null && isAdmin != null) {
            return isAdmin
                    ? administradorService.consultarUsuariosAdmins()
                    : administradorService.consultarUsuariosActiveNoAdmins(); // O se pueden combinar activos e
                                                                              // inactivos
        }
        // Si se filtran ambos
        if (activo && isAdmin) {
            return administradorService.consultarUsuariosActiveAdmins();
        } else if (activo && !isAdmin) {
            return administradorService.consultarUsuariosActiveNoAdmins();
        } else if (!activo && isAdmin) {
            return administradorService.consultarUsuariosInactiveAdmins();
        } else { // !activo && !isAdmin
            return administradorService.consultarUsuariosInactiveNoAdmins();
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

    @PostMapping("/usuario")
    @Operation(summary = "Agregar usuario", description = "Endpoint para agregar un nuevo usuario. El identificador es generado automáticamente.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario agregado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })

    public ResponseEntity<Void> agregarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        administradorService.agregarUsuario(usuarioDTO.getId(), usuarioDTO.getNombre(),
                usuarioDTO.getApellido(), usuarioDTO.getCorreo(), usuarioDTO.getIsAdmin());
        return ResponseEntity.status(201).build();
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
    @PatchMapping("/usuario/{id}")
    @Operation(summary = "Actualizar información de usuario", description = "Endpoint para actualizar parcialmente la información de un usuario. Los campos no provistos no se modifican.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Void> actualizarInformacionUsuario(@PathVariable int id,
            @RequestBody UsuarioDTO actualizarUsuarioDTO) {        
        administradorService.actualizarInformacionUsuario(actualizarUsuarioDTO);
        return ResponseEntity.noContent().build();
    }
}
