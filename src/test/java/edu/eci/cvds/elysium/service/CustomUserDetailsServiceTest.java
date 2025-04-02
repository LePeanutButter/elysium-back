package edu.eci.cvds.elysium.service;

import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomUserDetailsServiceTest {

    private UsuarioRepository usuarioRepository;
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        // Initialize the mock repository
        usuarioRepository = mock(UsuarioRepository.class);
        // Initialize the service with the mock repository
        customUserDetailsService = new CustomUserDetailsService(usuarioRepository);
    }

    @Test
    public void testLoadUserByUsernameFound() {
        // Arrange
        String correo = "test@domain.com";
        String password = "secret";

        Usuario dummyUser = new Usuario(1000096182, "Ander", "Snache", "andersson.sanchez@escuelaing.edu.co", false, false);
        dummyUser.setCorreoInstitucional(correo);
        dummyUser.setPassword(password);

        when(usuarioRepository.findByCorreoInstitucional(correo)).thenReturn(dummyUser);

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(correo);
        
        // Assert
        assertEquals(correo, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
    }

    @Test
    public void testLoadUserByUsernameNotFound() {
        // Arrange
        String correo = "nonexistent@domain.com";
        when(usuarioRepository.findByCorreoInstitucional(correo)).thenReturn(null);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(correo);
        });
    }
}