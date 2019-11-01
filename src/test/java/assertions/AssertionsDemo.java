package assertions;

import domain.Calculator;
import domain.Person;
import org.junit.jupiter.api.Test;

import static java.time.Duration.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AssertionsDemo {
    private final Calculator calculator = new Calculator();
    private final Person person = new Person("Jane", "Doe");

    /*
     * Message 기능
     * MessageSupplier 기능 제공
     */
    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2), "The optional failure message is now the last parameter");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- " + "to avoid constructing complex messages unnecessarily.");
    }

    /*
     * 모든 조건을 만족해야 하는 테스트가 필요한 경우 사용하면 좋음.
     * 실패한 CASE 결과를 취합하여 한번에 Report로 반환
     * Lambda Expression으로 표현해야하는게 아쉬움.
     */
    @Test
    void groupedAssertions() {
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    /*
     * Executable한 테스트들은 병렬적으로 실행
     * 실행 코드 내부에서는 순서에 의존적인 테스트도 작성 가능
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

    /*
     * junit4 - @Test(expected = Exception.class) or @Rule
        * @Rule - https://nesoy.github.io/articles/2018-12/Junit-Rule
     * junit5 - assertThrows
     */
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    /*
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

    /*
     * 시간 제한 테스트 & 결과 검증 Test
     */
    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
        assertThat(actualResult).isEqualTo("a result");
    }

    /*
     * 시간 제한 테스트 & Method Reference Result Test
     */
    @Test
    void timeoutNotExceededWithMethod() {
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    /*
     * 실패하는 경우 얼마나 초과했는지 Report
     * execution exceeded timeout of 10 ms by 90 ms
     */
    @Test
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> { Thread.sleep(100); });
    }

    /*
     * 실패한 정보만 노출
     * preemptively : 선매하여 ; 예방적으로 ; 우선적으로.
     */
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        assertTimeoutPreemptively(ofMillis(10), () -> { Thread.sleep(100); });
    }

    private static String greeting() {
        return "Hello, World!";
    }
}