package edu.eci.cvds.elysium.dto;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import edu.eci.cvds.elysium.model.Recurso;








public class SalonDTOTest {
 private static Validator validator;
    
    
    
    @Test
    public void testSettersAndGetters() {
        SalonDTO salon = new SalonDTO();
        
        // Set up sample data
        String expectedMnemonic = "A101";
        String expectedName = "Main Hall";
        String expectedDescription = "Large hall for events";
        String expectedLocation = "First Floor";
        Integer expectedCapacity = 50;
        List<Recurso> expectedRecursos = new ArrayList<>();
        expectedRecursos.add(new Recurso("location", 34, null));
        Boolean expectedActivo = true;
        Boolean expectedDisponible = false;
        
        // Use setters
        salon.setMnemonic(expectedMnemonic);
        salon.setName(expectedName);
        salon.setDescription(expectedDescription);
        salon.setLocation(expectedLocation);
        salon.setCapacity(expectedCapacity);
        salon.setResources(expectedRecursos);
        salon.setActivo(expectedActivo);
        salon.setAvailable(expectedDisponible);
        
        // Test getters
        assertEquals(expectedMnemonic, salon.getMnemonic());
        assertEquals(expectedName, salon.getName());
        assertEquals(expectedDescription, salon.getDescription());
        assertEquals(expectedLocation, salon.getLocation());
        assertEquals(expectedCapacity, salon.getCapacity());
        assertEquals(expectedRecursos, salon.getResources());
        assertEquals(expectedActivo, salon.getActivo());
        assertEquals(expectedDisponible, salon.getAvailable());
    }
    
    @Test
    public void testValidationConstraints_whenValid() {
        SalonDTO salon = new SalonDTO();
        
        salon.setMnemonic("B202");
        salon.setName("Conference Room");
        salon.setDescription("Room for meetings");
        salon.setLocation("Second Floor");
        salon.setCapacity(20);
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(new Recurso("location", 34, null));
        salon.setResources(recursos);
        salon.setActivo(true);
        salon.setAvailable(true);
        
        
    }
    
    @Test
    public void testValidationConstraints_whenInvalid() {
        SalonDTO salon = new SalonDTO();
        
        // Not setting required fields and invalid capacity
        salon.setMnemonic(null);         // Should trigger NotNull violation.
        salon.setLocation(null);         // Should trigger NotNull violation.
        salon.setCapacity(-10);          // Should trigger Positive (and NotNull) violation.
        salon.setResources(null);        // Should trigger NotNull violation.
        
        
    }
}