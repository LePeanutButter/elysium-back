package edu.eci.cvds.elysium.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import edu.eci.cvds.elysium.ElysiumExceptions;
import edu.eci.cvds.elysium.dto.ReservaDTO;
import edu.eci.cvds.elysium.model.DiaSemana;
import edu.eci.cvds.elysium.model.EstadoReserva;
import edu.eci.cvds.elysium.model.Reserva;
import edu.eci.cvds.elysium.model.Salon;
import edu.eci.cvds.elysium.model.Usuario;
import edu.eci.cvds.elysium.repository.ReservaRepository;
import edu.eci.cvds.elysium.service.SalonService;
import edu.eci.cvds.elysium.service.UsuarioService;



public class ReservaServiceImplTest {

    private ReservaRepository reservaRepository;
    private UsuarioService usuarioService;
    private SalonService salonService;
    private ReservaServiceImpl reservaService;

    @BeforeEach
    public void setUp() {
        reservaRepository = Mockito.mock(ReservaRepository.class);
        usuarioService = Mockito.mock(UsuarioService.class);
        salonService = Mockito.mock(SalonService.class);
        reservaService = new ReservaServiceImpl();
        reservaService.setReservaRepository(reservaRepository);
        reservaService.setUsuarioService(usuarioService);
        reservaService.setSalonService(salonService);
    }

    @Test
    public void testConsultarReservas() {
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, 1);
        Reserva reserva2 = new Reserva(LocalDate.now(), 12, DiaSemana.MARTES, "Clase", "Mat2", "102", true, 4, 2);
        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva1, reserva2));
        
        List<Reserva> reservas = reservaService.consultarReservas();
        assertEquals(2, reservas.size());
    }
    
    @Test
    public void testConsultarReservaPorUsuario() {
        int idUsuario = 1;
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, idUsuario);
        Reserva reserva2 = new Reserva(LocalDate.now(), 12, DiaSemana.MARTES, "Clase", "Mat2", "102", true, 4, idUsuario);
        when(reservaRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        List<Reserva> reservas = reservaService.consultarReservasPorUsuario(idUsuario);
        assertEquals(2, reservas.size());
    }

    @Test
    public void testCrearReservaSuccess() {
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 9;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Estudio";
        String materia = "Matemáticas";
        String idSalon = "201";
        boolean duracionBloque = true;
        int prioridad = 3;
        int idInstitucional = 1;
        
        // Setup mocks: valid usuario and salon returned
        Usuario usuario = new Usuario(10000004, "proposito", "cxva", "HDFAGF", true, true);
        Salon salon = new Salon();
        when(usuarioService.consultarUsuario(idInstitucional)).thenReturn(usuario);
        when(salonService.findByMnemonico(idSalon)).thenReturn(salon);
        
        reservaService.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);
        
        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaRepository).save(reservaCaptor.capture());
        Reserva savedReserva = reservaCaptor.getValue();
        assertEquals(fechaReserva, savedReserva.getFechaReserva());
        assertEquals(hora, savedReserva.getHora());
        assertEquals(diaSemana, savedReserva.getDiaSemana());
        assertEquals(proposito, savedReserva.getProposito());
        assertEquals(materia, savedReserva.getMateria());
        assertEquals(idSalon, savedReserva.getIdSalon());
        assertEquals(duracionBloque, savedReserva.isDuracionBloque());
        assertEquals(prioridad, savedReserva.getPrioridad());
        assertEquals(idInstitucional, savedReserva.getIdUsuario());
        assertEquals(EstadoReserva.ACTIVA, savedReserva.getEstado());
    }
    
    @Test
    public void testCrearReservaInvalidHour() {
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = -5; // invalid hour
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Estudio";
        String materia = "Matemáticas";
        String idSalon = "201";
        boolean duracionBloque = false;
        int prioridad = 3;
        int idInstitucional = 1;
        
        // Setup valid usuario and salon, though hour is invalid so should not create.
        Usuario usuario = new Usuario(10000004, "proposito", "cxva", "HDFAGF", true, true);
        Salon salon = new Salon();
        when(usuarioService.consultarUsuario(idInstitucional)).thenReturn(usuario);
        when(salonService.findByMnemonico(idSalon)).thenReturn(salon);
        
        reservaService.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);
        // Since the method catches the exception and prints error, repository.save should not be called.
        verify(reservaRepository, never()).save(any());
    }
    
    
    @Test
    public void testDeshabilitarReserva() {
        String idReserva = "reserva123";
        Reserva reservaExistente = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Some purpose", "AlgunaMateria", "401", false, 3, 1);
        reservaExistente.setEstado(EstadoReserva.ACTIVA);
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reservaExistente);
        
        reservaService.deshabilitarReserva(idReserva);
        
        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaRepository).save(reservaCaptor.capture());
        Reserva updatedReserva = reservaCaptor.getValue();
        assertEquals(EstadoReserva.DESHABILITADA, updatedReserva.getEstado());
    }

    @Test
    public void testConsultarReservasPorUsuario() {
        // Arrange
        int idUsuario = 1;
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, idUsuario);
        Reserva reserva2 = new Reserva(LocalDate.now(), 11, DiaSemana.MIERCOLES, "Clase", "Mat2", "102", true, 2, idUsuario);
        when(reservaRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorUsuario(idUsuario);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByIdUsuario(idUsuario);
    }
    
    @Test
    public void testConsultarReservasPorSalon() {
        // Arrange
        String idSalon = "101";
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", idSalon, false, 3, 1);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(1), 11, DiaSemana.MIERCOLES, "Clase", "Mat2", idSalon, true, 2, 2);
        when(reservaRepository.findByIdSalon(idSalon)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorSalon(idSalon);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByIdSalon(idSalon);
    }
    
    @Test
    public void testConsultarReservasPorFecha() {
        // Arrange
        LocalDate fecha = LocalDate.now();
        Reserva reserva1 = new Reserva(fecha, 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, 1);
        Reserva reserva2 = new Reserva(fecha, 14, DiaSemana.LUNES, "Taller", "Mat3", "103", true, 4, 3);
        when(reservaRepository.findByFechaReserva(fecha)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorFecha(fecha);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByFechaReserva(fecha);
    }
    
    @Test
    public void testConsultarReservasPorHora() {
        // Arrange
        double hora = 10.0;
        Reserva reserva1 = new Reserva(LocalDate.now(), hora, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, 1);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(1), hora, DiaSemana.MARTES, "Clase", "Mat2", "102", true, 2, 2);
        when(reservaRepository.findByHora(hora)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorHora(hora);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByHora(hora);
    }
    
    @Test
    public void testConsultarReservasPorDiaSemana() {
        // Arrange
        DiaSemana diaSemana = DiaSemana.LUNES;
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, diaSemana, "Reunión", "Mat1", "101", false, 3, 1);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(7), 14, diaSemana, "Asesoría", "Mat4", "105", false, 5, 2);
        when(reservaRepository.findByDiaSemana(diaSemana)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorDiaSemana(diaSemana);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByDiaSemana(diaSemana);
    }
    
    @Test
    public void testConsultarReservasPorEstado() {
        // Arrange
        EstadoReserva estado = EstadoReserva.ACTIVA;
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, 1);
        reserva1.setEstado(estado);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(1), 11, DiaSemana.MARTES, "Clase", "Mat2", "102", true, 2, 2);
        reserva2.setEstado(estado);
        when(reservaRepository.findByEstado(estado)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorEstado(estado);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByEstado(estado);
    }
    
    @Test
    public void testConsultarReservasPorDuracionBloque() {
        // Arrange
        boolean duracionBloque = true;
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Clase", "Mat1", "101", duracionBloque, 3, 1);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(2), 13, DiaSemana.MIERCOLES, "Lab", "Mat5", "201", duracionBloque, 4, 3);
        when(reservaRepository.findByDuracionBloque(duracionBloque)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorDuracionBloque(duracionBloque);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByDuracionBloque(duracionBloque);
    }
    
    @Test
    public void testConsultarReservasPorSalonAndEstado() {
        // Arrange
        String idSalon = "101";
        EstadoReserva estado = EstadoReserva.ACTIVA;
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", idSalon, false, 3, 1);
        reserva1.setEstado(estado);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(1), 11, DiaSemana.MARTES, "Clase", "Mat2", idSalon, true, 2, 2);
        reserva2.setEstado(estado);
        Reserva reserva3 = new Reserva(LocalDate.now().plusDays(2), 14, DiaSemana.MIERCOLES, "Taller", "Mat3", idSalon, false, 4, 3);
        reserva3.setEstado(EstadoReserva.DESHABILITADA);
        
        when(reservaRepository.findByIdSalon(idSalon)).thenReturn(Arrays.asList(reserva1, reserva2, reserva3));
        
        // Act
        List<Reserva> reservas = reservaService.consultarReservasPorSalonAndEstado(idSalon, estado);
        
        // Assert
        assertEquals(2, reservas.size());
        verify(reservaRepository).findByIdSalon(idSalon);
    }
    

    @Test
    public void testConsultarReserva() {
        // Arrange
        String idReserva = "abc123";
        Reserva reservaEsperada = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, 1);
        reservaEsperada.setIdReserva(idReserva);
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reservaEsperada);
        
        // Act
        Reserva reserva = reservaService.consultarReserva(idReserva);
        
        // Assert
        assertNotNull(reserva);
        assertEquals(idReserva, reserva.getIdReserva());
        verify(reservaRepository).findByIdReserva(idReserva);
    }
    

    
    @Test
    public void testCrearReservaInvalidPriority() {
        // Arrange
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 9;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Estudio";
        String materia = "Matemáticas";
        String idSalon = "201";
        boolean duracionBloque = true;
        int prioridad = 6; // Invalid priority (should be 1-5)
        int idInstitucional = 1;
        
        // Act
        reservaService.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);
        
        // Assert
        verify(reservaRepository, never()).save(any());
    }
    
    
    
    @Test
    public void testCrearReservaNoSalon() {
        // Arrange
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 9;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Estudio";
        String materia = "Matemáticas";
        String idSalon = ""; // Empty salon ID
        boolean duracionBloque = true;
        int prioridad = 3;
        int idInstitucional = 1;
        
        // Act
        reservaService.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);
        
        // Assert
        verify(reservaRepository, never()).save(any());
    }
    
    @Test
    public void testCrearReservaNoUsuario() {
        // Arrange
        LocalDate fechaReserva = LocalDate.now().plusDays(1);
        double hora = 9;
        DiaSemana diaSemana = DiaSemana.LUNES;
        String proposito = "Estudio";
        String materia = "Matemáticas";
        String idSalon = "201";
        boolean duracionBloque = true;
        int prioridad = 3;
        int idInstitucional = 0; // Invalid user ID
        
        // Act
        reservaService.crearReserva(fechaReserva, hora, diaSemana, proposito, materia, idSalon, duracionBloque, prioridad, idInstitucional);
        
        // Assert
        verify(reservaRepository, never()).save(any());
    }
    
    @Test
    public void testActualizarReserva() {
        // Arrange
        String idReserva = "reserva123";
        Reserva reservaExistente = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Old purpose", "Old subject", "101", false, 3, 1);
        reservaExistente.setIdReserva(idReserva);
        reservaExistente.setEstado(EstadoReserva.ACTIVA);
        
        LocalDate nuevaFecha = LocalDate.now().plusDays(1);
        double nuevaHora = 14;
        DiaSemana nuevoDia = DiaSemana.MARTES;
        String nuevoProposito = "New purpose";
        String nuevaMateria = "New subject";
        String nuevoSalon = "202";
        boolean nuevaDuracion = true;
        int nuevaPrioridad = 4;
        int nuevoUsuario = 2;
        
        // Mock the ReservaDTO since it only has getters
        ReservaDTO reservaDTO = Mockito.mock(ReservaDTO.class);
        when(reservaDTO.getFechaReserva()).thenReturn(nuevaFecha);
        when(reservaDTO.getHora()).thenReturn(nuevaHora);
        when(reservaDTO.getDiaSemana()).thenReturn(nuevoDia);
        when(reservaDTO.getProposito()).thenReturn(nuevoProposito);
        when(reservaDTO.getMateria()).thenReturn(nuevaMateria);
        when(reservaDTO.getIdSalon()).thenReturn(nuevoSalon);
        when(reservaDTO.isDuracionBloque()).thenReturn(nuevaDuracion);
        when(reservaDTO.getPrioridad()).thenReturn(nuevaPrioridad);
        when(reservaDTO.getIdUsuario()).thenReturn(nuevoUsuario);
        
        when(reservaRepository.findByIdReserva(idReserva)).thenReturn(reservaExistente);
        
        // Act
        reservaService.actualizarReserva(idReserva, reservaDTO);
        
        // Assert
        ArgumentCaptor<Reserva> reservaCaptor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaRepository).save(reservaCaptor.capture());
        Reserva updatedReserva = reservaCaptor.getValue();
        
        assertEquals(nuevaFecha, updatedReserva.getFechaReserva());
        assertEquals(nuevaHora, updatedReserva.getHora());
        assertEquals(nuevoDia, updatedReserva.getDiaSemana());
        assertEquals(nuevoProposito, updatedReserva.getProposito());
        assertEquals(nuevaMateria, updatedReserva.getMateria());
        assertEquals(nuevoSalon, updatedReserva.getIdSalon());
        assertEquals(nuevaDuracion, updatedReserva.isDuracionBloque());
        assertEquals(nuevaPrioridad, updatedReserva.getPrioridad());
        assertEquals(nuevoUsuario, updatedReserva.getIdUsuario());
        assertEquals(EstadoReserva.ACTIVA, updatedReserva.getEstado());
    }
    
    @Test
    public void testConsultarReservasPorUsuarioInteger() {
        // Arrange
        Integer idUsuario = 1; // Using Integer instead of int
        Reserva reserva1 = new Reserva(LocalDate.now(), 10, DiaSemana.LUNES, "Reunión", "Mat1", "101", false, 3, idUsuario);
        Reserva reserva2 = new Reserva(LocalDate.now().plusDays(1), 11, DiaSemana.MARTES, "Clase", "Mat2", "102", true, 2, idUsuario);
        when(reservaRepository.findByIdUsuario(idUsuario)).thenReturn(Arrays.asList(reserva1, reserva2));
        
        // Act - call the method with Integer parameter
        List<Reserva> reservas = reservaService.consultarReservasPorUsuario(idUsuario);
        
        // Assert
        assertEquals(2, reservas.size());
        assertEquals(reserva1, reservas.get(0));
        assertEquals(reserva2, reservas.get(1));
        verify(reservaRepository).findByIdUsuario(idUsuario);
    }
    
}