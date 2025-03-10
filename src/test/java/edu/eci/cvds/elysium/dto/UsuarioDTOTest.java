package edu.eci.cvds.elysium.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.eci.cvds.elysium.dto.usuario.UsuarioDTO;

public class UsuarioDTOTest {

    @Test
    public void testConstructorAndGetters() {
        int expectedId = 1;
        String expectedNombre = "Juan";
        String expectedApellido = "Perez";
        String expectedCorreo = "juan.perez@example.com";
        boolean expectedIsAdmin = true;

        UsuarioDTO usuario = new UsuarioDTO(expectedId, expectedNombre, expectedApellido, expectedCorreo, expectedIsAdmin);

        assertEquals(expectedId, usuario.getId());
        assertEquals(expectedNombre, usuario.getNombre());
        assertEquals(expectedApellido, usuario.getApellido());
        assertEquals(expectedCorreo, usuario.getCorreo());
        assertEquals(expectedIsAdmin, usuario.getIsAdmin());
    }
    
    @Test
    public void testNonAdminUser() {
        int expectedId = 2;
        String expectedNombre = "Ana";
        String expectedApellido = "Gomez";
        String expectedCorreo = "ana.gomez@example.com";
        boolean expectedIsAdmin = false;
        
        UsuarioDTO usuario = new UsuarioDTO(expectedId, expectedNombre, expectedApellido, expectedCorreo, expectedIsAdmin);

        assertEquals(expectedId, usuario.getId());
        assertEquals(expectedNombre, usuario.getNombre());
        assertEquals(expectedApellido, usuario.getApellido());
        assertEquals(expectedCorreo, usuario.getCorreo());
        assertFalse(usuario.getIsAdmin());
    }
}