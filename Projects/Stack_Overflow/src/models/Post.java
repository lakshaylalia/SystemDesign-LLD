package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Post implements Commentable {
    private static Long postCnt = 0L;
    protected Long id;
    protected User author;
    protected String statement;
    protected Long upvotes;
    protected Long downvotes;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected List<Comment> comments;

    public Post(User author, String statement) {
        this.id = getNextId();
        this.author = author;
        this.statement = statement;
        this.upvotes = 0L;
        this.downvotes = 0L;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.comments = new ArrayList<>();
    }

    private static synchronized Long getNextId() {
        return ++postCnt;
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

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public synchronized void incrementUpvotes() {
        this.upvotes++;
    }

    public synchronized void decrementUpvotes() {
        if (this.upvotes <= 0) return;
        this.upvotes--;
    }

    public synchronized void incrementDownvotes() {
        this.downvotes++;
    }

    public synchronized void decrementDownvotes() {
        if (this.downvotes <= 0) return;
        this.downvotes--;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
}