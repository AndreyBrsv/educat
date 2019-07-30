package ink.educat.user.dao.api;

public class UserNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = -2878103775082184544L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
