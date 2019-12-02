package test_interface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Interface로 선언하여 필요한 기능을 쉽게 확장 가능
 * AOP가 좋을까 Interface 방식이 좋을까?
 */
class TestInterfaceDemo implements TestLifecycleLogger, TimeExecutionLogger{

    @Test
    void isEqualValue() {
        assertEquals(1, "a".length(), "is always equal");
    }

}