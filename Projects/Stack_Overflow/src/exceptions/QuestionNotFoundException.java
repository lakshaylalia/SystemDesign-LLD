package exceptions;

public class QuestionNotFoundException extends AppException {
    public QuestionNotFoundException(Long id) {
        super("Question not found: " + id);
    }
}