package conditional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnJre;

import static org.junit.jupiter.api.condition.JRE.*;

/**
 * Java의 Version에 따라 테스트 작성도 가능
 */
public class JRESystemConditionsTest {
    @Test
    @EnabledOnJre(JAVA_8)
    public void onlyOnJava8(){
        System.out.println("I am Java 8");
    }
    @Test
    @EnabledOnJre({ JAVA_9, JAVA_10 })
    void onJava9Or10() {
        // ...
    }

    @Test
    @DisabledOnJre(JAVA_9)
    void notOnJava9() {
        // ...
    }
}
