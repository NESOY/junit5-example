package assertions;

import domain.Calculator;
import domain.Person;
import org.junit.jupiter.api.Test;

import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit5에서는 다양한 Assertion이 추가되었다.
 *
 */
class AssertionsDemo {
    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Jane", "Doe");

    /**
     * 실패 메시지 매개변수 추가
     * Lazy Loading하는 실패 문구
     */
    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2), "실패했을때 메시지를 매개변수로 넣을 수 있다");
        assertTrue('a' < 'b', () -> "실패 메시지는 Lazy 생성자에 초기화한다.");
    }

    /**
     * 모든 조건을 만족해야 하는 테스트가 필요한 경우 사용하면 좋을듯.
     * 실패한 테스트 케이스 결과를 취합하여 한번에 Report로 반환
     */
    @Test
    void groupedAssertions() {
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    /**
     * 실행가능한 테스트들은 Stream 형태로 실행
     * Stream에 의해 선언한 순서대로 실행
     * 실행 코드 내부에서도 선언한 Assert 순으로 테스트도 작성 가능
     */
    @Test
    void dependentAssertions() {
        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );
                },
                () -> {
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );
                }
        );
    }

    /**
     * 예외를 검증하는 Assert
     * 예외를 반환받아 Message까지 검증 가능
     *
     * junit4 - @Test(expected = Exception.class) or @Rule
     * junit5 - assertThrows
     *
     * {@link @Rule - https://nesoy.github.io/articles/2018-12/Junit-Rule }
     */
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    /**
     * 테스트 시간 관련 Assert
     * 좀 더 직관적으로 보임
     *
     * junit4 - @Test(timeout=1000)
     * junit5 - assertTimeout
     */
    @Test
    void timeoutNotExceeded() {
        assertTimeout(ofNanos(2), () -> { });
        assertTimeout(ofMillis(2), () -> { });
        assertTimeout(ofSeconds(2), () -> { });
        assertTimeout(ofMinutes(2), () -> { });
        assertTimeout(ofDays(2), () -> { });
    }

    /**
     * 시간 제한 테스트 & 결과 검증 Test
     */
    @Test
    void timeoutNotExceededWithResult() {
        String actualResult = assertTimeout(ofMinutes(2), () -> "a result");

        assertEquals("a result", actualResult);
    }

    /**
     * 시간 제한 테스트 & Method Reference Result Test
     */
    @Test
    void timeoutNotExceededWithMethod() {
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);

        assertEquals("Hello, World!", actualGreeting);
    }

    /**
     * 시간 제한 테스트를 실패하는 경우
     * -> 얼마나 초과했는지 Report
     * execution exceeded timeout of 10 ms by 90 ms
     * 끝까지 돌려보고 결과 Reporting
     */
    @Test
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> { Thread.sleep(100); });
        assertTimeout(ofMillis(10), () -> { Thread.sleep(100); }, "실패 메시지도 추가 가능");
    }

    /**
     * 실패한 정보만 노출
     * preemptively : 선매하여 ; 예방적으로 ; 우선적으로.
     * execution timed out after 10 ms
     */
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        assertTimeoutPreemptively(ofMillis(10), () -> { Thread.sleep(100); });
        assertTimeoutPreemptively(ofMillis(10), () -> { Thread.sleep(100); }, "실패 메시지도 추가 가능");
    }

    private static String greeting() {
        return "Hello, World!";
    }
}