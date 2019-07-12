package ink.educat.core.service.impl.validation;

import ink.educat.core.service.api.validation.Validate;
import ink.educat.core.service.api.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * Сервис для валидации полей. В перспективе в дальнейшем может
 * быть расширен до полноценного сервиса валидации.
 *
 * @see ValidationService
 * @see Validate
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    /**
     * {@inheritDoc}
     *
     * @discussion данный метод пока не является потокобезопасным, при получении полей нужно ставить synchronized
     */
    @Override
    public <T> void validate(T obj) {
        final Class tClass = obj.getClass();
        final Field[] fields = tClass.getDeclaredFields();
        for (final Field field : fields) {
            if (field.isAnnotationPresent(Validate.class)) {
                final Validate validate = field.getAnnotation(Validate.class);
                final Pattern pattern = Pattern.compile(validate.pattern());
                field.setAccessible(true);
                final Class fieldType = field.getType();
                //Имлементировать алгоритм валидации для различных типов
                field.setAccessible(false);
            }
        }
    }
}
