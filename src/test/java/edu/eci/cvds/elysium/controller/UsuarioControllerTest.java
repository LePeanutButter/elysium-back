package edu.eci.cvds.elysium.controller;

import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.dto.SalonDTO;
import edu.eci.cvds.elysium.dto.UsuarioDTO;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import edu.eci.cvds.elysium.model.DiaSemana;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario mockUsuario;
    private List<Usuario> mockUsuarios;
    private UsuarioDTO mockUsuarioDTO;
    private ReservaDTO mockReservaDTO;
    private SalonDTO mockSalonDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup common test data
        mockUsuario = new Usuario(1,"Nombre","Apellido","test@example.com",true,true);
        
        // Mock DTOs since they don't have setters or constructors
        mockUsuarioDTO = mock(UsuarioDTO.class);
        when(mockUsuarioDTO.getId()).thenReturn(1);
        when(mockUsuarioDTO.getNombre()).thenReturn("Nombre");
        when(mockUsuarioDTO.getApellido()).thenReturn("Apellido");
        when(mockUsuarioDTO.getCorreo()).thenReturn("test@example.com");
        when(mockUsuarioDTO.getIsAdmin()).thenReturn(true);
        
        mockReservaDTO = mock(ReservaDTO.class);
        when(mockReservaDTO.getFechaReserva()).thenReturn(LocalDate.now());
        when(mockReservaDTO.getHora()).thenReturn(10.0);
        when(mockReservaDTO.getDiaSemana()).thenReturn(DiaSemana.LUNES);
        when(mockReservaDTO.getProposito()).thenReturn("Propósito de prueba");
        when(mockReservaDTO.getMateria()).thenReturn("Materia de prueba");
        when(mockReservaDTO.getIdSalon()).thenReturn("S101");
        when(mockReservaDTO.isDuracionBloque()).thenReturn(true);
        when(mockReservaDTO.getPrioridad()).thenReturn(1);
        
        mockSalonDTO = mock(SalonDTO.class);
        when(mockSalonDTO.getMnemonic()).thenReturn("S101");
        when(mockSalonDTO.getName()).thenReturn("Salón 101");
        when(mockSalonDTO.getDescription()).thenReturn("Descripción");
        when(mockSalonDTO.getLocation()).thenReturn("Bloque A");
        when(mockSalonDTO.getCapacity()).thenReturn(30);
        when(mockSalonDTO.getResources()).thenReturn(null);
        
        mockUsuarios = new ArrayList<>();
        mockUsuarios.add(mockUsuario);
    }

    // Tests for consultarUsuario
    @Test
    public void testConsultarUsuarioExistente() {
        // Arrange
        int userId = 1;
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuario(userId);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuario, response.getBody());
        verify(usuarioService).consultarUsuario(userId);
    }

    @Test
    public void testConsultarUsuarioNoExistente() {
        // Arrange
        int userId = 999;
        when(usuarioService.consultarUsuario(userId)).thenReturn(null);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuario(userId);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Usuario no encontrado con ID: " + userId, responseBody.get("message"));
    }

    @Test
    public void testConsultarUsuarioConError() {
        // Arrange
        int userId = 1;
        when(usuarioService.consultarUsuario(userId)).thenThrow(new RuntimeException("Error de prueba"));
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuario(userId);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof Map);
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al consultar usuario"));
    }

    // Tests for consultarUsuarioPorCorreo
    @Test
    public void testConsultarUsuarioPorCorreoExistente() {
        // Arrange
        String correo = "test@example.com";
        when(usuarioService.consultarUsuarioPorCorreo(correo)).thenReturn(mockUsuario);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarioPorCorreo(correo);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuario, response.getBody());
    }

    @Test
    public void testConsultarUsuarioPorCorreoNoExistente() {
        // Arrange
        String correo = "noexiste@example.com";
        when(usuarioService.consultarUsuarioPorCorreo(correo)).thenReturn(null);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarioPorCorreo(correo);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Usuario no encontrado con correo: " + correo, responseBody.get("message"));
    }

    @Test
    public void testConsultarUsuarioPorCorreoError() {
        // Arrange
        String correo = "test@example.com";
        when(usuarioService.consultarUsuarioPorCorreo(correo)).thenThrow(new RuntimeException("Error de prueba"));
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarioPorCorreo(correo);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al consultar usuario por correo"));
    }

    // Tests for consultarUsuarios
    @Test
    public void testConsultarUsuariosSinFiltros() {
        // Arrange
        when(usuarioService.consultarUsuarios()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(null, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
    }

    @Test
    public void testConsultarUsuariosActivosFilter() {
        // Arrange
        when(usuarioService.consultarUsuariosActivos()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(true, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
    }

    @Test
    public void testConsultarUsuariosAdminsFilter() {
        // Arrange
        when(usuarioService.consultarUsuariosAdmins()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(null, true);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
    }

    @Test
    public void testConsultarUsuariosActivosAdminsFilter() {
        // Arrange
        when(usuarioService.consultarUsuariosActiveAdmins()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(true, true);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
    }

    @Test
    public void testConsultarUsuariosFalseNull() {
        // Arrange
        when(usuarioService.consultarUsuariosInactivos()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(false, null);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
        verify(usuarioService).consultarUsuariosInactivos();
    }

    @Test
    public void testConsultarUsuariosFalseTrue() {
        // Arrange
        when(usuarioService.consultarUsuariosInactiveAdmins()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(false, true);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
        verify(usuarioService).consultarUsuariosInactiveAdmins();
    }

    @Test
    public void testConsultarUsuariosFalseFalse() {
        // Arrange
        when(usuarioService.consultarUsuariosInactiveNoAdmins()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(false, false);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
        verify(usuarioService).consultarUsuariosInactiveNoAdmins();
    }
    
    @Test
    public void testConsultarUsuariosNullFalse() {
        // Arrange
        when(usuarioService.consultarUsuariosActiveNoAdmins()).thenReturn(mockUsuarios);
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(null, false);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarios, response.getBody());
        verify(usuarioService).consultarUsuariosActiveNoAdmins();
    }

    @Test
    public void testConsultarUsuariosError() {
        // Arrange
        when(usuarioService.consultarUsuarios()).thenThrow(new RuntimeException("Error de prueba"));
        
        // Act
        ResponseEntity<?> response = usuarioController.consultarUsuarios(null, null);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al consultar usuarios"));
    }

    // Tests for agregarUsuario
    @Test
    public void testAgregarUsuarioExito() {
        // Arrange
        when(usuarioService.agregarUsuario(
                anyInt(),
                anyString(),
                anyString(),
                anyString(),
                anyBoolean()))
                .thenReturn(mockUsuario);
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarUsuario(mockUsuarioDTO);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals("SUCCESS", responseBody.get("status"));
        assertEquals("Usuario creado correctamente", responseBody.get("message"));
        assertEquals(mockUsuario, responseBody.get("usuario"));
    }

    @Test
    public void testAgregarUsuarioError() {
        // Arrange
        when(usuarioService.agregarUsuario(
                anyInt(), 
                anyString(), 
                anyString(), 
                anyString(), 
                anyBoolean()))
                .thenThrow(new ElysiumExceptions("Error de validación"));
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarUsuario(mockUsuarioDTO);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Error de validación", responseBody.get("message"));
    }

    // Tests for actualizarInformacionUsuario
    @Test
    public void testActualizarInformacionUsuarioExito() {
        // Arrange
        int userId = 1;
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doNothing().when(usuarioService).actualizarInformacionUsuario(anyInt(), any(UsuarioDTO.class));
        
        // Act
        ResponseEntity<?> response = usuarioController.actualizarInformacionUsuario(userId, mockUsuarioDTO);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("SUCCESS", responseBody.get("status"));
        assertEquals("Usuario actualizado correctamente", responseBody.get("message"));
    }

    @Test
    public void testActualizarInformacionUsuarioNoExistente() {
        // Arrange
        int userId = 999;
        when(usuarioService.consultarUsuario(userId)).thenReturn(null);
        
        // Act
        ResponseEntity<?> response = usuarioController.actualizarInformacionUsuario(userId, mockUsuarioDTO);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Usuario no encontrado con ID: " + userId, responseBody.get("message"));
    }

    @Test
    public void testActualizarInformacionUsuarioError() {
        // Arrange
        int userId = 1;
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doThrow(new RuntimeException("Error de prueba")).when(usuarioService).actualizarInformacionUsuario(anyInt(), any(UsuarioDTO.class));
        
        // Act
        ResponseEntity<?> response = usuarioController.actualizarInformacionUsuario(userId, mockUsuarioDTO);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al actualizar usuario"));
    }

    // Tests for agregarSalon
    @Test
    public void testAgregarSalonExito() {
        // Arrange
        int userId = 1;
        mockUsuario.setAdmin(true);
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doNothing().when(usuarioService).agregarSalon(
                anyInt(), anyString(), anyString(), 
                anyString(), anyString(), 
                anyInt(), any());
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarSalon(userId, mockSalonDTO);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("SUCCESS", responseBody.get("status"));
        assertEquals("Salón agregado correctamente", responseBody.get("message"));
    }

    @Test
    public void testAgregarSalonUsuarioNoAdmin() {
        // Arrange
        int userId = 1;
        mockUsuario.setAdmin(false);
        SalonDTO salonDTO = new SalonDTO();
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarSalon(userId, salonDTO);
        
        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("El usuario no tiene permisos de administrador", responseBody.get("message"));
    }

    @Test
    public void testAgregarSalonUsuarioNoExistente() {
        // Arrange
        int userId = 999;
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(null);
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarSalon(userId, mockSalonDTO);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Usuario no encontrado con ID: " + userId, responseBody.get("message"));
    }

    @Test
    public void testAgregarSalonIllegalArgumentException() {
        // Arrange
        int userId = 1;
        mockUsuario.setAdmin(true);
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doThrow(new IllegalArgumentException("Datos del salón inválidos")).when(usuarioService).agregarSalon(
                anyInt(), anyString(), anyString(), 
                anyString(), anyString(), 
                anyInt(), any());
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarSalon(userId, mockSalonDTO);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Datos del salón inválidos", responseBody.get("message"));
    }
    
    @Test
    public void testAgregarSalonGenericException() {
        // Arrange
        int userId = 1;
        mockUsuario.setAdmin(true);
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doThrow(new RuntimeException("Error inesperado")).when(usuarioService).agregarSalon(
                anyInt(), anyString(), anyString(), 
                anyString(), anyString(), 
                anyInt(), any());
        
        // Act
        ResponseEntity<?> response = usuarioController.agregarSalon(userId, mockSalonDTO);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al agregar salón"));
    }

    // Tests for crearReserva
    @Test
    public void testCrearReservaExito() {
        // Arrange
        int userId = 1;
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        
        doNothing().when(usuarioService).crearReserva(
                any(LocalDate.class), anyDouble(), 
                any(DiaSemana.class), anyString(), 
                anyString(), anyString(),
                anyBoolean(), anyInt(), 
                anyInt());
        
        // Act
        ResponseEntity<?> response = usuarioController.crearReserva(userId, mockReservaDTO);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("SUCCESS", responseBody.get("status"));
        assertEquals("Reserva creada correctamente", responseBody.get("message"));
    }

    @Test
    public void testCrearReservaUsuarioNoExistente() {
        // Arrange
        int userId = 999;
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(null);
        
        // Act
        ResponseEntity<?> response = usuarioController.crearReserva(userId, mockReservaDTO);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Usuario no encontrado con ID: " + userId, responseBody.get("message"));
    }
    
    @Test
    public void testCrearReservaIllegalArgumentException() {
        // Arrange
        int userId = 1;
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doThrow(new IllegalArgumentException("Datos de reserva inválidos")).when(usuarioService).crearReserva(
                any(LocalDate.class), anyDouble(), 
                any(DiaSemana.class), anyString(), 
                anyString(), anyString(),
                anyBoolean(), anyInt(), anyInt());
        
        // Act
        ResponseEntity<?> response = usuarioController.crearReserva(userId, mockReservaDTO);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Datos de reserva inválidos", responseBody.get("message"));
    }
    
    @Test
    public void testCrearReservaGenericException() {
        // Arrange
        int userId = 1;
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        doThrow(new RuntimeException("Error inesperado")).when(usuarioService).crearReserva(
                any(LocalDate.class), anyDouble(), 
                any(DiaSemana.class), anyString(), 
                anyString(), anyString(),
                anyBoolean(), anyInt(), anyInt());
        
        // Act
        ResponseEntity<?> response = usuarioController.crearReserva(userId, mockReservaDTO);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al crear reserva"));
    }

    // Tests for listarReservas
    @Test
    public void testListarReservasExito() {
        // Arrange
        int userId = 1;
        List<Reserva> mockReservas = new ArrayList<>();
        Reserva reserva = new Reserva();
        reserva.setIdUsuario(1);
        mockReservas.add(reserva);
        
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        when(usuarioService.listarReservas(userId)).thenReturn(mockReservas);
        
        // Act
        ResponseEntity<?> response = usuarioController.listarReservas(userId);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertEquals("SUCCESS", responseBody.get("status"));
        assertEquals("Reservas consultadas correctamente", responseBody.get("message"));
        assertEquals(mockReservas, responseBody.get("reservas"));
    }

    @Test
    public void testListarReservasUsuarioNoExistente() {
        // Arrange
        int userId = 999;
        when(usuarioService.consultarUsuario(userId)).thenReturn(null);
        
        // Act
        ResponseEntity<?> response = usuarioController.listarReservas(userId);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertEquals("Usuario no encontrado con ID: " + userId, responseBody.get("message"));
    }

    @Test
    public void testListarReservasError() {
        // Arrange
        int userId = 1;
        when(usuarioService.consultarUsuario(userId)).thenReturn(mockUsuario);
        when(usuarioService.listarReservas(userId)).thenThrow(new RuntimeException("Error de prueba"));
        
        // Act
        ResponseEntity<?> response = usuarioController.listarReservas(userId);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("ERROR", responseBody.get("status"));
        assertTrue(responseBody.get("message").contains("Error al listar reservas"));
    }
}