package edu.eci.cvds.elysium.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import edu.eci.cvds.elysium.model.Salon;



@DataMongoTest
public class SalonRepositoryTest {

    @Autowired
    private SalonRepository repository;

    @BeforeEach
    public void cleanDatabase() {
        repository.deleteAll();
    }
    
    private Salon createSalon(String id, String mnemonico, boolean activo, boolean disponible, String nombre, String ubicacion, int capacidad) {
        Salon s = new Salon();
        s.setMnemonico(mnemonico);
        s.setActivo(activo);
        s.setDisponible(disponible);
        s.setNombre(nombre);
        s.setUbicacion(ubicacion);
        s.setCapacidad(capacidad);
        return s;
    }
    
    @Test
    public void testFindByMnemonico() {
        Salon s = createSalon("1", "M001", true, true, "Salon A", "Building 1", 50);
        repository.insert(s);
        Salon found = repository.findByMnemonico("M001");
        assertNotNull(found);
        assertEquals("M001", found.getMnemonico());
    }
    
    @Test
    public void testFindAll() {
        Salon s1 = createSalon("1", "M001", true, false, "Salon A", "Building 1", 50);
        Salon s2 = createSalon("2", "M002", false, true, "Salon B", "Building 2", 30);
        repository.insert(s1);
        repository.insert(s2);
        List<Salon> salons = repository.findAll();
        assertEquals(2, salons.size());
    }
    
    @Test
    public void testFindByActivoTrue() {
        Salon activeSalon = createSalon("1", "M001", true, true, "Salon A", "Building 1", 50);
        Salon inactiveSalon = createSalon("2", "M002", false, true, "Salon B", "Building 2", 30);
        repository.insert(activeSalon);
        repository.insert(inactiveSalon);
        List<Salon> activeSalons = repository.findByActivoTrue();
        assertEquals(1, activeSalons.size());
        assertTrue(activeSalons.get(0).isActivo());
    }
    
    @Test
    public void testFindByActivoFalse() {
        Salon salon1 = createSalon("1", "M001", false, true, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, true, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> inactiveSalons = repository.findByActivoFalse();
        assertEquals(1, inactiveSalons.size());
        assertFalse(inactiveSalons.get(0).isActivo());
    }
    
    @Test
    public void testFindByDisponibleTrue() {
        Salon salon1 = createSalon("1", "M001", true, true, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, false, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> availableSalons = repository.findByDisponibleTrue();
        assertEquals(1, availableSalons.size());
        assertTrue(availableSalons.get(0).isDisponible());
    }
    
    @Test
    public void testFindByDisponibleFalse() {
        Salon salon1 = createSalon("1", "M001", true, false, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, true, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> notAvailableSalons = repository.findByDisponibleFalse();
        assertEquals(1, notAvailableSalons.size());
        assertFalse(notAvailableSalons.get(0).isDisponible());
    }
    
    @Test
    public void testFindByActivoTrueAndDisponibleTrue() {
        Salon salon1 = createSalon("1", "M001", true, true, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, false, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> salons = repository.findByActivoTrueAndDisponibleTrue();
        assertEquals(1, salons.size());
        assertTrue(salons.get(0).isActivo());
        assertTrue(salons.get(0).isDisponible());
    }
    
    @Test
    public void testFindByActivoTrueAndDisponibleFalse() {
        Salon salon1 = createSalon("1", "M001", true, false, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, true, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> salons = repository.findByActivoTrueAndDisponibleFalse();
        assertEquals(1, salons.size());
        assertTrue(salons.get(0).isActivo());
        assertFalse(salons.get(0).isDisponible());
    }
    
    @Test
    public void testFindByNombreContainingIgnoreCase() {
        Salon salon1 = createSalon("1", "M001", true, true, "Main Hall", "Building 1", 100);
        Salon salon2 = createSalon("2", "M002", true, true, "Side Room", "Building 2", 40);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> salons = repository.findByNombreContainingIgnoreCase("main");
        assertEquals(1, salons.size());
        assertTrue(salons.get(0).getNombre().toLowerCase().contains("main"));
    }
    
    @Test
    public void testFindByUbicacionContainingIgnoreCase() {
        Salon salon1 = createSalon("1", "M001", true, true, "Salon A", "North Wing", 60);
        Salon salon2 = createSalon("2", "M002", true, true, "Salon B", "South Wing", 60);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> salons = repository.findByUbicacionContainingIgnoreCase("south");
        assertEquals(1, salons.size());
        assertTrue(salons.get(0).getUbicacion().toLowerCase().contains("south"));
    }
    
    @Test
    public void testFindByCapacidadGreaterThanEqual() {
        Salon salon1 = createSalon("1", "M001", true, true, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, true, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> salons = repository.findByCapacidadGreaterThanEqual(40);
        assertEquals(1, salons.size());
        assertTrue(salons.get(0).getCapacidad() >= 40);
    }
    
    @Test
    public void testFindByCapacidadLessThanEqual() {
        Salon salon1 = createSalon("1", "M001", true, true, "Salon A", "Building 1", 50);
        Salon salon2 = createSalon("2", "M002", true, true, "Salon B", "Building 2", 30);
        repository.insert(salon1);
        repository.insert(salon2);
        List<Salon> salons = repository.findByCapacidadLessThanEqual(40);
        assertEquals(1, salons.size());
        assertTrue(salons.get(0).getCapacidad() <= 40);
    }
    
    @Test
    public void testFindByNombreAndUbicacionContainingIgnoreCase() {
        Salon salon = createSalon("1", "M001", true, true, "Conference Hall", "East Wing", 80);
        repository.insert(salon);
        List<Salon> salons = repository.findByNombreAndUbicacionContainingIgnoreCase("Conference Hall", "east");
        assertEquals(1, salons.size());
        assertEquals("Conference Hall", salons.get(0).getNombre());
        assertTrue(salons.get(0).getUbicacion().toLowerCase().contains("east"));
    }
    
    @Test
    public void testExistsByMnemonico() {
        Salon salon = createSalon("1", "EXIST01", true, true, "Salon Exist", "Location", 100);
        repository.insert(salon);
        boolean exists = repository.existsByMnemonico("EXIST01");
        assertTrue(exists);
    }
    
    @Test
    public void testCustomQueriesForNonStandardMethods() {
        // These methods have ambiguous JavaDoc comments, but testing their expected behavior.
        Salon salon1 = createSalon("1", "M001", false, true, "Salon X", "Zone 1", 45);
        Salon salon2 = createSalon("2", "M002", false, false, "Salon Y", "Zone 2", 35);
        repository.insert(salon1);
        repository.insert(salon2);
        
        List<Salon> result1 = repository.findByActivoFalseAndDisponibleTrue();
        assertEquals(1, result1.size());
        assertFalse(result1.get(0).isActivo());
        assertTrue(result1.get(0).isDisponible());
        
        List<Salon> result2 = repository.findByActivoFalseAndDisponibleFalse();
        assertEquals(1, result2.size());
        assertFalse(result2.get(0).isActivo());
        assertFalse(result2.get(0).isDisponible());
    }
}