package models;

public class Answer extends Post {
    private Question question;
    private boolean isAccepted = false;

    public Answer(Question question, User author, String statement) {
        super(author, statement);
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void markAsAccepted() {
        isAccepted = true;
    }

    public void unmarkAccepted() {
        isAccepted = false;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}