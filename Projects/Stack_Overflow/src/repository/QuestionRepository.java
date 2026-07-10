package repository;

import models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuestionRepository extends PostRepository<Question> {

    private static final QuestionRepository instance = new QuestionRepository();
    private Map<Long, List<Long>> userToQuestionIds = new ConcurrentHashMap<>();

    private QuestionRepository() {}

    public static QuestionRepository getInstance() {
        return instance;
    }

    @Override
    public Question save(Question question) {
        super.save(question);
        Long authorId = question.getAuthor().getId();
        userToQuestionIds.computeIfAbsent(authorId, k -> new ArrayList<>()).add(question.getId());
        return question;
    }

    public List<Question> getUserQuestions(Long authorId) {
        List<Long> ids = userToQuestionIds.getOrDefault(authorId, new ArrayList<>());
        List<Question> result = new ArrayList<>();
        for (Long id : ids) {
            result.add(getById(id));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        Question question = getById(id);
        if (question == null) return;

        Long authorId = question.getAuthor().getId();
        super.deleteById(id);

        List<Long> userQuestionIds = userToQuestionIds.get(authorId);
        if (userQuestionIds != null) {
            userQuestionIds.remove(id);
        }
    }
}