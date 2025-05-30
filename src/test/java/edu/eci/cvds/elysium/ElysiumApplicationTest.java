package edu.eci.cvds.elysium;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;




public class ElysiumApplicationTest {

    @Test
    public void testMainDoesNotThrowException() {
        String[] args = {};
        assertDoesNotThrow(() -> ElysiumApplication.main(args));
    }
}