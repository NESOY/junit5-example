package order;

import org.junit.jupiter.api.*;

/**
 * 테스트 순서를 조정할 수 있다
 * {@link MethodOrderer.OrderAnnotation}
 * {@link MethodOrderer.Alphanumeric}
 * {@link MethodOrderer.Random}
 */
public class TestOrderingDemo {
        @Nested
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        class TestMethodOdering {
                @DisplayName("I AM FIRST")
                @Test
                @Order(1)
                void nullValues() {
                        // perform assertions against null values
                }

                @DisplayName("I AM SECOND")
                @Test
                @Order(2)
                void emptyValues() {
                        // perform assertions against empty values
                }

                @DisplayName("I AM THIRD")
                @Test
                @Order(3)
                void validValues() {
                        // perform assertions against valid values
                }
        }
}
