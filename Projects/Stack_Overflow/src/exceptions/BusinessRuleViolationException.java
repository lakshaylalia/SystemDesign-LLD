package exceptions;

public class BusinessRuleViolationException extends AppException {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}