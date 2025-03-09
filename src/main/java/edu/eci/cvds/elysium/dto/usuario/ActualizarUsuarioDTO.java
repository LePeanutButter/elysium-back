package edu.eci.cvds.elysium.dto.usuario;

public class ActualizarUsuarioDTO {
    private Integer idInstitucional; // para identificar al usuario
    private String nombre;           // opcional
    private String apellido;         // opcional
    private String correo;           // opcional
    private Boolean isAdmin;         // opcional

    public ActualizarUsuarioDTO() {
    }

    public ActualizarUsuarioDTO(Integer idInstitucional, String nombre, String apellido, String correo, Boolean isAdmin) {
        this.idInstitucional = idInstitucional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.isAdmin = isAdmin;
    }

    public Integer getIdInstitucional() {   
        return idInstitucional;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
}