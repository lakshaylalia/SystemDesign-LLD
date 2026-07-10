package models;

import java.util.List;

public interface Commentable {
    Long getId();
    void addComment(Comment comment);
    List<Comment> getComments();
}