package ink.educat.core.service.api;

/**
 * Сервис для валидации полей. Достаточно подгрузить его в нужный пакет
 * и аннотировать необходимые поля классов аннотацией {@link Validate}.
 */
public interface ValidationService {

    /**
     * Метод валидации полей.
     *
     * @param obj - объект для валидации
     * @throws ValidationException - если хотя бы одно из полей, аннотированное
     *                             {@link Validate} не соответвует условиям внутри аннотации.
     */
    <T> void validate(T obj);
}
