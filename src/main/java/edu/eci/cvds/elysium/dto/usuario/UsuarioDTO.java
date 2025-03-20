package edu.eci.cvds.elysium.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    @NotNull(message = "El id no puede ser nulo")
    @Size(min = 10, max = 10, message = "El id debe tener exactamente 10 dígitos")
    @JsonProperty("idInstitucional")
    private Integer idInstitucional; // para identificar al usuario

    //we use boolean wrapper class to allow null values
    private Boolean isAdmin;         // opcional
    private String nombre;           // opcional
    private String apellido;         // opcional
    
    @NotNull(message = "El correo no puede ser nulo")
    @Pattern(
    regexp = "^[a-zA-Z]+\\.[a-zA-Z]+@escuelaing\\.edu\\.co$", 
    message = "El correo debe tener el formato nombre.apellido@escuelaing.edu.co"
    )
    private String correo;           // opcional
    
    private Boolean activo;         // opcional

    /**
     * Default constructor
     */
    public UsuarioDTO() {        
    }

    // GETTERS

    /**
     * Get the user's institutional ID
     * @return the user's institutional ID
     */
    public Integer getId() { return idInstitucional; }

    /**
     * Get the user's name
     * @return the user's name
     */
    public String getNombre() { return nombre; }

    /**
     * Get the user's last name
     * @return the user's last name
     */
    public String getApellido() { return apellido; }

    /**
     * Get the user's email
     * @return the user's email
     */
    public String getCorreo() { return correo; }

    /**
     * Get the user's admin status
     * @return the user's admin status
     */
    public Boolean getIsAdmin() { return isAdmin; }

    /**
     * Get the user's active status
     * @return the user's active status
     */
    public Boolean getActivo() { return activo; }

}