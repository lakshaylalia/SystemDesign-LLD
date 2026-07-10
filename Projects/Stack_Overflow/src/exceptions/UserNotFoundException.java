package exceptions;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(Long id) {
        super("User not found: " + id);
    }
}