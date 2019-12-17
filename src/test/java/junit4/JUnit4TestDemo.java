package junit4;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class JUnit4TestDemo {
    /**
     * Vintage Engine 사용
     */
    @Test
    public void junit4Test() {
        assertFalse("Hello JUnit 4".isEmpty());
    }

    /**
     * Jupiter Engine 사용
     */
    @org.junit.jupiter.api.Test
    public void junit5Test() {
        org.junit.jupiter.api.Assertions.assertFalse("Hello JUnit 5".isEmpty());
    }

    /**
     * 혼용도 가능한 모습
     */
    @Test
    public void junitCombination() {
        org.junit.jupiter.api.Assertions.assertFalse("Hello JUnit 5".isEmpty());
        assertFalse("Hello JUnit 4".isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void junitCombination2() {
        org.junit.jupiter.api.Assertions.assertFalse("Hello JUnit 5".isEmpty());
        assertFalse("Hello JUnit 4".isEmpty());
    }
}
