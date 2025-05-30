package edu.eci.cvds.elysium.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class UsuarioTest {
    
    private Usuario usuario;
    
    @BeforeEach
    public void setUp() {
        // Create a sample Usuario object before each test.
        usuario = new Usuario(12345, "John", "Doe", "john.doe@institution.edu", true, false);
        usuario.setPassword("secret");
    }
    
    @Test
    public void testConstructorAndGetters() {
        assertEquals(12345, usuario.getIdInstitucional());
        assertEquals("John", usuario.getNombre());
        assertEquals("Doe", usuario.getApellido());
        assertEquals("john.doe@institution.edu", usuario.getCorreoInstitucional());
        assertTrue(usuario.isActivo());
        assertFalse(usuario.getIsAdmin());
        assertEquals("secret", usuario.getPassword());
    }
    
    @Test
    public void testSetters() {
        usuario.setIdInstitucional(54321);
        usuario.setNombre("Jane");
        usuario.setApellido("Smith");
        usuario.setCorreoInstitucional("jane.smith@institution.edu");
        usuario.setActivo(false);
        usuario.setAdmin(true);
        usuario.setPassword("newpassword");
        
        assertEquals(54321, usuario.getIdInstitucional());
        assertEquals("Jane", usuario.getNombre());
        assertEquals("Smith", usuario.getApellido());
        assertEquals("jane.smith@institution.edu", usuario.getCorreoInstitucional());
        assertFalse(usuario.isActivo());
        assertTrue(usuario.getIsAdmin());
        assertEquals("newpassword", usuario.getPassword());
    }
    
    @Test
    public void testAdminSetter() {
        // Initially false, then set to true and then false again.
        assertFalse(usuario.getIsAdmin());
        usuario.setAdmin(true);
        assertTrue(usuario.getIsAdmin());
        usuario.setAdmin(false);
        assertFalse(usuario.getIsAdmin());
    }
}