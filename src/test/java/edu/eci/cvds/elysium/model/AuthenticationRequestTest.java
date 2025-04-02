package edu.eci.cvds.elysium.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;





public class AuthenticationRequestTest {

    @Test
    public void testConstructorGetterSetter() {
        String email = "correo@institucional.edu";
        String password = "password123";
        // Using all-args constructor
        AuthenticationRequest request = new AuthenticationRequest(email, password);
        assertEquals(email, request.getCorreoInstitucional());
        assertEquals(password, request.getPassword());

        // Test setters
        String newEmail = "nuevo@correo.edu";
        String newPassword = "newpass456";
        request.setCorreoInstitucional(newEmail);
        request.setPassword(newPassword);
        assertEquals(newEmail, request.getCorreoInstitucional());
        assertEquals(newPassword, request.getPassword());
    }

}