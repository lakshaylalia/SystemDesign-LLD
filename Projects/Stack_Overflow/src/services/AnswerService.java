package services;

import exceptions.AnswerNotFoundException;
import exceptions.BusinessRuleViolationException;
import exceptions.InvalidInputException;
import exceptions.QuestionNotFoundException;
import exceptions.UnauthorizedActionException;
import models.Answer;
import models.Question;
import dao.AnswerDAO;
import dao.QuestionDAO;
import dto.PagedResult;
import models.User;
import strategy.SortStrategy;
import events.EventType;
import events.Observer;
import events.ReputationEvent;

import java.util.ArrayList;
import java.util.List;

public class AnswerService {
    private AnswerDAO answerDAO;
    private QuestionDAO questionDAO;
    private UserService userService;
    private List<Observer> observers = new ArrayList<>();
    private PostSortService<Answer> answerPostSortService = new PostSortService<>();


    public AnswerService(AnswerDAO answerDAO, QuestionDAO questionDAO, UserService userService) {
        this.answerDAO = answerDAO;
        this.questionDAO = questionDAO;
        this.userService = userService;
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
        if (answer.hasUpvoted(userId)) {
            throw new BusinessRuleViolationException("You have already upvoted this answer");
        }
        if (answer.hasDownvoted(userId)) {
            throw new BusinessRuleViolationException("You have already downvoted this answer. Remove your downvote first");
        }
        answer.addUpvote(userId);
        answer.setUpdatedAt();
        notifyObservers(new ReputationEvent(EventType.ANSWER_UPVOTED, answer.getAuthor().getId(), 10L));
    }

    public void downvoteAnswer(Long answerId, Long userId) {
        Answer answer = answerDAO.getById(answerId);
        if (answer == null) {
            throw new AnswerNotFoundException(answerId);
        }
        if (answer.getAuthor().getId().equals(userId)) {
            throw new BusinessRuleViolationException("Cannot vote on your own answer");
        }
        if (answer.hasDownvoted(userId)) {
            throw new BusinessRuleViolationException("You have already downvoted this answer");
        }
        if (answer.hasUpvoted(userId)) {
            throw new BusinessRuleViolationException("You have already upvoted this answer. Remove your upvote first");
        }
        User voter = userService.getUserById(userId);
        if (voter.getReputation() < 15L) {
            throw new UnauthorizedActionException("You need at least 15 reputation to downvote");
        }

        answer.addDownvote(userId);
        answer.setUpdatedAt();

        notifyObservers(new ReputationEvent(EventType.ANSWER_DOWNVOTED, answer.getAuthor().getId(), 2L));
        notifyObservers(new ReputationEvent(EventType.ANSWER_DOWNVOTED, userId, 1L));
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

        notifyObservers(new ReputationEvent(EventType.ANSWER_ACCEPTED, answer.getAuthor().getId(), 15L));
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

    public List<Answer> getAllAnswersSorted(Long questionId) {
        List<Answer> all = answerDAO.getAnswersForQuestion(questionId);
        return answerPostSortService.sort(all);
    }

    public void setAnswerSortStrategy(SortStrategy<Answer> strategy) {
        answerPostSortService.setStrategy(strategy);
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