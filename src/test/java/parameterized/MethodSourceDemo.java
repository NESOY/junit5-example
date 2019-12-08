package parameterized;

import org.assertj.core.util.Lists;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * MethodSource를 선언하면 해당 클래스나 메소드를 찾아 주입하는 형태
 * 선언하지않으면 Test이름과 동일한 메소드나 클래스를 찾음.
 */
public class MethodSourceDemo {
    /**
     * static으로 선언되어야 함
     * 마치 Spring의 Bean
     */
    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
        assertTrue(Lists.list("apple", "banana").contains(argument));
    }

    /**
     * javadoc Expression
     * 외부 클래스에 대한 접근 방법
     */
    @ParameterizedTest
    @MethodSource("parameterized.OtherClass#stringOtherProvider")
    void testWithExplicitLocalMethodSource2(String argument) {
        assertNotNull(argument);
        assertTrue(Lists.list("apple", "banana").contains(argument));
    }

    /**
     * 요런 형태도 가능한 모습
     */
    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }

    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }


    /**
     * Argument라는 객체로 묶어서 제공 가능
     */
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }
}

class OtherClass {
    static Stream<String> stringOtherProvider() {
        return Stream.of("apple", "banana");
    }
}
