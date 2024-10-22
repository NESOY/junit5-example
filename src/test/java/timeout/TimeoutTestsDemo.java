package timeout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * assert문뿐만 아니라 Annotation을 지원
 * assertTimeout
 */
class TimeoutTestsDemo {

    @BeforeEach
    @Timeout(5)
    void setUp() {
        // fails if execution time exceeds 5 seconds
    }

    /**
     * 기다리지 않고 Error
     */
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds100Milliseconds() throws InterruptedException {
        Thread.sleep(1000);
        // fails if execution time exceeds 100 milliseconds
    }
}