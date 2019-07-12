package ink.educat.core.service.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Маркерная аннотация для аннотирования других аннотаций.
 * Которые принадлежат внутренним пакетам.
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EducatAnnotation {
    // На данный момент нет никаких параметров
}
