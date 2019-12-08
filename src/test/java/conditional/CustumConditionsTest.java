package conditional;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static conditional.JAVA_VENDOR.AdoptOpenJDK;
import static conditional.JAVA_VENDOR.OracleJDK;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

@DisplayNameGeneration(AnnotationDisplayNameGenerator.class)
public class CustumConditionsTest {

    /**
     * 여러개 조합해서 테스트 가능
     */
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledOnJavaVmVendor(AdoptOpenJDK)
    public void testConditionsDemo(){
        assertTrue(1==1);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledOnJavaVmVendor(OracleJDK)
    public void testConditionsDemo2(){
        assertTrue(1==1);
    }
}

/**
 * Custom JavaVM Vendor Condition
 */
class EnabledOnJavaVmVendorCondition implements ExecutionCondition {
    private static final ConditionEvaluationResult ENABLED_BY_DEFAULT = enabled("@EnabledOnJavaVmVendor is not present");
    static final ConditionEvaluationResult ENABLED_ON_CURRENT_JAVA_VENDOR = enabled("Enabled on JRE version: " + System.getProperty("java.vm.vendor"));
    static final ConditionEvaluationResult DISABLED_ON_CURRENT_JAVA_VENDOR = disabled("Disabled on JRE version: " + System.getProperty("java.vm.vendor"));

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        Optional<EnabledOnJavaVmVendor> annotations = findAnnotation(context.getElement(), EnabledOnJavaVmVendor.class);
        if (annotations.isPresent()) {
            JAVA_VENDOR[] value = annotations.get().value();
            return (Arrays.stream(value).anyMatch(JAVA_VENDOR::isCurrentVendor)) ?
                    ENABLED_ON_CURRENT_JAVA_VENDOR : DISABLED_ON_CURRENT_JAVA_VENDOR;
        }

        return ENABLED_BY_DEFAULT;
    }
}


/**
 * Custom Interface
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(EnabledOnJavaVmVendorCondition.class)
@interface EnabledOnJavaVmVendor {
    JAVA_VENDOR[] value();
}

/**
 * Test Enum
 */
enum JAVA_VENDOR {
    AdoptOpenJDK("AdoptOpenJDK"),
    OracleJDK("OracleJDK");

    private String name;

    JAVA_VENDOR(String vendorName) {
        this.name = vendorName;
    }

    public boolean isCurrentVendor() {
        return this == getCurrentVendor();
    }

    private static JAVA_VENDOR getCurrentVendor() {
        String javaVendor = System.getProperty("java.vm.vendor");
        for(JAVA_VENDOR vendor : JAVA_VENDOR.values()){
            if(javaVendor.equalsIgnoreCase(vendor.name))
                return vendor;
        }

        return null;
    }
}


/**
 * 매번 테스트 이름을 작성하는게 귀찮으니 자동으로 할 수는 없을까?
 * -> DisplayNameGenerator를 사용해보자.
 */
class AnnotationDisplayNameGenerator extends DisplayNameGenerator.ReplaceUnderscores {
    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return super.generateDisplayNameForClass(testClass);
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return super.generateDisplayNameForNestedClass(nestedClass) + "...";
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return testMethod.getAnnotation(EnabledOnJre.class).value()[0].toString() + " "
                + testMethod.getAnnotation(EnabledOnJavaVmVendor.class).value()[0].toString()
                + "에서 동작하는 테스트";
    }
}