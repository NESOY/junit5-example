package parameterized;

import domain.City;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

public class EnumSourceDemo {
    /**
     * Enum도 사용가능
     */
    @ParameterizedTest
    @EnumSource(City.class)
    void nullEmptyAndBlankStrings2(City city) {
        assertNotNull(city);
    }

    /**
     * 사용할 Enum 선언도 가능
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(TimeUnit timeUnit) {
        assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
    }

    /**
     * 제외할 Enum 선언도 가능
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = { "DAYS", "HOURS" })
    void testWithEnumSourceExclude(TimeUnit timeUnit) {
        assertFalse(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        assertTrue(timeUnit.name().length() > 5);
    }

    /**
     * 패턴도 사용가능
     */
    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithEnumSourceRegex(TimeUnit timeUnit) {
        String name = timeUnit.name();
        assertTrue(name.startsWith("M") || name.startsWith("N"));
        assertTrue(name.endsWith("SECONDS"));
    }
}
