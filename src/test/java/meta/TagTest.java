package meta;

import domain.Calculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TagTest {
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
}
