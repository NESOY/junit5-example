package extension_model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static extension_model.RandomParametersExtension.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * {@link RandomParametersExtension}
 * Others Extensions
 * Mockito
 * - https://github.com/mockito/mockito/blob/release/2.x/subprojects/junit-jupiter/src/main/java/org/mockito/junit/jupiter/MockitoExtension.java
 * Spring
 * - https://github.com/spring-projects/spring-framework/blob/master/spring-test/src/main/java/org/springframework/test/context/junit/jupiter/SpringExtension.java
 */
@ExtendWith(RandomParametersExtension.class)
class ExtendWithTest {

    @Test
    void injectsInteger(@Random int i, @Random int j) {
        System.out.println(i + " " + j);
        assertNotEquals(i, j);
    }

    @Test
    void injectsDouble(@Random double d) {
        assertEquals(0.0, d, 1.0);
    }
}
