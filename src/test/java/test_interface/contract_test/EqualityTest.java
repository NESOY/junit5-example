package test_interface.contract_test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public interface EqualityTest<T> {

    T createValue();
    T createOtherValue();

    @Test
    @DisplayName("The values should be equal")
    default void should_be_equal() {
        assertThat(createValue()).isEqualTo(createValue());
        assertThat(createValue().hashCode()).isEqualTo(createValue().hashCode());
    }

    @Test
    @DisplayName("The values should not be equal")
    default void should_not_be_equal() {
        assertThat(createValue()).isNotEqualTo(createOtherValue());
        assertThat(createValue().hashCode()).isNotEqualTo(createOtherValue().hashCode());
    }
}
