package bbw.ng.cypresstutorialbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class CypressTutorialBackendApplicationTests {

    @Test
    void main_shouldStartApplication() {
        assertDoesNotThrow(() -> CypressTutorialBackendApplication.main(new String[]{}));
    }


}
