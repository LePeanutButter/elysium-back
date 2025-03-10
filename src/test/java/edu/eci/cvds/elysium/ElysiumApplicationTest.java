package edu.eci.cvds.elysium;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
public class ElysiumApplicationTest {

    @Test
    void contextLoads() {
        ElysiumApplication application = new ElysiumApplication();
        assertNotNull(application);
    }

    @Test
    void main() {
        ElysiumApplication.main(new String[] {});
    }
}
