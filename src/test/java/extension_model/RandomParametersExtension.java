package extension_model;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

/**
 * Spring의 HandlerMethodArgumentResolver 모습과 유사함
 * Ref - https://github.com/spring-projects/spring-framework/blob/3a0f309e2c9fdbbf7fb2d348be861528177f8555/spring-web/src/main/java/org/springframework/web/method/support/HandlerMethodArgumentResolver.java
 * Ref - https://github.com/junit-team/junit5-samples/blob/r5.5.2/junit5-jupiter-extensions/src/main/java/com/example/random/RandomParametersExtension.java
 */
public class RandomParametersExtension implements ParameterResolver {

    private int maxValue = 500;

    public RandomParametersExtension(int minValue) {
        this.maxValue = minValue;
    }

    /**
     * Example Annotation 추가
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface Random {
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(Random.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getRandomValue(parameterContext.getParameter(), extensionContext);
    }

    private Object getRandomValue(Parameter parameter, ExtensionContext extensionContext) {
        Class<?> type = parameter.getType();
        java.util.Random random = extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL).getOrComputeIfAbsent(java.util.Random.class);
        if (int.class.equals(type)) {
            return random.nextInt(maxValue);
        }
        if (double.class.equals(type)) {
            return random.nextDouble();
        }
        throw new ParameterResolutionException("No random generator implemented for " + type);
    }

}