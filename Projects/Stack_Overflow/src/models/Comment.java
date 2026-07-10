package models;

import java.time.LocalDateTime;

public class Comment {
    private static Long commentCnt = 0L;
    private Long id;
    private User author;
    private String content;
    private LocalDateTime createdAt;

    public Comment(User author, String content) {
        this.id = getNextId();
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    private static synchronized Long getNextId() {
        return ++commentCnt;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author.getUsername() +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id != null && id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}