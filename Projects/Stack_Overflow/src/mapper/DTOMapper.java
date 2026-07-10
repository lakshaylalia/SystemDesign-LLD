package mapper;

import dto.*;
import models.*;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static QuestionDTO toQuestionDTO(Question question, int answerCount) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setAuthorUsername(question.getAuthor().getUsername());
        dto.setAuthorId(question.getAuthor().getId());
        dto.setStatement(question.getStatement());
        dto.setTags(question.getTags());
        dto.setUpvotes(question.getUpvotes());
        dto.setDownvotes(question.getDownvotes());
        dto.setAnswerCount(answerCount);
        dto.setCreatedAt(question.getCreatedAt());
        dto.setUpdatedAt(question.getUpdatedAt());
        return dto;
    }

    public static QuestionDTO toQuestionDTO(Question question) {
        return toQuestionDTO(question, 0);
    }

    public static List<QuestionDTO> toQuestionDTOs(List<Question> questions) {
        return questions.stream()
                .map(q -> toQuestionDTO(q, 0))
                .collect(Collectors.toList());
    }


    public static AnswerDTO toAnswerDTO(Answer answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        dto.setQuestionId(answer.getQuestion().getId());
        dto.setQuestionStatement(answer.getQuestion().getStatement());
        dto.setAuthorUsername(answer.getAuthor().getUsername());
        dto.setAuthorId(answer.getAuthor().getId());
        dto.setStatement(answer.getStatement());
        dto.setUpvotes(answer.getUpvotes());
        dto.setDownvotes(answer.getDownvotes());
        dto.setAccepted(answer.isAccepted());
        dto.setCreatedAt(answer.getCreatedAt());
        dto.setUpdatedAt(answer.getUpdatedAt());
        return dto;
    }

    public static List<AnswerDTO> toAnswerDTOs(List<Answer> answers) {
        return answers.stream()
                .map(DTOMapper::toAnswerDTO)
                .collect(Collectors.toList());
    }

    // User mappers
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    public static List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream()
                .map(DTOMapper::toUserDTO)
                .collect(Collectors.toList());
    }


    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setAuthorUsername(comment.getAuthor().getUsername());
        dto.setAuthorId(comment.getAuthor().getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }

    public static List<CommentDTO> toCommentDTOs(List<Comment> comments) {
        return comments.stream()
                .map(DTOMapper::toCommentDTO)
                .collect(Collectors.toList());
    }
}