package models;

import java.time.LocalDateTime;

public class Answer {
    private static Long answerCnt = 0L;
    private Long id;
    private Question question;
    private User author;
    private String  statement;
    private Long upvotes;
    private Long downvotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Answer(Question question, User author, String statement) {
        this.id = getAnswerId();
        this.question = question;
        this.author = author;
        this.statement = statement;
        this.upvotes = 0L;
        this.downvotes = 0L;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private static synchronized Long getAnswerId() {
        return ++answerCnt;
    }

    public static Long getAnswerCnt() {
        return answerCnt;
    }

    public Long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public User getAuthor() {
        return author;
    }

    public String getStatement() {
        return statement;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public Long getDownvotes() {
        return downvotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public synchronized void incrementUpvotes(Long upvotes) {
        this.upvotes++;
    }

    public synchronized void decrementDownvotes(Long downvotes) {
        if(downvotes <= 0) return;
        this.downvotes--;
    }

    public synchronized void incrementDownvotes(Long downvotes) {
        this.downvotes++;
    }

    public synchronized void incrementUpvotes() {
        if(upvotes <= 0) return;
        this.upvotes++;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question=" + question.getStatement() +
                ", author=" + author.getUsername() +
                ", statement='" + statement + '\'' +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
