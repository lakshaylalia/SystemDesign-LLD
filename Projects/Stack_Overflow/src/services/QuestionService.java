package services;

import exceptions.BusinessRuleViolationException;
import exceptions.InvalidInputException;
import exceptions.QuestionNotFoundException;
import exceptions.UnauthorizedActionException;
import models.Question;
import dao.QuestionDAO;
import dto.PagedResult;
import models.User;
import strategy.SortStrategy;
import events.EventType;
import events.Observer;
import events.ReputationEvent;
import java.util.ArrayList;

import java.util.List;

public class QuestionService {
    private QuestionDAO questionDAO;
    private UserService userService;
    private List<Observer> observers = new ArrayList<>();
    private PostSortService<Question> questionSortService = new PostSortService<>();

    public QuestionService(QuestionDAO questionDAO, UserService userService) {
        this.questionDAO = questionDAO;
        this.userService = userService;
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
        if (question.hasUpvoted(userId)) {
            throw new BusinessRuleViolationException("You have already upvoted this question");
        }
        if (question.hasDownvoted(userId)) {
            throw new BusinessRuleViolationException("You have already downvoted this question. Remove your downvote first");
        }

        question.addUpvote(userId);
        question.setUpdatedAt();

        notifyObservers(new ReputationEvent(EventType.QUESTION_UPVOTED, question.getAuthor().getId(), 5L));
    }

    public void downvoteQuestion(Long questionId, Long userId) {
        Question question = questionDAO.getById(questionId);
        if (question == null) {
            throw new QuestionNotFoundException(questionId);
        }
        if (question.getAuthor().getId().equals(userId)) {
            throw new BusinessRuleViolationException("Cannot vote on your own question");
        }
        if (question.hasDownvoted(userId)) {
            throw new BusinessRuleViolationException("You have already downvoted this question");
        }
        if (question.hasUpvoted(userId)) {
            throw new BusinessRuleViolationException("You have already upvoted this question. Remove your upvote first");
        }
        User voter = userService.getUserById(userId);
        if (voter.getReputation() < 15L) {
            throw new UnauthorizedActionException("You need at least 15 reputation to downvote");
        }

        question.addDownvote(userId);
        question.setUpdatedAt();

        notifyObservers(new ReputationEvent(EventType.QUESTION_DOWNVOTED, question.getAuthor().getId(), 2L));
        notifyObservers(new ReputationEvent(EventType.QUESTION_DOWNVOTED, userId, 1L));
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

    public List<Question> getAllQuestionsSorted() {
        List<Question> all = questionDAO.getAll();
        return questionSortService.sort(all);
    }

    public void setQuestionSortStrategy(SortStrategy<Question> strategy) {
        questionSortService.setStrategy(strategy);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(ReputationEvent event) {
        for (Observer o : observers) {
            o.onEvent(event);
        }
    }
}