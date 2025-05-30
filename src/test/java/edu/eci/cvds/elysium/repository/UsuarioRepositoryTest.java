package edu.eci.cvds.elysium.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import edu.eci.cvds.elysium.model.Usuario;


@DataMongoTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario1, usuario2, usuario3, usuario4;

    @BeforeEach
    public void setUp() {
        usuarioRepository.deleteAll();

        // Create test users
        // usuario1: active administrator
        usuario1 = new Usuario(1000087564, "Andersson", "Sanchez","andersson.sanchez@escuelaing.edu.co", false, false);
        usuario1.setIdInstitucional(1);
        usuario1.setCorreoInstitucional("user1@institution.edu");
        usuario1.setActivo(true);
        usuario1.setAdmin(false);

        // usuario2: active non-administrator
        usuario2 = new Usuario(1000087563, "Andersson", "Sanchez","andersson.sanchez@escuelaing.edu.co", false, false);
        usuario2.setIdInstitucional(2);
        usuario2.setCorreoInstitucional("user2@institution.edu");
        usuario2.setActivo(true);
        usuario2.setAdmin(false);

        // usuario3: inactive administrator
        usuario3 = new Usuario(1000087561, "Andersson", "Sanchez","andersson.sanchez@escuelaing.edu.co", false, false);
        usuario3.setIdInstitucional(3);
        usuario3.setCorreoInstitucional("user3@institution.edu");
        usuario3.setActivo(false);
        usuario3.setAdmin(false);

        // usuario4: inactive non-administrator
        usuario4 = new Usuario(1000087560, "Andersson", "Sanchez","andersson.sanchez@escuelaing.edu.co", false, false);
        usuario4.setIdInstitucional(4);
        usuario4.setCorreoInstitucional("user4@institution.edu");
        usuario4.setActivo(false);
        usuario4.setAdmin(false);

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        usuarioRepository.save(usuario3);
        usuarioRepository.save(usuario4);
    }

    @Test
    public void testFindByIdInstitucional() {
        Usuario found = usuarioRepository.findByIdInstitucional(1);
        assertNotNull(found);
        assertEquals("user1@institution.edu", found.getCorreoInstitucional());
    }

    @Test
    public void testFindByCorreoInstitucional() {
        Usuario found = usuarioRepository.findByCorreoInstitucional("user2@institution.edu");
        assertNotNull(found);
        assertEquals(2, found.getIdInstitucional());
    }

    @Test
    public void testFindAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(4, usuarios.size());
    }

    @Test
    public void testFindByActivoTrue() {
        List<Usuario> activos = usuarioRepository.findByActivoTrue();
        assertEquals(2, activos.size());
        assertTrue(activos.stream().allMatch(u -> u.isActivo()));
    }

    @Test
    public void testFindByActivoFalse() {
        List<Usuario> inactivos = usuarioRepository.findByActivoFalse();
        assertEquals(2, inactivos.size());
        assertTrue(inactivos.stream().allMatch(u -> !u.isActivo()));
    }

    @Test
    public void testFindByIsAdminTrue() {
        List<Usuario> admins = usuarioRepository.findByIsAdminTrue();
        assertEquals(0, admins.size());
        assertTrue(admins.stream().allMatch(u -> u.getIsAdmin()));
    }

    @Test
    public void testFindByIsAdminFalse() {
        List<Usuario> nonAdmins = usuarioRepository.findByIsAdminFalse();
        assertEquals(4, nonAdmins.size());
        assertTrue(nonAdmins.stream().allMatch(u -> !u.getIsAdmin()));
    }

    @Test
    public void testFindByActivoTrueAndIsAdminTrue() {
        List<Usuario> activosAdmins = usuarioRepository.findByActivoTrueAndIsAdminTrue();
        assertEquals(0, activosAdmins.size());

    }

    @Test
    public void testFindByActivoTrueAndIsAdminFalse() {
        List<Usuario> activosNonAdmins = usuarioRepository.findByActivoTrueAndIsAdminFalse();
        assertEquals(2, activosNonAdmins.size());
        Usuario u = activosNonAdmins.get(0);
        assertTrue(u.isActivo());
        assertFalse(u.getIsAdmin());
    }

    @Test
    public void testFindByActivoFalseAndIsAdminTrue() {
        List<Usuario> inactivosAdmins = usuarioRepository.findByActivoFalseAndIsAdminTrue();
        assertEquals(0, inactivosAdmins.size());
    }

    @Test
    public void testFindByActivoFalseAndIsAdminFalse() {
        List<Usuario> inactivosNonAdmins = usuarioRepository.findByActivoFalseAndIsAdminFalse();
        assertEquals(2, inactivosNonAdmins.size());
        Usuario u = inactivosNonAdmins.get(0);
        assertFalse(u.isActivo());
        assertFalse(u.getIsAdmin());
    }

    @Test
    public void testExistsByIdInstitucional() {
        boolean exists = usuarioRepository.existsByIdInstitucional(1);
        assertTrue(exists);
        assertFalse(usuarioRepository.existsByIdInstitucional(999));
    }

    @Test
    public void testExistsByCorreoInstitucional() {
        boolean exists = usuarioRepository.existsByCorreoInstitucional("user3@institution.edu");
        assertTrue(exists);
        assertFalse(usuarioRepository.existsByCorreoInstitucional("nonexistent@institution.edu"));
    }
}