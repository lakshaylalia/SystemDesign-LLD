package events;

public enum EventType {
    QUESTION_UPVOTED(true),
    QUESTION_DOWNVOTED(false),
    ANSWER_UPVOTED(true),
    ANSWER_DOWNVOTED(false),
    ANSWER_ACCEPTED(true);

    private final boolean positive;

    EventType(boolean positive) {
        this.positive = positive;
    }

    public boolean isPositive() {
        return positive;
    }
}