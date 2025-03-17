package edu.eci.cvds.elysium.dto.usuario;

public class UsuarioDTO {
    private Integer idInstitucional; // para identificar al usuario
    //we use boolean wrapper class to allow null values
    private Boolean isAdmin;         // opcional
    private String nombre;           // opcional
    private String apellido;         // opcional
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