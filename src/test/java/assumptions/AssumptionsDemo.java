package assumptions;

import domain.Calculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * assume : 특정 환경에서만 테스트가 실행 될 수 있는 환경을 만들 수 있음.
 */
class AssumptionsDemo {
    private final Calculator calculator = new Calculator();

    /**
     * 특정 환경의 경우에만 Test 동작할 수 있도록
     */
    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        assertThat(1).isEqualTo(1);
    }

    /**
     * Message 전달도 가능
     */
    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")), () -> "Aborting test: not on developer workstation");
    }

    /**
     * 통합환경 TEST + CI 전용 테스트 작성도 가능
     */
    @Test
    void testInAllEnvironments() {
        //System.setProperty("ENV", "CI");
        assumingThat("CI".equals(System.getenv("ENV")), () -> assertEquals(2, calculator.divide(4, 2)));

        assertEquals(42, calculator.multiply(6, 7));
    }
}