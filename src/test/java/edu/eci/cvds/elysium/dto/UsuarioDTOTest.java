package edu.eci.cvds.elysium.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;




public class UsuarioDTOTest {

    @Test
    public void testDefaultValues() {
        UsuarioDTO usuario = new UsuarioDTO();
        assertNull(usuario.getId(), "Default id should be null");
        assertNull(usuario.getNombre(), "Default nombre should be null");
        assertNull(usuario.getApellido(), "Default apellido should be null");
        assertNull(usuario.getCorreo(), "Default correo should be null");
        assertNull(usuario.getIsAdmin(), "Default isAdmin should be null");
        assertNull(usuario.getActivo(), "Default activo should be null");
    }

    @Test
    public void testGetters() throws NoSuchFieldException, IllegalAccessException {
        UsuarioDTO usuario = new UsuarioDTO();

        Field idField = UsuarioDTO.class.getDeclaredField("idInstitucional");
        Field nombreField = UsuarioDTO.class.getDeclaredField("nombre");
        Field apellidoField = UsuarioDTO.class.getDeclaredField("apellido");
        Field correoField = UsuarioDTO.class.getDeclaredField("correo");
        Field isAdminField = UsuarioDTO.class.getDeclaredField("isAdmin");
        Field activoField = UsuarioDTO.class.getDeclaredField("activo");

        idField.setAccessible(true);
        nombreField.setAccessible(true);
        apellidoField.setAccessible(true);
        correoField.setAccessible(true);
        isAdminField.setAccessible(true);
        activoField.setAccessible(true);

        idField.set(usuario, 12345);
        nombreField.set(usuario, "Juan");
        apellidoField.set(usuario, "Perez");
        correoField.set(usuario, "Juan.Perez@escuelaing.edu.co");
        isAdminField.set(usuario, true);
        activoField.set(usuario, true);

        assertEquals(12345, usuario.getId());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Perez", usuario.getApellido());
        assertEquals("Juan.Perez@escuelaing.edu.co", usuario.getCorreo());
        assertTrue(usuario.getIsAdmin());
        assertTrue(usuario.getActivo());
    }
}