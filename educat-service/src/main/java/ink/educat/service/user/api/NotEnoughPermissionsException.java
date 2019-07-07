package ink.educat.service.user.api;

/**
 * Исключение, генерируемое при недостатке прав доступа.
 */
public class NotEnoughPermissionsException extends RuntimeException {
    public NotEnoughPermissionsException(String message) {
        super(message);
    }
}
