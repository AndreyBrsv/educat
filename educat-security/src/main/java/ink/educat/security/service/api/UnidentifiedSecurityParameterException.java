package ink.educat.security.service.api;

public class UnidentifiedSecurityParameterException extends RuntimeException {

    public UnidentifiedSecurityParameterException() {
    }

    public UnidentifiedSecurityParameterException(String message) {
        super(message);
    }

    public UnidentifiedSecurityParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnidentifiedSecurityParameterException(Throwable cause) {
        super(cause);
    }

    public UnidentifiedSecurityParameterException(String message,
                                                  Throwable cause,
                                                  boolean enableSuppression,
                                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
