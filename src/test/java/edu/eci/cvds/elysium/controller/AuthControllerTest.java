package edu.eci.cvds.elysium.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import edu.eci.cvds.elysium.model.AuthenticationRequest;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.CustomUserDetailsService;
import edu.eci.cvds.elysium.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthControllerTest {

    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;
    private UsuarioRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        authenticationManager = mock(AuthenticationManager.class);
        userDetailsService = mock(CustomUserDetailsService.class);
        userRepository = mock(UsuarioRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtUtil = mock(JwtUtil.class);
        authController = new AuthController(authenticationManager, userDetailsService, userRepository, passwordEncoder, jwtUtil);
    }

    @Test
    public void testRegisterUserSuccess() {
        Usuario user = new Usuario(412412444, "null", "null", "null", false, false);
        user.setIdInstitucional(412412412);
        user.setPassword("rawPassword");

        when(userRepository.existsByIdInstitucional(412412412)).thenReturn(false);
        when(passwordEncoder.encode("rawPassword")).thenReturn("encodedPassword");

        ResponseEntity<String> response = authController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario registrado exitosamente", response.getBody());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRegisterUserConflict() {
        Usuario user = new Usuario(412412444, "null", "null", "null", false, false);
        user.setIdInstitucional(412412412);

        when(userRepository.existsByIdInstitucional(412412412)).thenReturn(true);

        ResponseEntity<String> response = authController.registerUser(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("El usuario ya existe", response.getBody());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testLoginUserSuccess() throws Exception {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setCorreoInstitucional("test@example.com");
        authRequest.setPassword("password");

        UserDetails userDetails = mock(UserDetails.class);
        Authentication authentication = mock(Authentication.class);
        String token = "jwt-token";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn(token);

        ResponseEntity<?> response = authController.loginUser(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody());
    }

    @Test
    public void testLoginUserUnauthorized() throws Exception {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setCorreoInstitucional("test@example.com");
        authRequest.setPassword("wrongPassword");

        doThrow(new RuntimeException("Authentication failed")).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        ResponseEntity<?> response = authController.loginUser(authRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales inválidas", response.getBody());
    }

    @Test
    public void testHello() {
        String response = authController.hello();
        assertEquals("Hello, World!", response);
    }
}