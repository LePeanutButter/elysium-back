package edu.eci.cvds.elysium.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.elysium.model.usuario.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {
    

    // Este método es opcional si usas el ID de tipo Integer en la anotación @Id
    Usuario findByIdInstitucional(int idInstitucional);

    List<Usuario>findAll();

    // Retorna todos los usuarios activos
    List<Usuario> findByActivoTrue();

    // Retorna todos los usuarios inactivos
    List<Usuario> findByActivoFalse();

    // Retorna todos los usuarios que son administradores
    List<Usuario> findByIsAdminTrue();

    // Retorna todos los usuarios que NO son admi   nistradores
    List<Usuario> findByIsAdminFalse();

    // Usuarios activos que son administradores
    List<Usuario> findByActivoTrueAndIsAdminTrue();

    // Usuarios activos que NO son administradores
    List<Usuario> findByActivoTrueAndIsAdminFalse();

    // Usuarios inactivos que son administradores
    List<Usuario> findByActivoFalseAndIsAdminTrue();

    // Usuarios inactivos que NO son administradores
    List<Usuario> findByActivoFalseAndIsAdminFalse();

}