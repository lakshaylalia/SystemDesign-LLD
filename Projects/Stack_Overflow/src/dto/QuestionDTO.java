package dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDTO {
    private Long id;
    private String authorUsername;
    private Long authorId;
    private String statement;
    private List<String> tags;
    private Long upvotes;
    private Long downvotes;
    private Integer answerCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public QuestionDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }

    public Long getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Long downvotes) {
        this.downvotes = downvotes;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}