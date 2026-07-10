package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    protected Set<Long> usersWhoUpvoted;
    protected Set<Long> usersWhoDownvoted;

    public Post(User author, String statement) {
        this.id = getNextId();
        this.author = author;
        this.statement = statement;
        this.upvotes = 0L;
        this.downvotes = 0L;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.usersWhoUpvoted = new HashSet<>();
        this.usersWhoDownvoted = new HashSet<>();
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

    public boolean hasUpvoted(Long userId) {
        return usersWhoUpvoted.contains(userId);
    }

    public boolean hasDownvoted(Long userId) {
        return usersWhoDownvoted.contains(userId);
    }

    public synchronized boolean addUpvote(Long userId) {
        if (usersWhoUpvoted.contains(userId)) {
            return false;
        }
        usersWhoUpvoted.add(userId);
        upvotes++;
        return true;
    }

    public synchronized boolean removeUpvote(Long userId) {
        if (!usersWhoUpvoted.contains(userId)) {
            return false;
        }
        usersWhoUpvoted.remove(userId);
        if (upvotes > 0) upvotes--;
        return true;
    }

    public synchronized boolean addDownvote(Long userId) {
        if (usersWhoDownvoted.contains(userId)) {
            return false;
        }
        usersWhoDownvoted.add(userId);
        downvotes++;
        return true;
    }

    public synchronized boolean removeDownvote(Long userId) {
        if (!usersWhoDownvoted.contains(userId)) {
            return false;
        }
        usersWhoDownvoted.remove(userId);
        if (downvotes > 0) downvotes--;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id != null && id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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