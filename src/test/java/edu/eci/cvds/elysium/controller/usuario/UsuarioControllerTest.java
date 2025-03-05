package edu.eci.cvds.elysium.controller.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import edu.eci.cvds.elysium.model.usuario.Administrador;
import edu.eci.cvds.elysium.model.usuario.Usuario;
import edu.eci.cvds.elysium.service.usuario.UsuarioService;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarUsuario() {
        // Se crea un usuario concreto (Administrador) para simular el retorno del servicio
        Usuario usuarioEsperado = new Administrador(1, "Admin", "Apellido", "admin@example.com", true);
        when(usuarioService.consultarUsuario(1)).thenReturn(usuarioEsperado);

        // Se invoca el método del controlador
        Usuario usuarioObtenido = usuarioController.consultarUsuario(1);
        
        // Se verifica que se retorne el usuario esperado
        assertNotNull(usuarioObtenido);
        assertEquals(usuarioEsperado, usuarioObtenido);
        verify(usuarioService).consultarUsuario(1);
    }
}
