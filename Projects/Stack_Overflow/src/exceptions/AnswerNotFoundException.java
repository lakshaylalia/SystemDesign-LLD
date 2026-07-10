package exceptions;

public class AnswerNotFoundException extends AppException {
    public AnswerNotFoundException(Long id) {
        super("Answer not found: " + id);
    }
}