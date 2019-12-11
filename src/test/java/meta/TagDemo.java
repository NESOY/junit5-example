package meta;

import domain.Calculator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 빌드할때 Tag관련 옵션을 추가하여 특정 Tag를 포함 or 제외 할 수 있다.
 * gradle clean test
 * Reference - https://www.mkyong.com/junit5/junit-5-tagging-and-filtering-tag-examples/
 */
public class TagDemo {
    private final Calculator calculator = new Calculator();

    /**
     * @Fast 태그 추가 & @Test Annotation 분리
     */
    @Fast
    @Test
    public void tagAnnotationTest(){
        assertThat(2).isEqualTo(calculator.add(1, 1));
    }

    /**
     * @Fast 태그 추가 & @Test Annotation 결합
     */
    @FastTest
    public void integrationTagAnnotation(){
        assertThat(2).isEqualTo(calculator.add(1, 1));
    }

    @Tag("slow")
    @Test
    public void slowTagAnnoation(){
        assertThat(2).isEqualTo(calculator.add(1, 1));
    }
}


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@interface Fast {
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@Test
@interface FastTest {
}

