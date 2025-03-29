package edu.eci.cvds.elysium.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.usuario.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    /**
     * Busca un usuario por su ID
     * @param idInstitucional el ID del usuario
     * @return el usuario con el ID dado
     */
    // Este método es opcional si usas el ID de tipo Integer en la anotación @Id
    Usuario findByIdInstitucional(String idInstitucional);

    /**
     * Busca un usuario por su correo institucional
     * @param correoInstitucional el correo institucional del usuario
     * @return el usuario con el correo institucional dado
     */
    Usuario findByCorreoInstitucional(String correoInstitucional);

    /**
     * Look for all users
     * @return all users
     */
    @SuppressWarnings("null")
    List<Usuario>findAll();

    /**
     * Look at active users 
     * @return active users
     */
    // Retorna todos los usuarios activos
    List<Usuario> findByActivoTrue();

    /**
     * Look at inactive users
     * @return inactive users
     */
    // Retorna todos los usuarios inactivos
    List<Usuario> findByActivoFalse();

    /**
     * Look at users who are administrators
     * @return administrators
     */
    // Retorna todos los usuarios que son administradores
    List<Usuario> findByIsAdminTrue();

    /**
     * Look at users who are NOT administrators
     * @return users who are NOT administrators
     */
    // Retorna todos los usuarios que NO son admi   nistradores
    List<Usuario> findByIsAdminFalse();

    /**
     * Look at active users who are administrators
     * @return active users who are administrators
     */
    // Usuarios activos que son administradores
    List<Usuario> findByActivoTrueAndIsAdminTrue();

    /**
     * Look at active users who are NOT administrators
     * @return active users who are NOT administrators
     */
    // Usuarios activos que NO son administradores
    List<Usuario> findByActivoTrueAndIsAdminFalse();

    /**
     * Look at inactive users who are administrators
     * @return inactive users who are administrators
     */
    // Usuarios inactivos que son administradores
    List<Usuario> findByActivoFalseAndIsAdminTrue();

    /**
     * Look at inactive users who are NOT administrators
     * @return inactive users who are NOT administrators
     */
    // Usuarios inactivos que NO son administradores
    List<Usuario> findByActivoFalseAndIsAdminFalse();

    /**
     * Look at active users who are administrators and whose name contains the given text
     * @param idInstitucional idInstitucional of the user
     * @return active users who are administrators and whose name contains the given text
     */
    // Usuarios activos que son administradores y cuyo nombre contiene el texto dado
    boolean existsByIdInstitucional(String idInstitucional);

    /**
     * Look at active users who are administrators and whose name contains the given text
     * @param correoInstitucional email of the user
     * @return active users who are administrators and whose name contains the given text
     */
    // Usuarios activos que son administradores y cuyo nombre contiene el texto dado
    boolean existsByCorreoInstitucional(String correoInstitucional);


}