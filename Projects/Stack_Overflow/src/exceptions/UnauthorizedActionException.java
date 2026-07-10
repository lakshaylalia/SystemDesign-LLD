package exceptions;

public class UnauthorizedActionException extends AppException {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}