package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private static Long questionCnt = 0L;
    private Long id;
    private User author;
    private String statement;
    private Long upvotes;
    private Long downvotes;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Question(User author, String statement) {
        this.id = getNextId();
        this.author = author;
        this.statement = statement;
        this.tags = new  ArrayList<>();
        this.upvotes = 0L;
        this.downvotes = 0L;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private static synchronized Long getNextId() {
        return ++questionCnt;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public Long getDownvotes() {
        return downvotes;
    }

    public synchronized void incrementDownvotes() {
        this.downvotes++;
    }

    public synchronized void decrementDownvotes() {
        if(this.downvotes <= 0) return ;
        this.downvotes--;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public synchronized void incrementUpvotes() {
        this.upvotes++;
    }

    public synchronized void decrementUpvotes() {
        if(this.upvotes <= 0) return ;
        this.upvotes--;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "id=" + id +
                ", author=" + author.getUsername() +
                ", statement='" + statement + '\'' +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", tags=" + tags +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}