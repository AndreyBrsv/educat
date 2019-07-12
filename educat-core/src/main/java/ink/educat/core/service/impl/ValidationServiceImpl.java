package ink.educat.core.service.impl;

import ink.educat.core.service.api.Validate;
import ink.educat.core.service.api.ValidationService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

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
                //Имлементировать алгоритм валидации для различных типов
            }
        }
    }
}
