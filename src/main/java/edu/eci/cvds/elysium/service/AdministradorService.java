package edu.eci.cvds.elysium.service;

public interface AdministradorService extends UsuarioService {
    void actualizarInformacionUsuario(int idInstitucional, char tipoCampo, String value);
    void deshabilitarUsuario(int idInstitucional);
    void habilitarUsuario(int idInstitucional);
    void agregarUsuario(int idInstitucional, String nombre, String apellido, String correoInstitucional, boolean isAdmin);
    void añadirSalon(int adminId, String nombre, String ubicacion, int capacidad);
    void hacerAdmin(int id);
}
