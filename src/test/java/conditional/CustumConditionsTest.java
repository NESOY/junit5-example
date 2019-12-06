package conditional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.Optional;

import static conditional.JAVA_VENDOR.AdoptOpenJDK;
import static conditional.JAVA_VENDOR.OracleJDK;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.disabled;
import static org.junit.jupiter.api.extension.ConditionEvaluationResult.enabled;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

public class CustumConditionsTest {

    /**
     * 여러개 조합해서 테스트 가능
     */
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledOnJavaVmVendor(AdoptOpenJDK)
    @DisplayName("JAVA_8, AdoptJDK에서 동작하는 테스트")
    public void testConditionsDemo(){
        assertTrue(1==1);
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    @EnabledOnJavaVmVendor(OracleJDK)
    @DisplayName("JAVA_8, OracleJDK에서 동작하는 테스트")
    public void testConditionsDemo2(){
        assertTrue(1==1);
    }
}


class EnabledOnJavaVmVendorCondition implements ExecutionCondition {
    private static final ConditionEvaluationResult ENABLED_BY_DEFAULT = enabled("@EnabledOnJavaVmVendor is not present");

    static final ConditionEvaluationResult ENABLED_ON_CURRENT_JAVA_VENDOR =
            enabled("Enabled on JRE version: " + System.getProperty("java.vm.vendor"));

    static final ConditionEvaluationResult DISABLED_ON_CURRENT_JAVA_VENDOR =
            disabled("Disabled on JRE version: " + System.getProperty("java.vm.vendor"));

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


@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(EnabledOnJavaVmVendorCondition.class)
@interface EnabledOnJavaVmVendor {
    JAVA_VENDOR[] value();
}

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