package edu.eci.cvds.elysium.Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import edu.eci.cvds.elysium.ElysiumExceptions;






public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    
    // Use the production ElysiumExceptions instead of a dummy inner class.
    
    @BeforeEach
    public void setUp() {
        handler = new GlobalExceptionHandler();
    }
    
    @Test
    public void testHandleElysiumExceptions_BadRequest_ID() {
        ElysiumExceptions ex = new ElysiumExceptions(ElysiumExceptions.ID_NO_VALIDO);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleElysiumExceptions(ex);
        
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().get("status"));
        assertEquals("INVALID_ID", responseEntity.getBody().get("code"));
        assertEquals(ElysiumExceptions.ID_NO_VALIDO, responseEntity.getBody().get("message"));
    }
    
    @Test
    public void testHandleElysiumExceptions_BadRequest_Email() {
        ElysiumExceptions ex = new ElysiumExceptions(ElysiumExceptions.CORREO_NO_VALIDO);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleElysiumExceptions(ex);
        
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().get("status"));
        assertEquals("INVALID_EMAIL", responseEntity.getBody().get("code"));
        assertEquals(ElysiumExceptions.CORREO_NO_VALIDO, responseEntity.getBody().get("message"));
    }
    
    @Test
    public void testHandleElysiumExceptions_Conflict_User() {
        ElysiumExceptions ex = new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_USUARIO);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleElysiumExceptions(ex);
        
        assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getBody().get("status"));
        assertEquals("USER_EXISTS", responseEntity.getBody().get("code"));
        assertEquals(ElysiumExceptions.YA_EXISTE_USUARIO, responseEntity.getBody().get("message"));
    }
    
    @Test
    public void testHandleElysiumExceptions_Conflict_Email() {
        ElysiumExceptions ex = new ElysiumExceptions(ElysiumExceptions.YA_EXISTE_CORREO);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleElysiumExceptions(ex);
        
        assertEquals(HttpStatus.CONFLICT.value(), responseEntity.getBody().get("status"));
        assertEquals("EMAIL_EXISTS", responseEntity.getBody().get("code"));
        assertEquals(ElysiumExceptions.YA_EXISTE_CORREO, responseEntity.getBody().get("message"));
    }
    
    @Test
    public void testHandleElysiumExceptions_NotFound_User() {
        ElysiumExceptions ex = new ElysiumExceptions(ElysiumExceptions.NO_EXISTE_USUARIO);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleElysiumExceptions(ex);
        
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().get("status"));
        assertEquals("USER_NOT_FOUND", responseEntity.getBody().get("code"));
        assertEquals(ElysiumExceptions.NO_EXISTE_USUARIO, responseEntity.getBody().get("message"));
    }
    
    @Test
    public void testHandleElysiumExceptions_GenericError() {
        String customMessage = "Otro error";
        ElysiumExceptions ex = new ElysiumExceptions(customMessage);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleElysiumExceptions(ex);
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getBody().get("status"));
        assertEquals("GENERAL_ERROR", responseEntity.getBody().get("code"));
        assertEquals(customMessage, responseEntity.getBody().get("message"));
    }
    
    @Test
    public void testHandleValidationExceptions() {
        // Create a BindingResult with a FieldError for testing purposes.
        Object target = new Object();
        BindingResult bindingResult = new BeanPropertyBindingResult(target, "target");
        bindingResult.addError(new FieldError("target", "field1", "must not be empty"));
        bindingResult.addError(new FieldError("target", "field2", "must be a valid number"));
        
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleValidationExceptions(ex);
        Map<String, Object> body = responseEntity.getBody();
        
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.get("status"));
        assertEquals("VALIDATION_ERROR", body.get("code"));
        assertEquals("Error de validación", body.get("message"));
        assertTrue(body.get("errors").toString().contains("field1"));
        assertTrue(body.get("errors").toString().contains("field2"));
    }
    
    @Test
    public void testHandleGenericException() {
        Exception ex = new Exception("Generic failure");
        ResponseEntity<Map<String, Object>> responseEntity = handler.handleGenericException(ex);
        Map<String, Object> body = responseEntity.getBody();
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), body.get("status"));
        assertEquals("SYSTEM_ERROR", body.get("code"));
        assertEquals("Ha ocurrido un error inesperado", body.get("message"));
        assertEquals("Generic failure", body.get("details"));
    }
}