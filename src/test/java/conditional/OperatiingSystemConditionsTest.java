package conditional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.condition.OS.*;

/**
 * 운영체재에 따라 전용테스트 작성 가능
 */
public class OperatiingSystemConditionsTest {
    @Test
    @EnabledOnOs({MAC, LINUX})
    public void onlyOnMacOS(){
        System.out.println("Hello My Mac");
    }

    @Test
    @DisabledOnOs({WINDOWS})
    void notOnWindows() {
        System.out.println("Good Bye My Window");
    }

    @TestOnMac
    public void onlyOnMacOSWithCustomAnnotation(){
        System.out.println("Hello My Mac");
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(MAC)
    @interface TestOnMac {
    }
}
