package ink.educat.core.service.impl.validation;

import ink.educat.core.service.api.validation.Validate;
import ink.educat.core.service.api.validation.ValidationException;
import ink.educat.core.service.api.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
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
     * @discussion нужно поставить вопрос потокобезопасности данного методв
     */
    @Override
    public <T> void validate(T obj) {
        final Class tClass = obj.getClass();
        final Field[] fields = tClass.getDeclaredFields();
        for (final Field field : fields) {
            if (field.isAnnotationPresent(Validate.class)) {
                final Validate validate = field.getAnnotation(Validate.class);
                final Pattern pattern;
                if (validate.caseInsensitive()) {
                    pattern = Pattern.compile(validate.pattern(), Pattern.CASE_INSENSITIVE);
                } else {
                    pattern = Pattern.compile(validate.pattern());
                }
                final Class fieldType = field.getType();
                // Валидация для строк
                if (fieldType.equals(String.class)) {
                    field.setAccessible(true);
                    try {
                        final String str = (String) field.get(obj);
                        if (str == null) {
                            throw new ValidationException("Can't validate null field with name: " + field.getName());
                        } else {
                            if (str.length() > validate.maxLength()) {
                                throw new ValidationException(
                                        "Field " + field + " can't has size more than " + validate.maxLength()
                                );
                            }
                            final Matcher matcher = pattern.matcher(str);
                            if (!matcher.find()) {
                                throw new ValidationException(
                                        "Field " + field + " doesn't satisfy to regex " + validate.pattern()
                                );
                            }
                        }
                    } catch (final IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
                field.setAccessible(false);
            }
        }
    }
}
