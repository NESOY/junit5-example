package test_template;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@TestTemplate
@ExtendWith(NesoyTestTemplate.class)
public @interface StringTest {
}
