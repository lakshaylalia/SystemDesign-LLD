package repository;

import models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuestionRepository {

    private static final QuestionRepository instance = new QuestionRepository();
    private Map<Long, Question> idToQuestion = new ConcurrentHashMap<>();
    private Map<Long, List<Long>> userToQuestionIds  = new ConcurrentHashMap<>();

    private QuestionRepository() {};

    public static QuestionRepository getInstance() {
        return instance;
    }

    public Question saveQuestion(Question question) {
        idToQuestion.put(question.getId(), question);
        Long authorId = question.getAuthor().getId();
        userToQuestionIds.computeIfAbsent(authorId, k -> new ArrayList<>()).add(question.getId());
        return question;
    }

    public Question getQuestionById(Long id) {
        return idToQuestion.get(id);
    }

    public List<Question> getUserQuestions(Long authorId) {
        List<Long> ids = userToQuestionIds.getOrDefault(authorId, new ArrayList<>());
        List<Question> result = new ArrayList<>();
        for (Long id : ids) {
            result.add(idToQuestion.get(id));
        }
        return result;
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(idToQuestion.values());
    }

    public Question updateQuestion(Question question) {
        idToQuestion.put(question.getId(), question);
        return idToQuestion.get(question.getId());
    }

    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);
        if (question == null) return;

        Long authorId = question.getAuthor().getId();

        idToQuestion.remove(id);

        List<Long> userQuestionIds = userToQuestionIds.get(authorId);
        if (userQuestionIds != null) {
            userQuestionIds.remove(id);
        }
    }

}
