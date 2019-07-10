package ink.educat.security.service.api;

/**
 * Исключение, генерируемое при недостатке прав доступа.
 */
public class NotEnoughPermissionsException extends RuntimeException {

    public NotEnoughPermissionsException() {
    }

    public NotEnoughPermissionsException(String message) {
        super(message);
    }

    public NotEnoughPermissionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPermissionsException(Throwable cause) {
        super(cause);
    }

    public NotEnoughPermissionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
