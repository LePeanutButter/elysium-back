package edu.eci.cvds.elysium.dto.usuario;

public class ActualizarUsuarioDTO {
    private Integer idInstitucional; // para identificar al usuario
    private String nombre;           // opcional
    private String apellido;         // opcional
    private String correo;           // opcional
    private Boolean isAdmin;         // opcional

    public ActualizarUsuarioDTO() {        
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

    public void setIdInstitucional(Integer idInstitucional) {
        this.idInstitucional = idInstitucional;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}