package repository;

import models.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnswerRepository extends PostRepository<Answer> {

    private static final AnswerRepository instance = new AnswerRepository();
    private Map<Long, List<Long>> questionToAnswerIds = new ConcurrentHashMap<>();

    private AnswerRepository() {}

    public static AnswerRepository getInstance() {
        return instance;
    }

    @Override
    public Answer save(Answer answer) {
        super.save(answer);
        Long questionId = answer.getQuestion().getId();
        questionToAnswerIds.computeIfAbsent(questionId, k -> new ArrayList<>()).add(answer.getId());
        return answer;
    }

    public List<Answer> getAnswersForQuestion(Long questionId) {
        List<Long> ids = questionToAnswerIds.getOrDefault(questionId, new ArrayList<>());
        List<Answer> result = new ArrayList<>();
        for (Long id : ids) {
            result.add(getById(id));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        Answer answer = getById(id);
        if (answer == null) return;

        Long questionId = answer.getQuestion().getId();
        super.deleteById(id);

        List<Long> answerIds = questionToAnswerIds.get(questionId);
        if (answerIds != null) {
            answerIds.remove(id);
        }
    }
}