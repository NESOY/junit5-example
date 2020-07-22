package extension_model;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * ExtendWith과는 다르게 관련 필드를 설정할 수 있다.
 */
public class RegisterExtensionTest {
    @RegisterExtension
    static RandomParametersExtension randomParametersExtension = new RandomParametersExtension(5);

    @RepeatedTest(1000)
    void registerExtenstionTest(@RandomParametersExtension.Random int value){
        assertTrue(value <= 5);
    }
}
