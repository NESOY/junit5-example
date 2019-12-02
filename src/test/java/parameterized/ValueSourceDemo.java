package parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ValueSourceDemo {
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest(name = "[{index}] {arguments} 테스트입니다.")
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void longgerTest(String candidate) {
        assertTrue(candidate.length() > 5);
    }

    /**
     * the target type declares exactly one suitable factory method or a factory constructor as defined below.
     * -> 아직까지는 같은 타입의 여러개의 필드는 주입하기 어렵다.
     * If multiple factory methods are discovered, they will be ignored. If a factory method and a factory constructor are discovered, the factory method will be used instead of the constructor.
     * factory method: a non-private, static method declared in the target type that accepts a single String argument
     */
    @ParameterizedTest
    @ValueSource(strings = "42 Cats")
    void testWithImplicitFallbackArgumentConversion(Book book) {
        assertEquals("42 Cats", book.getTitle());
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithExplicitArgumentConversion(@ConvertWith(ToStringArgumentConverter.class) String argument) {

        assertNotNull(TimeUnit.valueOf(argument));
    }

    @ParameterizedTest
    @ValueSource(strings = { "01.01.2017", "31.12.2017" })
    void testWithExplicitJavaTimeConverter(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {

        assertEquals(2017, argument.getYear());
    }
}

/**
 * Spring의 Converter와 비슷해보인다.
 */
class ToStringArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, targetType, "Can only convert to String");
        return String.valueOf(source);
    }
}