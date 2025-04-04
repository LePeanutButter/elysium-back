package edu.eci.cvds.elysium.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.Recurso;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.SalonRepository;
import edu.eci.cvds.elysium.repository.UsuarioRepository;
import edu.eci.cvds.elysium.service.ReservaService;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ReservaService reservaService;

    @Mock
    private SalonRepository salonRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    private Usuario testUsuario;
    private Usuario adminUsuario;
    private List<Usuario> usuarios;
    
    @BeforeEach
    public void setUp() {
        testUsuario = new Usuario(1234567890, "John", "Doe", "john.doe@escuelaing.edu.co", true, false);
        adminUsuario = new Usuario(1987654321, "Admin", "User", "admin.user@escuelaing.edu.co", true, true);
        usuarios = Arrays.asList(testUsuario, adminUsuario);
    }
    
    @Test
    public void testConsultarUsuario() {
        when(usuarioRepository.findByIdInstitucional(1234567890)).thenReturn(testUsuario);
        Usuario result = usuarioServiceImpl.consultarUsuario(1234567890);
        assertNotNull(result);
        assertEquals("John", result.getNombre());
    }

    @Test
    public void testConsultarUsuarioNoExistente() {
        when(usuarioRepository.findByIdInstitucional(9999999)).thenReturn(null);
        Usuario result = usuarioServiceImpl.consultarUsuario(9999999);
        assertNull(result);
    }

    @Test
    public void testConsultarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> result = usuarioServiceImpl.consultarUsuarios();
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNombre());
        assertEquals("Admin", result.get(1).getNombre());
    }

    @Test
    public void testConsultarUsuariosActivos() {
        List<Usuario> usuariosActivos = Arrays.asList(testUsuario, adminUsuario);
        when(usuarioRepository.findByActivoTrue()).thenReturn(usuariosActivos);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosActivos();
        assertEquals(2, result.size());
    }

    @Test
    public void testConsultarUsuarioPorCorreo() {
        String correo = "john.doe@escuelaing.edu.co";
        when(usuarioRepository.findByCorreoInstitucional(correo)).thenReturn(testUsuario);
        
        Usuario result = usuarioServiceImpl.consultarUsuarioPorCorreo(correo);
        assertNotNull(result);
        assertEquals(correo, result.getCorreoInstitucional());
    }

    @Test
    public void testConsultarUsuarioPorCorreoNoExistente() {
        String correo = "noexiste@escuelaing.edu.co";
        when(usuarioRepository.findByCorreoInstitucional(correo)).thenReturn(null);
        
        Usuario result = usuarioServiceImpl.consultarUsuarioPorCorreo(correo);
        assertNull(result);
    }

    @Test
    public void testConsultarUsuariosInactivos() {
        Usuario inactiveUser = new Usuario(1111111111, "Inactive", "User", "inactive.user@escuelaing.edu.co", false, false);
        List<Usuario> usuariosInactivos = Arrays.asList(inactiveUser);
        when(usuarioRepository.findByActivoFalse()).thenReturn(usuariosInactivos);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosInactivos();
        assertEquals(1, result.size());
        assertEquals("Inactive", result.get(0).getNombre());
    }

    @Test
    public void testConsultarUsuariosAdmins() {
        List<Usuario> admins = Arrays.asList(adminUsuario);
        when(usuarioRepository.findByIsAdminTrue()).thenReturn(admins);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosAdmins();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getIsAdmin());
        assertEquals("Admin", result.get(0).getNombre());
    }

    @Test
    public void testConsultarUsuariosActiveAdmins() {
        List<Usuario> activeAdmins = Arrays.asList(adminUsuario);
        when(usuarioRepository.findByActivoTrueAndIsAdminTrue()).thenReturn(activeAdmins);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosActiveAdmins();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getIsAdmin());
    }

    @Test
    public void testConsultarUsuariosInactiveAdmins() {
        Usuario inactiveAdmin = new Usuario(22222222, "Inactive", "Admin", "inactive.admin@escuelaing.edu.co", false, true);
        List<Usuario> inactiveAdmins = Arrays.asList(inactiveAdmin);
        when(usuarioRepository.findByActivoFalseAndIsAdminTrue()).thenReturn(inactiveAdmins);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosInactiveAdmins();
        assertEquals(1, result.size());
        assertTrue(result.get(0).getIsAdmin());
    }

    @Test
    public void testConsultarUsuariosActiveNoAdmins() {
        List<Usuario> activeNoAdmins = Arrays.asList(testUsuario);
        when(usuarioRepository.findByActivoTrueAndIsAdminFalse()).thenReturn(activeNoAdmins);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosActiveNoAdmins();
        assertEquals(1, result.size());
        assertFalse(result.get(0).getIsAdmin());
    }

    @Test
    public void testConsultarUsuariosInactiveNoAdmins() {
        Usuario inactiveNoAdmin = new Usuario(3333333, "Inactive", "NonAdmin", "inactive.nonadmin@escuelaing.edu.co", false, false);
        List<Usuario> inactiveNoAdmins = Arrays.asList(inactiveNoAdmin);
        when(usuarioRepository.findByActivoFalseAndIsAdminFalse()).thenReturn(inactiveNoAdmins);
        
        List<Usuario> result = usuarioServiceImpl.consultarUsuariosInactiveNoAdmins();
        assertEquals(1, result.size());
        assertFalse(result.get(0).getIsAdmin());
    }
    
    @Test
    public void testActualizarInformacionUsuarioSuccessful() throws ElysiumExceptions {
        int id = 1234567890;
        UsuarioDTO dto = new UsuarioDTO();
        
        when(usuarioRepository.findByIdInstitucional(id)).thenReturn(testUsuario);
        
        usuarioServiceImpl.actualizarInformacionUsuario(id, dto);
        
        // Verify the user was updated and saved
        verify(usuarioRepository).save(testUsuario);
        assertEquals("John", testUsuario.getNombre());
        assertEquals("Doe", testUsuario.getApellido());
    }


    @Test
    public void testActualizarInformacionUsuarioNotFound() {
        int id = 1234567890;
        UsuarioDTO dto = new UsuarioDTO();
        when(usuarioRepository.findByIdInstitucional(id)).thenReturn(null);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.actualizarInformacionUsuario(id, dto);
        });
        assertEquals(ElysiumExceptions.USUARIO_NO_ENCONTRADO, ex.getMessage());
    }
    
    @Test
    public void testAgregarUsuarioSuccessful() throws ElysiumExceptions {
        int idInstitucional = 1234567890;
        String nombre = "Alice";
        String apellido = "Wonderland";
        String correo = "alice.wonderland@escuelaing.edu.co";
        boolean isAdmin = false;
        String password = String.valueOf(idInstitucional);
        String encodedPassword = "encodedPassword";
        
        when(usuarioRepository.existsByIdInstitucional(idInstitucional)).thenReturn(false);
        when(usuarioRepository.existsByCorreoInstitucional(correo)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        
        Usuario savedUser = new Usuario(idInstitucional, nombre, apellido, correo, true, isAdmin);
        savedUser.setPassword(encodedPassword);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(savedUser);
        
        Usuario returnedUser = new Usuario(idInstitucional, nombre, apellido, correo, true, isAdmin);
        returnedUser.setPassword(encodedPassword);
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(returnedUser);
        
        Usuario nuevoUsuario = usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        
        assertNotNull(nuevoUsuario);
        assertEquals(nombre, nuevoUsuario.getNombre());
        assertEquals(apellido, nuevoUsuario.getApellido());
        assertEquals(correo, nuevoUsuario.getCorreoInstitucional());
        assertEquals(encodedPassword, nuevoUsuario.getPassword());
        
        verify(passwordEncoder).encode(password);
        verify(usuarioRepository).save(any(Usuario.class));
    }
    
    @Test
    public void testAgregarUsuarioInvalidName() {
        int idInstitucional = 1234567890;
        String nombre = "   ";
        String apellido = "Wonderland";
        String correo = "alice.wonderland@escuelaing.edu.co";
        boolean isAdmin = false;
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        });
        assertEquals(ElysiumExceptions.NOMBRE_NO_VALIDO, ex.getMessage());
    }

    @Test
    public void testAgregarUsuarioInvalidLastName() {
        int idInstitucional = 1234567890;
        String nombre = "Alice";
        String apellido = "";
        String correo = "alice.wonderland@escuelaing.edu.co";
        boolean isAdmin = false;
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        });
        assertEquals(ElysiumExceptions.APELLIDO_NO_VALIDO, ex.getMessage());
    }

    @Test
    public void testAgregarUsuarioInvalidId() {
        int idInstitucional = 123; // Too short
        String nombre = "Alice";
        String apellido = "Wonderland";
        String correo = "alice.wonderland@escuelaing.edu.co";
        boolean isAdmin = false;
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        });
        assertEquals(ElysiumExceptions.ID_NO_VALIDO, ex.getMessage());
    }

    @Test
    public void testAgregarUsuarioInvalidEmail() {
        int idInstitucional = 1234567890;
        String nombre = "Alice";
        String apellido = "Wonderland";
        String correo = "alice.wonderland@gmail.com"; // Not institutional email
        boolean isAdmin = false;
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        });
        assertEquals(ElysiumExceptions.CORREO_NO_VALIDO, ex.getMessage());
    }

    @Test
    public void testAgregarUsuarioExistingId() {
        int idInstitucional = 1234567890;
        String nombre = "Alice";
        String apellido = "Wonderland";
        String correo = "alice.wonderland@escuelaing.edu.co";
        boolean isAdmin = false;
        
        when(usuarioRepository.existsByIdInstitucional(idInstitucional)).thenReturn(true);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        });
        assertEquals(ElysiumExceptions.YA_EXISTE_USUARIO, ex.getMessage());
    }

    @Test
    public void testAgregarUsuarioExistingEmail() {
        int idInstitucional = 1234567890;
        String nombre = "Alice";
        String apellido = "Wonderland";
        String correo = "alice.wonderland@escuelaing.edu.co";
        boolean isAdmin = false;
        
        when(usuarioRepository.existsByIdInstitucional(idInstitucional)).thenReturn(false);
        when(usuarioRepository.existsByCorreoInstitucional(correo)).thenReturn(true);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarUsuario(idInstitucional, nombre, apellido, correo, isAdmin);
        });
        assertEquals(ElysiumExceptions.YA_EXISTE_CORREO, ex.getMessage());
    }
    
    @Test
    public void testAgregarSalonSuccessful() throws ElysiumExceptions {
        int userId = 1987654321;
        String mnemonico = "SALON1";
        String nombreSalon = "Salon A";
        String descripcion = "Description";
        String ubicacion = "Location A";
        Integer capacidad = 50;
        List<Recurso> recursos = Collections.emptyList();
        
        // User is admin
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(adminUsuario);
        when(salonRepository.existsById(mnemonico)).thenReturn(false);
        
        usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        
        // Verify salon was saved
        verify(salonRepository).save(any(Salon.class));
    }
    
    @Test
    public void testAgregarSalonNotAdmin() {
        int userId = 1234567890;
        String mnemonico = "SALON1";
        String nombreSalon = "Salon A";
        String descripcion = "Description";
        String ubicacion = "Location A";
        Integer capacidad = 50;
        List<Recurso> recursos = Collections.emptyList();
        
        // testUsuario is not admin (false)
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(testUsuario);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        });
        assertEquals(ElysiumExceptions.NO_ES_ADMIN, ex.getMessage());
    }

    @Test
    public void testAgregarSalonInvalidMnemonico() {
        int userId = 1987654321;
        String mnemonico = "";
        String nombreSalon = "Salon A";
        String descripcion = "Description";
        String ubicacion = "Location A";
        Integer capacidad = 50;
        List<Recurso> recursos = Collections.emptyList();
        
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(adminUsuario);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        });
        assertEquals(ElysiumExceptions.MNEMONICO_NO_VALIDO, ex.getMessage());
    }

    @Test
    public void testAgregarSalonInvalidNombre() {
        int userId = 1987654321;
        String mnemonico = "SALON1";
        String nombreSalon = null;
        String descripcion = "Description";
        String ubicacion = "Location A";
        Integer capacidad = 50;
        List<Recurso> recursos = Collections.emptyList();
        
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(adminUsuario);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        });
        assertEquals(ElysiumExceptions.NOMBRE_SALON_NO_VALIDO, ex.getMessage());
    }

    @Test
    public void testAgregarSalonInvalidUbicacion() {
        int userId = 1987654321;
        String mnemonico = "SALON1";
        String nombreSalon = "Salon A";
        String descripcion = "Description";
        String ubicacion = "";
        Integer capacidad = 50;
        List<Recurso> recursos = Collections.emptyList();
        
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(adminUsuario);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        });
        assertEquals(ElysiumExceptions.UBICACION_NO_VALIDA, ex.getMessage());
    }

    @Test
    public void testAgregarSalonInvalidCapacidad() {
        int userId = 1987654321;
        String mnemonico = "SALON1";
        String nombreSalon = "Salon A";
        String descripcion = "Description";
        String ubicacion = "Location A";
        Integer capacidad = 0; // Invalid
        List<Recurso> recursos = Collections.emptyList();
        
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(adminUsuario);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        });
        assertEquals(ElysiumExceptions.CAPACIDAD_NO_VALIDA, ex.getMessage());
    }

    @Test
    public void testAgregarSalonExistente() {
        int userId = 1987654321;
        String mnemonico = "SALON1";
        String nombreSalon = "Salon A";
        String descripcion = "Description";
        String ubicacion = "Location A";
        Integer capacidad = 50;
        List<Recurso> recursos = Collections.emptyList();
        
        when(usuarioRepository.findByIdInstitucional(userId)).thenReturn(adminUsuario);
        when(salonRepository.existsById(mnemonico)).thenReturn(true);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.agregarSalon(userId, mnemonico, nombreSalon, descripcion, ubicacion, capacidad, recursos);
        });
        assertEquals(ElysiumExceptions.YA_EXISTE_SALON, ex.getMessage());
    }
    
    @Test
    public void testCrearReservaSuccessful() throws ElysiumExceptions {
        int idInstitucional = 1234567890;
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 8.0;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Meeting";
        String materia = "Math";
        String idSalon = "SALON1";
        boolean duracionBloque = false;
        int prioridad = 1;
        
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(testUsuario);
        when(salonRepository.existsById(idSalon)).thenReturn(true);
        doNothing().when(reservaService).crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                idSalon, duracionBloque, prioridad, idInstitucional);
        
        usuarioServiceImpl.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                idSalon, duracionBloque, prioridad, idInstitucional);
        
        verify(reservaService).crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                idSalon, duracionBloque, prioridad, idInstitucional);
    }
    
    @Test
    public void testCrearReservaFechaPasada() {
        int idInstitucional = 1234567890;
        LocalDate fechaReserva = LocalDate.now().minusDays(1);
        double hora = 8.0;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Meeting";
        String materia = "Math";
        String idSalon = "SALON1";
        boolean duracionBloque = false;
        int prioridad = 1;
        
        // Prepare mocks for user and salon existence checks.
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(testUsuario);
        when(salonRepository.existsById(idSalon)).thenReturn(true);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);
        });
        assertEquals(ElysiumExceptions.FECHA_PASADA, ex.getMessage());
    }

    @Test
    public void testCrearReservaUsuarioNoEncontrado() {
        int idInstitucional = 999999; // Non-existent
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 8.0;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Meeting";
        String materia = "Math";
        String idSalon = "SALON1";
        boolean duracionBloque = false;
        int prioridad = 1;
        
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(null);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                    idSalon, duracionBloque, prioridad, idInstitucional);
        });
        assertEquals(ElysiumExceptions.USUARIO_NO_ENCONTRADO, ex.getMessage());
    }

    @Test
    public void testCrearReservaSalonNoEncontrado() {
        int idInstitucional = 1234567890;
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 8.0;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Meeting";
        String materia = "Math";
        String idSalon = "NOSALON"; // Non-existent
        boolean duracionBloque = false;
        int prioridad = 1;
        
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(testUsuario);
        when(salonRepository.existsById(idSalon)).thenReturn(false);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                    idSalon, duracionBloque, prioridad, idInstitucional);
        });
        assertEquals(ElysiumExceptions.SALON_NO_ENCONTRADO, ex.getMessage());
    }

    @Test
    public void testCrearReservaHoraInvalida() {
        int idInstitucional = 1234567890;
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 20.0; // Invalid hour
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Meeting";
        String materia = "Math";
        String idSalon = "SALON1";
        boolean duracionBloque = false;
        int prioridad = 1;
        
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(testUsuario);
        when(salonRepository.existsById(idSalon)).thenReturn(true);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                    idSalon, duracionBloque, prioridad, idInstitucional);
        });
        assertEquals(ElysiumExceptions.HORA_NO_VALIDA, ex.getMessage());
    }

    @Test
    public void testCrearReservaPropositoInvalido() {
        int idInstitucional = 1234567890;
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 8.0;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = ""; // Invalid purpose
        String materia = "Math";
        String idSalon = "SALON1";
        boolean duracionBloque = false;
        int prioridad = 1;
        
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(testUsuario);
        when(salonRepository.existsById(idSalon)).thenReturn(true);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, 
                    idSalon, duracionBloque, prioridad, idInstitucional);
        });
        assertEquals(ElysiumExceptions.PROPOSITO_NO_VALIDO, ex.getMessage());
    }
    
    @Test
    public void testListarReservasSuccessful() throws ElysiumExceptions {
        int idInstitucional = 1234567890;
        List<Reserva> reservas = Arrays.asList(
            new Reserva(), new Reserva()
        );
        
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(testUsuario);
        when(reservaService.consultarReservasPorUsuario(idInstitucional)).thenReturn(reservas);
        
        List<Reserva> result = usuarioServiceImpl.listarReservas(idInstitucional);
        assertEquals(2, result.size());
    }
    
    @Test
    public void testListarReservasUserNotFound() {
        int idInstitucional = 1234567890;
        when(usuarioRepository.findByIdInstitucional(idInstitucional)).thenReturn(null);
        
        ElysiumExceptions ex = assertThrows(ElysiumExceptions.class, () -> {
            usuarioServiceImpl.listarReservas(idInstitucional);
        });
        assertEquals(ElysiumExceptions.USUARIO_NO_ENCONTRADO, ex.getMessage());
    }
}