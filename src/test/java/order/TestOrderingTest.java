package order;

import org.junit.jupiter.api.*;

/*
 * MethodOrderer#OrderAnnotation
 * MethodOrderer#Alphanumeric
 * MethodOrderer#Random
 */
public class TestOrderingTest {
        @Nested
        @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
        class TestMethodOdering {
                @Test
                @Order(1)
                void nullValues() {
                        // perform assertions against null values
                }

                @Test
                @Order(2)
                void emptyValues() {
                        // perform assertions against empty values
                }

                @Test
                @Order(3)
                void validValues() {
                        // perform assertions against valid values
                }
        }
}
