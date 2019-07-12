package ink.educat.core.service.api.validation;

import ink.educat.core.service.api.EducatAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@EducatAnnotation
public @interface Validate {

    int maxLength() default 100;

    boolean nullable() default true;

    // Использует для проверки регулярные выражения
    String pattern() default "[{*}]";
}
