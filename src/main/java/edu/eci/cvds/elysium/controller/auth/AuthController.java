package edu.eci.cvds.elysium.controller.auth;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.elysium.model.AuthenticationRequest;
import edu.eci.cvds.elysium.model.usuario.Administrador;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.CustomUserDetailsService;
import edu.eci.cvds.elysium.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          UsuarioRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Administrador user) {
        // Si no se envía password, usamos el idInstitucional como raw password
        if (userRepository.existsByIdInstitucional(user.getIdInstitucional())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        // Encriptar la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Guardar el usuario en la base de datos
        userRepository.save(user);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
    

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getCorreoInstitucional(),
                            authenticationRequest.getPassword()
                    )
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getCorreoInstitucional());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}