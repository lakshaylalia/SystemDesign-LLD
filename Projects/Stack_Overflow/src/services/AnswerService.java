package services;

import exceptions.AnswerNotFoundException;
import exceptions.BusinessRuleViolationException;
import exceptions.InvalidInputException;
import exceptions.QuestionNotFoundException;
import exceptions.UnauthorizedActionException;
import models.Answer;
import models.Question;
import dao.AnswerDAO;
import dao.AnswerDAOImpl;
import dao.QuestionDAO;
import dao.QuestionDAOImpl;
import dto.PagedResult;

import java.util.List;

public class AnswerService {
    private AnswerDAO answerDAO;
    private QuestionDAO questionDAO;

    public AnswerService() {
        this.answerDAO = new AnswerDAOImpl();
        this.questionDAO = new QuestionDAOImpl();
    }

    public Answer addAnswer(Answer answer) {
        if (answer.getStatement() == null || answer.getStatement().trim().isEmpty()) {
            throw new InvalidInputException("Answer statement cannot be empty");
        }
        Question question = questionDAO.getById(answer.getQuestion().getId());
        if (question == null) {
            throw new QuestionNotFoundException(answer.getQuestion().getId());
        }
        return answerDAO.save(answer);
    }

    public Answer getAnswerById(Long id) {
        Answer answer = answerDAO.getById(id);
        if (answer == null) {
            throw new AnswerNotFoundException(id);
        }
        return answer;
    }

    public List<Answer> getAnswersForQuestion(Long questionId) {
        return answerDAO.getAnswersForQuestion(questionId);
    }

    public List<Answer> getAllAnswers() {
        return answerDAO.getAll();
    }

    public PagedResult<Answer> getAnswersPaginated(int page, int size) {
        List<Answer> answers = answerDAO.getAllPaginated(page, size);
        long totalItems = answerDAO.count();
        return new PagedResult<>(answers, page, size, totalItems);
    }

    public Answer updateAnswer(Answer answer) {
        return answerDAO.update(answer);
    }

    public void deleteAnswer(Long answerId, Long requestingUserId) {
        Answer answer = answerDAO.getById(answerId);
        if (answer == null) {
            throw new AnswerNotFoundException(answerId);
        }
        if (!answer.getAuthor().getId().equals(requestingUserId)) {
            throw new UnauthorizedActionException("Only the author can delete this answer");
        }
        answerDAO.deleteById(answerId);
    }

    public void upvoteAnswer(Long answerId, Long userId) {
        Answer answer = answerDAO.getById(answerId);
        if (answer == null) {
            throw new AnswerNotFoundException(answerId);
        }
        if (answer.getAuthor().getId().equals(userId)) {
            throw new BusinessRuleViolationException("Cannot vote on your own answer");
        }
        answer.incrementUpvotes();
        answer.setUpdatedAt();
    }

    public void downvoteAnswer(Long answerId, Long userId) {
        Answer answer = answerDAO.getById(answerId);
        if (answer == null) {
            throw new AnswerNotFoundException(answerId);
        }
        if (answer.getAuthor().getId().equals(userId)) {
            throw new BusinessRuleViolationException("Cannot vote on your own answer");
        }
        answer.incrementDownvotes();
        answer.setUpdatedAt();
    }

    public void acceptAnswer(Long answerId, Long questionId, Long userId) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        Answer answer = answerDAO.getById(answerId);
        if (answer == null) {
            throw new AnswerNotFoundException(answerId);
        }
        if (!answer.getQuestion().getId().equals(questionId)) {
            throw new BusinessRuleViolationException("Answer does not belong to this question");
        }
        if (!question.getAuthor().getId().equals(userId)) {
            throw new UnauthorizedActionException("Only the question author can accept an answer");
        }
        for (Answer a : answerDAO.getAnswersForQuestion(questionId)) {
            a.unmarkAccepted();
        }
        answer.markAsAccepted();
        answer.setUpdatedAt();
    }

    public void rejectAnswer(Long answerId, Long questionId, Long userId) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        Answer answer = answerDAO.getById(answerId);
        if (answer == null) {
            throw new AnswerNotFoundException(answerId);
        }
        if (!answer.getQuestion().getId().equals(questionId)) {
            throw new BusinessRuleViolationException("Answer does not belong to this question");
        }
        if (!question.getAuthor().getId().equals(userId)) {
            throw new UnauthorizedActionException("Only the question author can reject an answer");
        }
        answer.unmarkAccepted();
        answer.setUpdatedAt();
    }

    public List<Answer> getAnswersSortedByVotes(Long questionId) {
        List<Answer> answers = answerDAO.getAnswersForQuestion(questionId);
        answers.sort((a, b) -> Long.compare(b.getUpvotes(), a.getUpvotes()));
        return answers;
    }
}