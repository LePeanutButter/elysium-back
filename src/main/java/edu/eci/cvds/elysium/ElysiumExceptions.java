package edu.eci.cvds.elysium;

/**
 * This class contains all the exceptions that we'll do in Elysium.
 * @author Andersson David Sánchez Méndez
 * @author Cristian Santiago Pedraza Rodríguez
 * 
 * @version 2025
 */
public class ElysiumExceptions extends Exception {

    public static final String NO_EXISTE_USUARIO = "El usuario no existe";
    public static final String YA_EXISTE_USUARIO = "El usuario ya existe";
    public static final String NO_EXISTE_RECURSO = "El recurso no existe";
    public static final String NO_EXISTE_RESERVA = "La reserva no existe";
    public static final String YA_EXISTE_SALON = "El salon ya existe";
    public static final String NO_EXISTE_SALON = "El salon no existe";
    public static final String ID_NO_VALIDO = "El id no es valido";
    public static final String CORREO_NO_VALIDO = "El correo no es valido";
    public static final String YA_EXISTE_CORREO = "El correo ya existe";
    public static final String HORA_NO_VALIDA = "La hora no es valida";
    public static final String DIA_NO_VALIDO = "El dia no es valido";
    public static final String CAPACIDAD_NO_VALIDA = "La capacidad no es valida";
    public static final String PRIORIDAD_NO_VALIDA = "La prioridad no es valida";

    
    /**
     * Constructor of the class.
     * @param message The message of the exception.
     */
    public ElysiumExceptions(String message) {
        super(message);
    }

    
    
}
