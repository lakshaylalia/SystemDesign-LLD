package services;

import exceptions.InvalidInputException;
import models.Comment;
import models.Commentable;
import models.User;

public class CommentService {

    public Comment addComment(Commentable target, User author, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new InvalidInputException("Question statement cannot be empty");
        }
        Comment comment = new Comment(author, content);
        target.addComment(comment);
        return comment;
    }
}