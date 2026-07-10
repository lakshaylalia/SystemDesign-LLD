package services;

import exceptions.BusinessRuleViolationException;
import exceptions.InvalidInputException;
import exceptions.QuestionNotFoundException;
import exceptions.UnauthorizedActionException;
import models.Question;
import dao.QuestionDAO;
import dao.QuestionDAOImpl;
import dto.PagedResult;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {
    private QuestionDAO questionDAO;

    public QuestionService() {
        this.questionDAO = new QuestionDAOImpl();
    }

    public Question addQuestion(Question question) {
        if (question.getStatement() == null || question.getStatement().trim().isEmpty()) {
            throw new InvalidInputException("Question statement cannot be empty");
        }
        return questionDAO.save(question);
    }

    public Question getQuestionById(Long id) {
        Question question = questionDAO.getById(id);
        if (question == null) {
            throw new QuestionNotFoundException(id);
        }
        return question;
    }

    public List<Question> getUserQuestions(Long authorId) {
        return questionDAO.getUserQuestions(authorId);
    }

    public List<Question> getAllQuestions() {
        return questionDAO.getAll();
    }

    public PagedResult<Question> getQuestionsPaginated(int page, int size) {
        List<Question> questions = questionDAO.getAllPaginated(page, size);
        long totalItems = questionDAO.count();
        return new PagedResult<>(questions, page, size, totalItems);
    }

    public Question updateQuestion(Question question) {
        return questionDAO.update(question);
    }

    public void deleteQuestion(Long questionId, Long requestingUserId) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        if (!question.getAuthor().getId().equals(requestingUserId)) {
            throw new UnauthorizedActionException("Only the author can delete this question");
        }
        questionDAO.deleteById(questionId);
    }

    public void upvoteQuestion(Long questionId, Long userId) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        if (question.getAuthor().getId().equals(userId)) {
            throw new BusinessRuleViolationException("Cannot vote on your own question");
        }
        question.incrementUpvotes();
        question.setUpdatedAt();
    }

    public void downvoteQuestion(Long questionId, Long userId) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        if (question.getAuthor().getId().equals(userId)) {
            throw new BusinessRuleViolationException("Cannot vote on your own question");
        }
        question.incrementDownvotes();
        question.setUpdatedAt();
    }

    public void addNewTag(Long questionId, String tag) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        String normalizedTag = tag.toLowerCase();
        if (question.getTags().contains(normalizedTag)) {
            return;
        }
        question.addTag(normalizedTag);
    }

    public void removeTag(Long questionId, String tag) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        question.removeTag(tag.toLowerCase());
    }

    public List<Question> getQuestionsByTag(String tag) {
        return questionDAO.getQuestionsByTag(tag);
    }

    public List<Question> searchByKeyword(String keyword) {
        return questionDAO.searchByKeyword(keyword);
    }

    public List<Question> getAllQuestionsSortedByDate() {
        List<Question> all = questionDAO.getAll();
        all.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        return all;
    }

    public List<Question> getAllQuestionsSortedByVotes() {
        List<Question> all = questionDAO.getAll();
        all.sort((a, b) -> Long.compare(b.getUpvotes(), a.getUpvotes()));
        return all;
    }
}