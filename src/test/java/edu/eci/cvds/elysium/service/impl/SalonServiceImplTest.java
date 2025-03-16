// package edu.eci.cvds.elysium.service.impl;

// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.mockito.junit.jupiter.MockitoExtension;

// import edu.eci.cvds.elysium.dto.salon.SalonDTO;
// import edu.eci.cvds.elysium.model.Salon;
// import edu.eci.cvds.elysium.repository.SalonRepository;

// @ExtendWith(MockitoExtension.class)
// public class SalonServiceImplTest {

//     @Mock
//     private SalonRepository salonRepository;

//     @InjectMocks
//     private SalonServiceImpl salonService;

//     @BeforeEach
//     public void setUp() {
//         // @InjectMocks handles the injection of the mock repository.
//     }

//     // Additional tests for methods not covered in existing tests

//     @Test
//     public void testFindByActivoTrue() {
//         List<Salon> expected = Arrays.asList(
//                 new Salon("Salon Activo1", "A101", "Ubicacion 1", 50,"Descripcion 1"),
//                 new Salon("Salon Activo2", "A102", "Ubicacion 2", 60,"Descripcion 1"));
//         when(salonRepository.findByActivoTrue()).thenReturn(expected);
//         List<Salon> result = salonService.findByActivoTrue();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByActivoTrue();
//     }

//     @Test
//     public void testFindByActivoFalse() {
//         List<Salon> expected = Collections.singletonList(new Salon("Salon Inactivo", "I201", "Ubicacion X", 40,"Descripcion 1"));
//         when(salonRepository.findByActivoFalse()).thenReturn(expected);
//         List<Salon> result = salonService.findByActivoFalse();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByActivoFalse();
//     }

//     @Test
//     public void testFindByDisponibleTrue() {
//         List<Salon> expected = Arrays.asList(
//                 new Salon("Salon Disp1", "D301", "Ubicacion A", 55,"Descripcion 1"),
//                 new Salon("Salon Disp2", "D302", "Ubicacion B", 65,"Descripcion 1"));
//         when(salonRepository.findByDisponibleTrue()).thenReturn(expected);
//         List<Salon> result = salonService.findByDisponibleTrue();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByDisponibleTrue();
//     }

//     @Test
//     public void testFindByDisponibleFalse() {
//         List<Salon> expected = Collections.singletonList(new Salon("Salon NoDisp", "ND401", "Ubicacion Z", 70,"Descripcion 1"));
//         when(salonRepository.findByDisponibleFalse()).thenReturn(expected);
//         List<Salon> result = salonService.findByDisponibleFalse();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByDisponibleFalse();
//     }

//     @Test
//     public void testFindByActivoTrueAndDisponibleTrue() {
//         List<Salon> expected = Collections.singletonList(new Salon("Salon Both True", "BT501", "Ubicacion BT", 80,"Descripcion 1"));
//         when(salonRepository.findByActivoTrueAndDisponibleTrue()).thenReturn(expected);
//         List<Salon> result = salonService.findByActivoTrueAndDisponibleTrue();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByActivoTrueAndDisponibleTrue();
//     }

//     @Test
//     public void testFindByActivoTrueAndDisponibleFalse() {
//         List<Salon> expected = Collections
//                 .singletonList(new Salon("Salon Activo DispFalse", "ADF601", "Ubicacion ADF", 90,"Descripcion 1"));
//         when(salonRepository.findByActivoTrueAndDisponibleFalse()).thenReturn(expected);
//         List<Salon> result = salonService.findByActivoTrueAndDisponibleFalse();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByActivoTrueAndDisponibleFalse();
//     }

//     @Test
//     public void testFindByActivoFalseAndDisponibleTrue() {
//         List<Salon> expected = Collections
//                 .singletonList(new Salon("Salon Inactivo DispTrue", "IDT701", "Ubicacion IDT", 100,"Descripcion 1"));
//         when(salonRepository.findByActivoFalseAndDisponibleTrue()).thenReturn(expected);
//         List<Salon> result = salonService.findByActivoFalseAndDisponibleTrue();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByActivoFalseAndDisponibleTrue();
//     }

//     @Test
//     public void testFindByActivoFalseAndDisponibleFalse() {
//         List<Salon> expected = Collections.singletonList(new Salon("Salon Both False", "BF801", "Ubicacion BF", 110,"Descripcion 1"));
//         when(salonRepository.findByActivoFalseAndDisponibleFalse()).thenReturn(expected);
//         List<Salon> result = salonService.findByActivoFalseAndDisponibleFalse();
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByActivoFalseAndDisponibleFalse();
//     }

//     @Test
//     public void testFindByCapacidadGreaterThanEqual() {
//         int capacidad = 60;
//         List<Salon> expected = Arrays.asList(
//                 new Salon("Salon Cap1", "C901", "Ubicacion C1", 60,"Descripcion 1"),
//                 new Salon("Salon Cap2", "C902", "Ubicacion C2", 80,"Descripcion 1"));
//         when(salonRepository.findByCapacidadGreaterThanEqual(capacidad)).thenReturn(expected);
//         List<Salon> result = salonService.findByCapacidadGreaterThanEqual(capacidad);
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByCapacidadGreaterThanEqual(capacidad);
//     }

//     @Test
//     public void testFindByCapacidadLessThanEqual() {
//         int capacidad = 70;
//         List<Salon> expected = Arrays.asList(
//                 new Salon("Salon Cap3", "C903", "Ubicacion C3", 50,"Descripcion 1"),
//                 new Salon("Salon Cap4", "C904", "Ubicacion C4", 70,"Descripcion 1"));
//         when(salonRepository.findByCapacidadLessThanEqual(capacidad)).thenReturn(expected);
//         List<Salon> result = salonService.findByCapacidadLessThanEqual(capacidad);
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByCapacidadLessThanEqual(capacidad);
//     }

//     @Test
//     public void testFindByNombreAndUbicacionContainingIgnoreCase() {
//         String nombre = "Salon";
//         String ubicacion = "Ubicacion";
//         List<Salon> expected = Collections.singletonList(new Salon("Salon Combo", "CB101", "Ubicacion Combo", 85,"Descripcion 1"));
//         when(salonRepository.findByNombreAndUbicacionContainingIgnoreCase(nombre, ubicacion)).thenReturn(expected);
//         List<Salon> result = salonService.findByNombreAndUbicacionContainingIgnoreCase(nombre, ubicacion);
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByNombreAndUbicacionContainingIgnoreCase(nombre, ubicacion);
//     }

//     @Test
//     public void testInstantiation() {
//         assertNotNull(salonService);
//     }

//     @Test
//     public void testFindByMnemonico() {
//         String mnemonico = "A101";
//         Salon expectedSalon = new Salon("Salon A", mnemonico, "Ubicacion A", 50,"Descripcion 1");
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(expectedSalon);
//         Salon result = salonService.findByMnemonico(mnemonico);
//         assertEquals(expectedSalon, result);
//         verify(salonRepository, times(1)).findByMnemonico(mnemonico);
//     }

//     @Test
//     public void testFindAll() {
//         List<Salon> expectedList = Arrays.asList(
//                 new Salon("Salon A", "A101", "Ubicacion A", 50,"Descripcion 1"),
//                 new Salon("Salon B", "B202", "Ubicacion B", 75,"Descripcion 1"));
//         when(salonRepository.findAll()).thenReturn(expectedList);
//         List<Salon> resultList = salonService.findAll();
//         assertEquals(expectedList, resultList);
//         verify(salonRepository, times(1)).findAll();
//     }

//     @Test
//     public void testAgregarSalon() {
//         String nombre = "Nuevo Salon";
//         String mnemonico = "N303";
//         String ubicacion = "Ubicacion Nueva";
//         int capacidad = 100;
//         String description = "Descripcion Nueva";
//         // Call the service method
//         salonService.agregarSalon(nombre, mnemonico, ubicacion, capacidad,description);
//         // Capture the Salon passed to the repository
//         ArgumentCaptor<Salon> salonCaptor = ArgumentCaptor.forClass(Salon.class);
//         verify(salonRepository, times(1)).save(salonCaptor.capture());
//         Salon savedSalon = salonCaptor.getValue();
//         assertEquals(nombre, savedSalon.getNombre());
//         assertEquals(mnemonico, savedSalon.getMnemonico());
//         assertEquals(ubicacion, savedSalon.getUbicacion());
//         assertEquals(capacidad, savedSalon.getCapacidad());
//     }

//     @Test
//     public void testDeshabilitarSalon() {
//         String mnemonico = "D404";
//         Salon salon = new Salon("Salon D", mnemonico, "Ubicacion D", 60,"Descripcion 1");
//         salon.setActivo(true);
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         salonService.deshabilitarSalon(mnemonico);
//         assertFalse(salon.isActivo());
//         verify(salonRepository, times(1)).save(salon);
//     }

//     @Test
//     public void testHabilitarSalon() {
//         String mnemonico = "H505";
//         Salon salon = new Salon("Salon H", mnemonico, "Ubicacion H", 80,"Descripcion 1");
//         salon.setActivo(false);
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         salonService.habilitarSalon(mnemonico);
//         assertTrue(salon.isActivo());
//         verify(salonRepository, times(1)).save(salon);
//     }

//     @Test
//     public void testGetActivo() {
//         String mnemonico = "G606";
//         Salon salon = new Salon("Salon G", mnemonico, "Ubicacion G", 70,  "Descripcion 1");
//         salon.setActivo(true);
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         assertTrue(salonService.getActivo(mnemonico));
//         // Test for non-existent salon
//         when(salonRepository.findByMnemonico("NONEXIST")).thenReturn(null);
//         assertFalse(salonService.getActivo("NONEXIST"));
//     }

//     @Test
//     public void testActualizarSalon() {
//         String mnemonico = "U707";
//         Salon salon = new Salon("Salon U", mnemonico, "Ubicacion U", 90,"Descripcion 1");
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         SalonDTO dto = new SalonDTO();
//         dto.setNombre("Salon Updated");
//         dto.setUbicacion("Ubicacion Updated");
//         dto.setCapacidad(120);
//         salonService.actualizarSalon(mnemonico, dto);
//         // Assert that salon is updated based on DTO
//         assertEquals("Salon Updated", salon.getNombre());
//         assertEquals("Ubicacion Updated", salon.getUbicacion());
//         assertEquals(120, salon.getCapacidad());
//         verify(salonRepository, times(1)).save(salon);
//     }

//     @Test
//     public void testGetDisponible() {
//         String mnemonico = "D808";
//         Salon salon = new Salon("Salon D", mnemonico, "Ubicacion D", 55,"Descripcion 1");
//         salon.setDisponible(true);
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         assertTrue(salonService.getDisponible(mnemonico));
//         // Non existent salon returns false
//         when(salonRepository.findByMnemonico("NONEXIST")).thenReturn(null);
//         assertFalse(salonService.getDisponible("NONEXIST"));
//     }

//     @Test
//     public void testSetDisponible() {
//         String mnemonico = "SD909";
//         Salon salon = new Salon("Salon SD", mnemonico, "Ubicacion SD", 65,"Descripcion 1");
//         salon.setDisponible(false);
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         boolean result = salonService.setDisponible(mnemonico);
//         assertTrue(result);
//         assertTrue(salon.isDisponible());
//         verify(salonRepository, times(1)).save(salon);
//         // Test when salon not found
//         when(salonRepository.findByMnemonico("NONEXIST")).thenReturn(null);
//         boolean result2 = salonService.setDisponible("NONEXIST");
//         assertFalse(result2);
//     }

//     @Test
//     public void testSetNoDisponible() {
//         String mnemonico = "SND010";
//         Salon salon = new Salon("Salon SND", mnemonico, "Ubicacion SND", 75,"Descripcion 1");
//         salon.setDisponible(true);
//         when(salonRepository.findByMnemonico(mnemonico)).thenReturn(salon);
//         boolean result = salonService.setNoDisponible(mnemonico);
//         assertTrue(result);
//         assertFalse(salon.isDisponible());
//         verify(salonRepository, times(1)).save(salon);
//         // Test when salon not found
//         when(salonRepository.findByMnemonico("NONEXIST")).thenReturn(null);
//         boolean result2 = salonService.setNoDisponible("NONEXIST");
//         assertFalse(result2);
//     }

//     @Test
//     public void testFindByNombreContainingIgnoreCase() {
//         String nombreFragment = "salon";
//         List<Salon> expected = Collections.singletonList(new Salon("Salon X", "X111", "Ubicacion X", 40,"Descripcion 1"));
//         when(salonRepository.findByNombreContainingIgnoreCase(nombreFragment)).thenReturn(expected);
//         List<Salon> result = salonService.findByNombreContainingIgnoreCase(nombreFragment);
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByNombreContainingIgnoreCase(nombreFragment);
//     }

//     @Test
//     public void testFindByUbicacionContainingIgnoreCase() {
//         String ubicacionFragment = "ubicacion";
//         List<Salon> expected = Collections.singletonList(new Salon("Salon Y", "Y222", "Ubicacion Y", 85,"Descripcion 1"));
//         when(salonRepository.findByUbicacionContainingIgnoreCase(ubicacionFragment)).thenReturn(expected);
//         List<Salon> result = salonService.findByUbicacionContainingIgnoreCase(ubicacionFragment);
//         assertEquals(expected, result);
//         verify(salonRepository, times(1)).findByUbicacionContainingIgnoreCase(ubicacionFragment);
//     }
// }