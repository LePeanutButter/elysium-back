package edu.eci.cvds.elysium.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    // Este es el servicio que se va a inyectar
    @Qualifier("usuarioServiceImpl")
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable int id) {
        return usuarioService.consultarUsuario(id);
    }
}
