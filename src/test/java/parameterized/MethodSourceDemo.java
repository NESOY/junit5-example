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

public class MethodSourceDemo {
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
        assertTrue(Lists.list("apple", "banana").contains(argument));
    }
    @ParameterizedTest
    @MethodSource("parameterized.OtherClass#stringOtherProvider") // javadoc Expression
    void testWithExplicitLocalMethodSource2(String argument) {
        assertNotNull(argument);
        assertTrue(Lists.list("apple", "banana").contains(argument));
    }

    /**
     * static으로 선언되어야 함
     * 마치 Spring의 Bean
     */
    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }




    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }

    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }


    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    /**
     * 예상? 순서대로 Binding
     */
    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }
}

class OtherClass {
    static Stream<String> stringOtherProvider() {
        return Stream.of("apple", "banana");
    }
}
