package edu.eci.cvds.elysium.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.eci.cvds.elysium.ElysiumExceptions;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElysiumExceptions.class)
    public ResponseEntity<Map<String, Object>> handleElysiumExceptions(ElysiumExceptions ex) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status;
        
        // Asignar código HTTP apropiado según el tipo de error
        switch (ex.getMessage()) {
            case ElysiumExceptions.ID_NO_VALIDO:
            case ElysiumExceptions.CORREO_NO_VALIDO:
                status = HttpStatus.BAD_REQUEST;
                break;
            case ElysiumExceptions.YA_EXISTE_USUARIO:
            case ElysiumExceptions.YA_EXISTE_CORREO:
                status = HttpStatus.CONFLICT;
                break;
            case ElysiumExceptions.NO_EXISTE_USUARIO:
                status = HttpStatus.NOT_FOUND;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", ex.getMessage());
        response.put("code", getCodigo(ex.getMessage()));
        
        // Log para depuración
        System.out.println("Error procesado: " + response);
        
        return new ResponseEntity<>(response, status);
    }
    
    // Manejo de errores de validación de @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField, 
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing + ", " + replacement
                ));
        
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.put("message", "Error de validación");
        response.put("errors", errors);
        response.put("code", "VALIDATION_ERROR");
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    // Obtener un código que el frontend pueda usar para identificar el tipo de error
    private String getCodigo(String mensaje) {
        switch (mensaje) {
            case ElysiumExceptions.ID_NO_VALIDO:
                return "INVALID_ID";
            case ElysiumExceptions.CORREO_NO_VALIDO:
                return "INVALID_EMAIL";
            case ElysiumExceptions.YA_EXISTE_USUARIO:
                return "USER_EXISTS";
            case ElysiumExceptions.YA_EXISTE_CORREO:
                return "EMAIL_EXISTS";
            case ElysiumExceptions.NO_EXISTE_USUARIO:
                return "USER_NOT_FOUND";
            default:
                return "GENERAL_ERROR";
        }
    }
    
    // Manejador para excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Error interno del servidor");
        response.put("message", "Ha ocurrido un error inesperado");
        response.put("details", ex.getMessage());
        response.put("code", "SYSTEM_ERROR");
        
        // Log para depuración
        ex.printStackTrace();
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}