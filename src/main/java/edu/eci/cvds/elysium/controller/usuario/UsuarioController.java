package edu.eci.cvds.elysium.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.dto.usuario.ActualizarUsuarioDTO;
import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.AdministradorService;
import edu.eci.cvds.elysium.service.usuario.EstandarService;
import edu.eci.cvds.elysium.service.usuario.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    // Este es el servicio que se va a inyectar
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;
    @Autowired
    private EstandarService estandarService;
    // @Autowired
    // private ReservaService reservaService;
    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable int id) {
        return usuarioService.consultarUsuario(id);
    }
    
    //Estandar section
    //Falta considerar en donde va el mnemonico
    @PostMapping("/estandar/crearReserva")
    public ResponseEntity<String> crearReserva(@RequestBody
    ReservaDTO reservaDTO){
        estandarService.crearReserva(reservaDTO.getIdReserva(), reservaDTO.getFechaReserva(), reservaDTO.getDiaSemana(), reservaDTO.getProposito(), reservaDTO.getIdSalon(),reservaDTO.isDuracionBloque());
        return ResponseEntity.ok("Reserva creada");
    }

    // implentar endpoint para listar reservas y que sea accesible solo para usuarios estandar

    //Admin section
    /**
     * Endpoint unificado para consultar usuarios.
     * Se pueden usar los parámetros opcionales:
     * - activo: true/false para filtrar por estado activo.
     * - isAdmin: true/false para filtrar por rol de administrador.
     * 
     * Ejemplos:
     * GET /api/users -> retorna todos los usuarios.
     * GET /api/users?activo=true -> usuarios activos.
     * GET /api/users?activo=false -> usuarios inactivos.
     * GET /api/users?isAdmin=true -> usuarios que son administradores.
     * GET /api/users?activo=true&isAdmin=false -> usuarios activos que no son
     * administradores.
     */
    @GetMapping("administrador")
    public List<Usuario> consultarUsuarios(
            @RequestParam(required = false) Boolean activo,
            @RequestParam(required = false) Boolean isAdmin) {
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

    @PostMapping("administrador/agregarUsuario")
    public void agregarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        administradorService.agregarUsuario(usuarioDTO.getId(), usuarioDTO.getNombre(),
                usuarioDTO.getApellido(), usuarioDTO.getCorreo(), usuarioDTO.getIsAdmin());
    }

    @PatchMapping("administrador/{id}")
    public ResponseEntity<Void> actualizarInformacionUsuario(@PathVariable int id, 
            @RequestBody ActualizarUsuarioDTO actualizarUsuarioDTO) {
        // Opcional: Validar que el id en la URL y el dto coincidan.
        // if(!actualizarUsuarioDTO.getIdInstitucional().equals(id)){
        //     return ResponseEntity.badRequest().build();
        // }
        administradorService.actualizarInformacionUsuario(actualizarUsuarioDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("administrador/{id}/deshabilitarUsuario")
    public ResponseEntity<String> deshabilitarUsuario(@PathVariable int id) {
        administradorService.deshabilitarUsuario(id);
        return ResponseEntity.ok("Usuario deshabilitado exitosamente");
    }

    @PutMapping("administrador/{id}/habilitarUsuario")
    public ResponseEntity<String> habilitarUsuario(@PathVariable int id) {
        administradorService.habilitarUsuario(id);
        return ResponseEntity.ok("Usuario habilitado exitosamente");
    }

    @PutMapping("administrador/{id}/hacerAdmin")
    public ResponseEntity<String> hacerAdmin(@PathVariable int id) {
        administradorService.hacerAdmin(id);
        return ResponseEntity.ok("Usuario ahora es administrador");
    }

    @PutMapping("administrador/{id}/quitarAdmin")
    public ResponseEntity<String> quitarAdmin(@PathVariable int id) {
        administradorService.quitarAdmin(id);
        return ResponseEntity.ok("Usuario ya no es administrador");
    }

}
