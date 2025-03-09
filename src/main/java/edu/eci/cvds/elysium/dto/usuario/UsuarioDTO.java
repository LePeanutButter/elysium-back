package edu.eci.cvds.elysium.dto.usuario;
// DTO para recibir los datos en JSON
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private boolean isAdmin;

    // Constructor usuario estandard con isAdmin
    public UsuarioDTO(int id, String nombre, String apellido, String correo, boolean isAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.isAdmin = isAdmin; 
    }


    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public boolean getIsAdmin() {return isAdmin;}
}   